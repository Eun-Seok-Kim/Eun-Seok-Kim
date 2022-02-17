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
		
		//0.수신인코딩
		request.setCharacterEncoding("utf-8");
		
		//1.parameter
		String m_id  = request.getParameter("m_id");
		String m_pwd = request.getParameter("m_pwd");
		String url = request.getParameter("url");
		//2.m_id에 해당되는 Vo얻어온다
		MemberVo  user = MemberDao.getInstance().selectOne(m_id); 
		
		//아이디가 틀린경우
		if(user==null) {
			//GET(Query)을 이용한 SessionTracking
			response.sendRedirect("login_form.do?reason=fail_id");
			return;
		}
		
		//비밀번호 틀린경우
		if(user.getM_pwd().equals(m_pwd)==false) {
			
			response.sendRedirect("login_form.do?reason=fail_pwd");
			return;
		}
		
		//로그인 된경우->로그인정보를 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if(url.isEmpty()) { 
		//메인페이지 이동
		response.sendRedirect("../product/list.do");
		}else {
		//url에 값이 있으면 들어온 url로 이동한다
		response.sendRedirect(url);		
		}
		
		
		

		
	}
}
