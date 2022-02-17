package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoInsertAcion
 */
@WebServlet("/photo/insert.do")
public class PhotoInsertAcion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//세션만료시에 대한 조치
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			
			//Session Tracking: GET(Query방식)
			response.sendRedirect("list.do?reason=logout");
			
			return;
		}
		
		
		//웹상경로
		String webPath = "/upload/";
		
		//웹경로->절대경로
		ServletContext application = request.getServletContext();
		String saveDir = application.getRealPath(webPath);
		System.out.println(saveDir);
		int maxSize = 1024*1024 * 100;
		
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		int    m_idx     = Integer.parseInt(mr.getParameter("m_idx"));
		
		//mr 통해 받기
		String p_subject = mr.getParameter("p_subject");
		String p_content = mr.getParameter("p_content").replaceAll("\r\n", "<br>");
		
		//업로드된 화일정보 구하기
		String p_filename = "no_file";
		
		File f = mr.getFile("photo");
		
		if(f != null) {
			p_filename = f.getName();
		}
		
		//request 통해 받기
		String p_ip = request.getRemoteAddr();
		
		//포장
		PhotoVo vo = new PhotoVo(p_subject, p_content, p_filename, p_ip, m_idx);
		
		//DB insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//목록보기
		response.sendRedirect("list.do");
		
		
	}
}