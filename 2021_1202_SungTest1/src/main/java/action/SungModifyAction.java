package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SungTBDao;
import vo.SungVo;

/**
 * Servlet implementation class SungModifyAction
 */
@WebServlet("/sung/modify.do")
public class SungModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//   /sung/modify.do?idx=28&name=박길동&kor=77&eng=88&mat=99
		
		//0.수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//1.parameter 받기
		String  name	= request.getParameter("name");
		
		int  idx 		= Integer.parseInt(request.getParameter("idx"));
		int  kor 		= Integer.parseInt(request.getParameter("kor"));
		int  eng 		= Integer.parseInt(request.getParameter("eng"));
		int  mat 		= Integer.parseInt(request.getParameter("mat"));
		
		//2.SungVo로 포장
		SungVo  vo = new SungVo(idx, name, kor, eng, mat);
		
		//3.DB update
		int res = SungTBDao.getInstance().update(vo);
		
		//4.목록보기
		response.sendRedirect("list.do");
		
	}
}








