package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SungTBDao;

/**
 * Servlet implementation class SungDeleteActionSungListAction
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

		// /sung/delete.do?idx=12
		//idx 값이 문자로 들어오기때문에 숫자로 변환
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//DB delete
		int res = SungTBDao.getInstance().delete(idx);
		
		//전체 list로 이동 (Client Browser에게 list.do로 재요청하도록 정보 전달
		//response가 없으면 delete 페이지에서 멈춰있음.
		response.sendRedirect("list.do");

		}
}