package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class PhotoInsertAction
 */
//@WebServlet("/photo/insert.do")
public class X_PhotoInsertAction extends HttpServlet {
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
		ServletContext application =  request.getServletContext();
		String saveDir = application.getRealPath(webPath);
		int    maxSize = 1024*1024 * 100;  // 100MB
		
		MultipartRequest mr = new MultipartRequest(
				                                request,   //위임
				                                saveDir,   //저장위치
				                                maxSize,   //저장최대크기(byte단위)
				                                "utf-8",   //수신인코딩
				                                new DefaultFileRenamePolicy());
		
		
		//업로드된 화일정보 구하기
		String p_filename = "no_file";
		
		//방법1)
		File  f = mr.getFile("photo");
		if(f != null) {
			//업로드화일이 존재하면
			p_filename = f.getName(); //업로드된 화일명을 구한다
		}
		
		//(주의)업로드화일외 나머지 파라메터는 어떻게 받아야되냐?
		//: mr을 통해서 받아야 된다(request로부터 위임받았기 때문에)
		String p_subject = mr.getParameter("p_subject");//(O)
		String p_content = mr.getParameter("p_content").replaceAll("\r\n", "<br>");//(O)
		
		//단 ip정보는 request을 이용
		String p_ip = request.getRemoteAddr();
		
		int m_idx = Integer.parseInt(mr.getParameter("m_idx"));
		
		//포장
		PhotoVo  vo = new PhotoVo(p_subject, p_content, p_filename, p_ip, m_idx);
	
		//DB insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//목록보기
		response.sendRedirect("list.do");
	
	
	}
}
