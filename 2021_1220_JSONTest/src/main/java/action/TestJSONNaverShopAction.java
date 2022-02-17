package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class TestJSONNaverShopActionSungListAction
 */
@WebServlet("/test_json_naver_shop.do")
public class TestJSONNaverShopAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String json =  "{ \"lastBuildDate\": \"Mon, 20 Dec 2021 11:07:20 +0900\", \"total\": 23233875, \"start\": 1, \"display\": 10, \"items\": [ { \"title\": \"삼성전자 노트북 플러스2 NT550XDA-K14A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=26529080523\", \"image\": \"https://shopping-phinf.pstatic.net/main_2652908/26529080523.20210415173758.jpg\", \"lprice\": \"528000\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"26529080523\", \"productType\": \"1\", \"brand\": \"노트북 플러스2\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"삼성전자 노트북 플러스2 NT550XDA-K24A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=26760806522\", \"image\": \"https://shopping-phinf.pstatic.net/main_2676080/26760806522.20210414174757.jpg\", \"lprice\": \"549000\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"26760806522\", \"productType\": \"1\", \"brand\": \"노트북 플러스2\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"삼성전자 갤럭시북 프로360 NT950QDY-A51A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=26962413523\", \"image\": \"https://shopping-phinf.pstatic.net/main_2696241/26962413523.20210531145315.jpg\", \"lprice\": \"1697990\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"26962413523\", \"productType\": \"1\", \"brand\": \"갤럭시북 프로360\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"삼성전자 노트북 플러스2 NT550XDZ-AD5A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=28482155554\", \"image\": \"https://shopping-phinf.pstatic.net/main_2848215/28482155554.20210818170935.jpg\", \"lprice\": \"699000\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"28482155554\", \"productType\": \"1\", \"brand\": \"노트북 플러스2\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"삼성전자 갤럭시북 프로 NT950XDX-G51A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=26964275522\", \"image\": \"https://shopping-phinf.pstatic.net/main_2696427/26964275522.20210531150512.jpg\", \"lprice\": \"1527990\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"26964275522\", \"productType\": \"1\", \"brand\": \"갤럭시북 프로\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"삼성전자 갤럭시북 프로 NT950XDC-X71A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=26963856522\", \"image\": \"https://shopping-phinf.pstatic.net/main_2696385/26963856522.20210720170654.jpg\", \"lprice\": \"1849000\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"26963856522\", \"productType\": \"1\", \"brand\": \"갤럭시북 프로\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"베이직스 베이직북13 2세대 BB1321FW\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=29833859618\", \"image\": \"https://shopping-phinf.pstatic.net/main_2983385/29833859618.20211213143613.jpg\", \"lprice\": \"389000\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"29833859618\", \"productType\": \"1\", \"brand\": \"베이직스\", \"maker\": \"베이직스\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"삼성전자 갤럭시북 NT750XDA-X71A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=29327500618\", \"image\": \"https://shopping-phinf.pstatic.net/main_2932750/29327500618.20211021151036.jpg\", \"lprice\": \"1499000\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"29327500618\", \"productType\": \"1\", \"brand\": \"갤럭시북\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"삼성전자 갤럭시북 프로 NT950XDY-A51A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=26964351522\", \"image\": \"https://shopping-phinf.pstatic.net/main_2696435/26964351522.20210531152708.jpg\", \"lprice\": \"1398000\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"26964351522\", \"productType\": \"1\", \"brand\": \"갤럭시북 프로\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" }, { \"title\": \"삼성전자 갤럭시북 프로360 NT930QDY-A51A\", \"link\": \"https://search.shopping.naver.com/gate.nhn?id=26963354522\", \"image\": \"https://shopping-phinf.pstatic.net/main_2696335/26963354522.20210531160045.jpg\", \"lprice\": \"1598000\", \"hprice\": \"\", \"mallName\": \"네이버\", \"productId\": \"26963354522\", \"productType\": \"1\", \"brand\": \"갤럭시북 프로360\", \"maker\": \"삼성전자\", \"category1\": \"디지털/가전\", \"category2\": \"노트북\", \"category3\": \"\", \"category4\": \"\" } ] }";
		JSONObject jsonParser = new JSONObject(json);
		JSONArray jsonArray = jsonParser.getJSONArray("items");
		for(int i=0; i<jsonArray.length();i++) {
		JSONObject p = jsonArray.getJSONObject(i);
		String title = p.getString("title");
		int hprice=0, lprice =0;		
		try {
			lprice = Integer.parseInt(p.getString("lprice"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			hprice = Integer.parseInt(p.getString("hprice"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String mallName = p.getString("mallName");
        String brand = p.getString("brand");
        String maker = p.getString("maker");
        
        System.out.printf("%s \n최저가:%d  최고가:%d 쇼핑몰 :%s 브랜드:%s  마켓:%s",title,lprice,hprice,mallName,brand,maker);
		}
	}

}
