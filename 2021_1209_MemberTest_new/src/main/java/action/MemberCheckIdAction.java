package action;

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
 * Servlet implementation class MemberCheckIdActionSungListAction
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
		// /member/check_id.do?m_id=hong
		request.setCharacterEncoding("utf-8");
		
		String m_id = request.getParameter("m_id");
		
		//vo에 등로된 selectOne로 데이터 존재여부 확인
		MemberVo vo = MemberDao.getInstance().selectOne(m_id);
		
		boolean bResult = true; //사용가능 true,
		//조회한 id에 사용중이면 null 이 아니기에 false로 변경
		if(vo!=null)
			bResult = false;
		//결과전송
		//json데이터 생성
		String resultJson = String.format("{\"result\":%b}", bResult);
		//전송 인코딩 설정
		response.setContentType("text/json; charset=utf-8;");
		
		response.getWriter().print(resultJson);
	}

}

