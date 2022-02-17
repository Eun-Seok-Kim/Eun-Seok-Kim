package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberLoginActionSungListAction
 */
@WebServlet("/member/login.do")
public class MemberLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /member/login.do?m_id=admin&m_pwd=1234
		request.setCharacterEncoding("utf-8");
		
		String m_id	 = request.getParameter("m_id");
		String m_pwd = request.getParameter("m_pwd");
		
		MemberVo user = MemberDao.getInstance().selectOne(m_id);
		//���̵� Ʋ���ų� ���°��
		if(user==null) {
			//GET Query �� �̿��� SessionTracking���
			response.sendRedirect("login_form.do?reasion=fail_id");			
			return;
		}
		//��й�ȣ Ʋ�����
		if(user.getM_pwd().equals(m_pwd)==false) {
			response.sendRedirect("login_form.do?reasion=fail_pwd");			
			return;
		}
		//�α��εȰ�� ���� ���ǿ� ����
		
		HttpSession session = request.getSession();
		
		session.setAttribute("user", user);
		
		//������������ �̵�
		response.sendRedirect("list.do");
	}

}

