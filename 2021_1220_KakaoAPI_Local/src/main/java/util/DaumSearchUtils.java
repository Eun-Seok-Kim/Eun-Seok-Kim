package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import mykey.MyOpenAPIKey;
import vo.DaumLocalVo;

public class DaumSearchUtils {

	public static List<DaumLocalVo>  getLocalListFromJson(
										String  keyword,      //검색어
										double latitude,      //위도
										double longitude,     //경도
										int radius,
										int page,
										int size) throws Exception{
		List<DaumLocalVo> list = new ArrayList<DaumLocalVo>();
		
		//        3ebe1c7e586491e37bc04090f8d133b0
		//String  kakaoAK = "KakaoAK 3ebe1c7e586491e37bc04090f8d133b0";
		keyword = URLEncoder.encode(keyword, "utf-8");
		String  kakaoAK = MyOpenAPIKey.Daum.KAKAOAK;
		String urlStr = String.format("https://dapi.kakao.com/v2/local/search/keyword.json?query=%s&x=%f&y=%f&radius=%d&page=%d&size=%d&sort=distance",
				keyword,longitude,latitude,radius,page,size
			);
	
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		//발급받은 key
		connection.setRequestProperty("Authorization", kakaoAK); 
		connection.setRequestProperty("Content-Type", "application/plain"); 
		connection.connect();
		
		InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"utf-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		
		while(true) {
			String readData = br.readLine();
			if(readData==null)break;
			sb.append(readData);
		}
		
		 
		JSONObject json = new JSONObject(sb.toString());
		
		//검색결과정보
		JSONObject meta = json.getJSONObject("meta");
		int pageable_count = meta.getInt("pageable_count");
		
		//검색목록
		JSONArray local_array = json.getJSONArray("documents");
		for(int i=0;i<local_array.length();i++) {
			
			JSONObject local = local_array.getJSONObject(i);
			DaumLocalVo vo = new DaumLocalVo();
			vo.setPlace_name(local.getString("place_name"));
			vo.setPlace_url(local.getString("place_url"));
			vo.setAddress(local.getString("address_name"));
			vo.setRoad_address(local.getString("road_address_name"));
			vo.setCategory_group_name(local.getString("category_group_name"));
			vo.setCategory_name(local.getString("category_name"));
			vo.setPhone(local.getString("phone"));
			
			int distance=0;
			double local_longitude=0;
			double local_latitude=0;
			
			try {
				local_longitude = Double.parseDouble(local.getString("y"));
				vo.setLongitude(local_longitude);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			try {
				local_latitude = Double.parseDouble(local.getString("x"));
				vo.setLatitude(local_latitude);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				radius = Integer.parseInt(local.getString("radius"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				distance = Integer.parseInt(local.getString("distance"));
				vo.setDistance(distance);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			list.add(vo);
			
		}
		
		//거리순(가까운 순서로 정렬)
		//Collections.sort(list, new DaumLocalVoComp());
		
		
		
		return list;
	}
	//ArrayList 정렬기준 객체
	static class DaumLocalVoComp implements Comparator<DaumLocalVo>{

		public int compare(DaumLocalVo lhs, DaumLocalVo rhs) {
			// TODO Auto-generated method stub
			int ret=0;
			if(lhs.getDistance()>rhs.getDistance())ret=1;
			else if(lhs.getDistance()<rhs.getDistance())ret=-1;

			return ret;
		}
	}
	
}
