package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SungTBDao;

/**
 * Servlet implementation class SungDeleteAction
 */
@WebServlet("/sung/delete.do")
public class SungDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//  /sung/delete.do?idx=12
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//DB Delete
		int res = SungTBDao.getInstance().delete(idx);
		
		//리스트로 이동해라: Client(Browser)에게 list.do로 재요청하도록 정보를 전달
		response.sendRedirect("list.do");
		

		
	}
}


