package action.member;

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
 * Servlet implementation class MemberLoginAction
 */
//@WebServlet("/member/login.do")
public class X_MemberLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /member/login.do?m_id=one&m_pwd=1234
		
		//0.�������ڵ�
		request.setCharacterEncoding("utf-8");
		
		//1.parameter
		String m_id  = request.getParameter("m_id");
		String m_pwd = request.getParameter("m_pwd");
		String url = request.getParameter("url");
		//2.m_id�� �ش�Ǵ� Vo���´�
		MemberVo  user = MemberDao.getInstance().selectOne(m_id); 
		
		//���̵� Ʋ�����
		if(user==null) {
			//GET(Query)�� �̿��� SessionTracking
			response.sendRedirect("login_form.do?reason=fail_id");
			return;
		}
		
		//��й�ȣ Ʋ�����
		if(user.getM_pwd().equals(m_pwd)==false) {
			
			response.sendRedirect("login_form.do?reason=fail_pwd");
			return;
		}
		
		//�α��� �Ȱ��->�α��������� ���ǿ� ����
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if(url.isEmpty()) { 
		//���������� �̵�
		response.sendRedirect("../product/list.do");
		}else {
		//url�� ���� ������ ���� url�� �̵��Ѵ�
		response.sendRedirect(url);		
		}
		
		
		

		
	}
}
