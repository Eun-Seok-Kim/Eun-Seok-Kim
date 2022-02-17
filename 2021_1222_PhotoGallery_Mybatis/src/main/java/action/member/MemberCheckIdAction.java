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
 * Servlet implementation class MemberCheckIdAction
 */
@WebServlet("/member/check_id.do")
public class MemberCheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//  /member/check_id.do?m_id=hong
		
		//0.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//1.parameter받기
		String m_id = request.getParameter("m_id");
		
		//2.m_id에 해당되는 객체가 존재하는지 여부
		MemberVo vo = MemberDao.getInstance().selectOne(m_id);
		
		boolean bResult = true; //사용가능하면 true 아니면 false
		
		//m_id가 이미 사용중이면...
		if(vo != null)
			bResult = false;
		
		//결과 전송
		//1.전송데이터 생성(JSON)
		String resultJson = String.format("{\"result\":%b}", bResult);
		
		//전송 mimeType및 인코딩설정
		response.setContentType("text/json; charset=utf-8;");
		
		response.getWriter().print(resultJson);
		

		
	}
}
