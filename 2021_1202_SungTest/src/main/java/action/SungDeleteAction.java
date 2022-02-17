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
		//idx ���� ���ڷ� �����⶧���� ���ڷ� ��ȯ
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//DB delete
		int res = SungTBDao.getInstance().delete(idx);
		
		//��ü list�� �̵� (Client Browser���� list.do�� ���û�ϵ��� ���� ����
		//response�� ������ delete ���������� ��������.
		response.sendRedirect("list.do");

		}
}