package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitCheckPwdActionSungListAction
 */
@WebServlet("/visit/check_pwd.do")
public class VisitCheckPwdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,						
						   HttpServletResponse response)
						   throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /visit/check_pwd.do?idx=13&c_pwd=1234
		//수신 인코딩 설정
		request.setCharacterEncoding("utf-8"); 
		//parameter받기
		int idx			= Integer.parseInt(request.getParameter("idx"));
		String c_pwd	= request.getParameter("c_pwd");
		//idx에 해당되는 게시물 정보 얻어오기
		
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		//비밀번호 비교
		boolean bSame = vo.getPwd().equals(c_pwd);
		
		//결과를 요청한 데이터 타입으로 포장(json){"result" : true}
		//										  {"result" : false}
		String json_result = String.format("{\"result\":%b}", bSame);
		
		//결과응답
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json_result);
		
	

	}

}

