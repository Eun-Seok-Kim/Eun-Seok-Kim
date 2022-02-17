package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class TestJsonParseActionSungListAction
 */
@WebServlet("/test_json.do")
public class TestJsonParseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String json = "{\"name\":\"�ϱ浿\",\"age\":30,\"tel\":\"010-111-1234\"}";
		JSONObject jsonParser = new JSONObject(json);
		
		String name = jsonParser.getString("name");
		int age	= jsonParser.getInt("age");
		String tel = jsonParser.getString("tel");
		System.out.printf("�̸� : %s ����:%d�� ��ȭ:%s\n",name,age,tel);
	}

}

