package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberInsertAction
 */
//@WebServlet("/member/insert.do")
public class X_MemberInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /member/insert.do?m_name=ȫ�浿&m_id=hong&m_pwd=1234&m_zipcode=08768&m_addr=���� ���Ǳ�	
		
		//0.�������ڵ�
		request.setCharacterEncoding("utf-8");
		
		//1.parameter�ޱ�
		String m_name		=	request.getParameter("m_name");
		String m_id			= 	request.getParameter("m_id");
		String m_pwd		= 	request.getParameter("m_pwd");
		String m_zipcode	= 	request.getParameter("m_zipcode");
		String m_addr		= 	request.getParameter("m_addr");
		
		//2.ip���ϱ�
		String m_ip			= 	request.getRemoteAddr();
		
		//3.����
		MemberVo  vo = new MemberVo(m_name, m_id, m_pwd, m_zipcode, m_addr, m_ip, "�Ϲ�");
		
		//4.DB Insert
		int res = MemberDao.getInstance().insert(vo);
		
		//5.��Ϻ���
		response.sendRedirect("list.do");
		
		
	}
}










