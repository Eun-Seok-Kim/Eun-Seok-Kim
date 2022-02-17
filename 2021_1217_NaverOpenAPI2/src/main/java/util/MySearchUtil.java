package util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import vo.ProductVo;

public class MySearchUtil {

	public static List<ProductVo> search_shop(String p_name,int start,int display)
	{
		List<ProductVo> list = new ArrayList<ProductVo>();
		String clientId = "Agnln2mMFnjsK4Wa3o7D";
		String clientSecret = "ZWFfNy1ILS";

		try {
			p_name = URLEncoder.encode(p_name, "utf-8");
			String urlStr = String.format("https://openapi.naver.com/v1/search/shop.xml?query=%s&start=%d&display=%d",
					         p_name,start,display
					);

			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			//占쌩급뱄옙占쏙옙 ID
			connection.setRequestProperty("X-Naver-Client-Id", clientId); 
			//占쌩급뱄옙占쏙옙 PW
			connection.setRequestProperty("X-Naver-Client-Secret", clientSecret); 
			// 占쏙옙占쏙옙占쏙옙청타占쏙옙
			connection.setRequestProperty("Content-Type", "application/xml"); 
			connection.connect();

			SAXBuilder builder = new SAXBuilder();
			Document   doc = builder.build (connection.getInputStream());

			Element  root     = doc.getRootElement();
			List<Element>   element_list = (List<Element>)root.getChild("channel").getChildren("item");

			for(Element item : element_list){
				String g_category = item.getChildText("category3");
				String g_name  = item.getChildText("title");
				String g_filename = item.getChildText("image");
				int g_price=0;
				try {
					g_price = Integer.parseInt(item.getChildText("lprice"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				String g_link = item.getChildText("link");
				
				//占쏙옙품占쏙옙占쏙옙占� 占쏙옙占쏙옙
				ProductVo vo = new ProductVo();
				vo.setG_category(g_category);
				vo.setG_name(g_name);
				vo.setG_filename(g_filename);
				vo.setG_price(g_price);
				vo.setG_link(g_link);
				
								
				//ArrayList占쏙옙 占쌍깍옙
				list.add(vo);
				

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return list;
	}
	
	
}