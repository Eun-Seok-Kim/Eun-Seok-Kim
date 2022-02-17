package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoModifyAction
 */
@WebServlet("/photo/modify.do")
public class PhotoModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /photo/modify.do?p_idx=22&p_subject=제목&p_content=내용
		request.setCharacterEncoding("utf-8");
		
		//mr 통해 받기
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		String p_subject = request.getParameter("p_subject");
		String p_content = request.getParameter("p_content").replaceAll("\r\n", "<br>");
		
		//request 통해 받기
		String p_ip = request.getRemoteAddr();
		
		//포장
		PhotoVo vo = new PhotoVo(p_idx, p_subject, p_content, p_ip);
		
		//DB insert
		int res = PhotoDao.getInstance().update(vo);

		response.sendRedirect("list.do");
		


	}
}
