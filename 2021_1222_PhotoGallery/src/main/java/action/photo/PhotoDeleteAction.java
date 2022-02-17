package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoDeleteAction
 */
@WebServlet("/photo/delete.do")
public class PhotoDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /photo/delete.do?p_idx=3
		
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		//삭제화일명 정보를 얻기위해 VO얻어옴
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		//화일의 상대경로 -> 절대경로 구하기
		String webPath = "/upload/";
		String absPath = request.getServletContext().getRealPath(webPath);
		
		//삭제할 화일정보 구함
		//                디렉터리, 화일명
		File f = new File(absPath, vo.getP_filename());
		
		//화일삭제
		f.delete();
		
		//DB insert
		int res = PhotoDao.getInstance().delete(p_idx);

		//목록보기
		response.sendRedirect("list.do");
	}
}