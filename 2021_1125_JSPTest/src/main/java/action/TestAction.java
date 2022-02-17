package action;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestAction
 */
@WebServlet("/TestAction")
public class TestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//멤버변수 선언
	String name = "TestServlet";
	
	//멤버메소드
	public void display() {
			System.out.println(name + "님 안녕하세요");
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 해당서블릿의 초기화시 활용
		//JSP -> _jspInit()
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		//해당서블릿이 Container에서 내려(소멸)올때
		//JSP -> _jspDestory()
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//사용자의 요구에 의해서 호출
		//JSP -> _jspService()
		
		//service()내의 코드영역
		String ip = request.getRemoteAddr();
	}


}
