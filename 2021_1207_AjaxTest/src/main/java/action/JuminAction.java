package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myutil.Jumin;

/**
 * Servlet implementation class JuminActionSungListAction
 */
@WebServlet("/jumin.do")
public class JuminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//jumin.do?jumin_no=801212-1234560
		request.setCharacterEncoding("utf-8");

		// 파라메터받기
		String jumin_no = request.getParameter("jumin_no");

		// 주민객체를 이용해서 부가정보 구하기
		Jumin jumin = new Jumin();
		jumin.setJumin_no(jumin_no);

		int year 		= jumin.getYear();
		int age 		= jumin.getAge();
		String tti 		= jumin.getTti();
		String season 	= jumin.getSeason();
		String ganji 	= jumin.getGanji();
		String gender 	= jumin.getGender();
		String local 	= jumin.getLocal();
		
		
		//request binding(request통해서 데이터를 연결시킨다)
		//:자바의 모든객체(Object) 저장할 수 있다
		//                      key       value
		request.setAttribute("jumin_no", jumin_no);
		request.setAttribute("year", year);  //auto-boxing (int -> Integer)
		request.setAttribute("age", age);
		request.setAttribute("tti", tti );
		request.setAttribute("season", season);
		request.setAttribute("ganji", ganji);
		request.setAttribute("gender", gender);
		request.setAttribute("local", local);
			
		
		
		//출력할 JSP로 포워드
		String forward_page = "jumin_result.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}
