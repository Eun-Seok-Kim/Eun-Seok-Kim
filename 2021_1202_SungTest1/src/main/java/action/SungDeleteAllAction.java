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
 * Servlet implementation class SungDeleteAllAction
 */
@WebServlet("/sung/delete_all.do")
public class SungDeleteAllAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /sung/delete_all.do?idx=28&idx=29
		
		String [] idx_array = request.getParameterValues("idx");
		//String [] idx_array = { "28" , "29" };
		
		for(String str_idx : idx_array) {
			
			int idx = Integer.parseInt(str_idx);
			
			int res = SungTBDao.getInstance().delete(idx);
			
		}
		
		//목록보기
		response.sendRedirect("list.do");
		
	
	}
}
