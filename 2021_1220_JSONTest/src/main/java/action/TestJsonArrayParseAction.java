package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class TestJsonArrayParseActionSungListAction
 */
@WebServlet("/test_json_arrat.do")
public class TestJsonArrayParseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String json = "{ \"data\": [ {\"name\":\"일길동\", \"age\":31, \"tel\":\"010-111-1234\"}, {\"name\":\"이길동\", \"age\":32, \"tel\":\"010-222-1234\"}, {\"name\":\"삼길동\", \"age\":33, \"tel\":\"010-333-1234\"} ] }";
	      
		JSONObject jsonParser = new JSONObject(json);
		JSONArray jsonArray = jsonParser.getJSONArray("data");
		//System.out.println(jsonArray.length());
		for(int i=0; i<jsonArray.length();i++) {
			JSONObject p = jsonArray.getJSONObject(i);
			String name = p.getString("name");
			int age = p.getInt("age");
			String tel = p.getString("tel");
			System.out.printf("[%d]:이름 :%s 나이 :%d 번호 :%s\n",i,name,age,tel);
		}
	}

}

