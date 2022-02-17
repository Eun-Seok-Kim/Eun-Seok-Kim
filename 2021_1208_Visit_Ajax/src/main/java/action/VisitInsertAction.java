package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitInsertActionSungListAction
 */
@WebServlet("/visit/insert.do")
public class VisitInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");

		//1.넘어온 Parameter수신
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		//\r\n -> <br>로 변환시켜서 저장
		content = content.replaceAll("\r\n", "<br>");
	
		//2. vo객체로 포장
		VisitVo vo = new VisitVo(name, content, pwd, ip);
	
		//3. DB insert
		int res = VisitDao.getInstance().insert(vo);
		
		//4.목록보기
		response.sendRedirect("list.do");
	}

}

