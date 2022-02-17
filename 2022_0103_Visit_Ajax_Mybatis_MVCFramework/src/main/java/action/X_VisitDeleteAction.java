package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;

/**
 * Servlet implementation class VisitDeleteActionSungListAction
 */
//@WebServlet("/visit/delete.do")
public class X_VisitDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//데이터 가져오기
		int idx = Integer.parseInt(request.getParameter("idx"));
		//request binding
		int res =VisitDao.getInstance().delete(idx);
		//forward
		response.sendRedirect("list.do");
	}

}

