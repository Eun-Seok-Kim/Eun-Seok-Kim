package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitModifyActionSungListAction
 */
@WebServlet("/visit/modify.do")
public class VisitModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//������ ��������
		
		int idx			= Integer.parseInt(request.getParameter("idx"));
		String name		= request.getParameter("name");
		//����� ���ÿ�                          ����Ǿ��� <br>�� �ٽ� �����ؼ� ����
		String content	= request.getParameter("content").replaceAll("\r\n", "<br>");
		String pwd		= request.getParameter("pwd");
		String ip		= request.getRemoteAddr();
		
	

		//request binding
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		
		
		
		int res = VisitDao.getInstance().update(vo);
		
		//��Ϻ���
		response.sendRedirect("list.do");
		
	}

}
