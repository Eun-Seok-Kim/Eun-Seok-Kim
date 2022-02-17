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
		//아이디가 틀리거나 없는경우
		if(user==null) {
			//GET Query 을 이용한 SessionTracking방식
			response.sendRedirect("login_form.do?reasion=fail_id");			
			return;
		}
		//비밀번호 틀린경우
		if(user.getM_pwd().equals(m_pwd)==false) {
			response.sendRedirect("login_form.do?reasion=fail_pwd");			
			return;
		}
		//로그인된경우 정보 세션에 저장
		
		HttpSession session = request.getSession();
		
		session.setAttribute("user", user);
		
		//메인페이지로 이동
		response.sendRedirect("list.do");
	}

}

