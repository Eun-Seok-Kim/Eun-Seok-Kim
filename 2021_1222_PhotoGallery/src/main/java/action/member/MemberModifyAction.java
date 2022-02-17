package action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberModifyAction
 */
@WebServlet("/member/modify.do")
public class MemberModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//  /member/modify.do?m_idx=2&m_name=김관리&m_id=admin&m_pwd=1234&m_zipcode=08768&m_addr=서울시&m_grade=관리자
		
		//0.수신인코딩
		request.setCharacterEncoding("utf-8");
		
		//1.parameter
		int m_idx = Integer.parseInt(request.getParameter("m_idx"));
		
		String m_name    = request.getParameter("m_name");
		String m_id      = request.getParameter("m_id");
		String m_pwd     = request.getParameter("m_pwd");
		String m_zipcode = request.getParameter("m_zipcode");
		String m_addr    = request.getParameter("m_addr");
		String m_grade   = request.getParameter("m_grade");
		
		//2.ip얻기
		String m_ip      = request.getRemoteAddr();
		
		//3.포장
		MemberVo vo = new MemberVo(m_idx, m_name, m_id, m_pwd, m_zipcode, m_addr, m_ip, m_grade);
		
		//4.DB update
		int res = MemberDao.getInstance().update(vo);

		//5.목록보기
		response.sendRedirect("list.do");
	}
}
