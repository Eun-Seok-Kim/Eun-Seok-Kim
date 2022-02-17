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

		// �Ķ���͹ޱ�
		String jumin_no = request.getParameter("jumin_no");

		// �ֹΰ�ü�� �̿��ؼ� �ΰ����� ���ϱ�
		Jumin jumin = new Jumin();
		jumin.setJumin_no(jumin_no);

		int year 		= jumin.getYear();
		int age 		= jumin.getAge();
		String tti 		= jumin.getTti();
		String season 	= jumin.getSeason();
		String ganji 	= jumin.getGanji();
		String gender 	= jumin.getGender();
		String local 	= jumin.getLocal();
		
		
		//request binding(request���ؼ� �����͸� �����Ų��)
		//:�ڹ��� ��簴ü(Object) ������ �� �ִ�
		//                      key       value
		request.setAttribute("jumin_no", jumin_no);
		request.setAttribute("year", year);  //auto-boxing (int -> Integer)
		request.setAttribute("age", age);
		request.setAttribute("tti", tti );
		request.setAttribute("season", season);
		request.setAttribute("ganji", ganji);
		request.setAttribute("gender", gender);
		request.setAttribute("local", local);
			
		
		
		//����� JSP�� ������
		String forward_page = "jumin_result.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}
