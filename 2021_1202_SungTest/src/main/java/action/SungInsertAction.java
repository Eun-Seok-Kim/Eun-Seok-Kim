package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SungTBDao;
import vo.SungVo;

/**
 * Servlet implementation class SungInsertActionSungListAction
 */
@WebServlet("/sung/insert.do")
public class SungInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//���� ���ڵ� ����
		request.setCharacterEncoding("utf-8");
		//Parameter ����
		String name = request.getParameter("name");
		int kor     = Integer.parseInt(request.getParameter("kor"));
		int eng		= Integer.parseInt(request.getParameter("eng"));
		int mat		= Integer.parseInt(request.getParameter("mat"));
		//SungVo��ü�� ����
		SungVo vo = new SungVo(name, kor, eng, mat);
		//DB Insert ��û
		
		int res = SungTBDao.getInstance().insert(vo);
		
		//����Ʈ�� ���ư���(���ο���� �߰� �Ϸ�)
		response.sendRedirect("list.do");
	}

}

