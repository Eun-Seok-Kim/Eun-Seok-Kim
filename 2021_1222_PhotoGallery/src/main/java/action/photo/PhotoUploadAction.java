package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoUploadAction
 */
@WebServlet("/photo/photo_upload.do")
public class PhotoUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//웹상경로
		String webPath = "/upload/";
		
		//웹경로->절대경로
		ServletContext application = request.getServletContext();
		String saveDir = application.getRealPath(webPath);
		System.out.println(saveDir);
		int maxSize = 1024*1024 * 100;
		
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		//업로드된 화일정보 구하기
		String p_filename = "no_file";
		
		File f = mr.getFile("photo");
		
		if(f != null) {
			p_filename = f.getName();
		}
		
		int p_idx = Integer.parseInt(mr.getParameter("p_idx"));
		
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx); 
				
		File f1 = new File(saveDir,vo.getP_filename());
		f1.delete();
		
		//포장
		vo.setP_filename(p_filename);
		
		//DB insert
		int res = PhotoDao.getInstance().update_img(vo);
		
		//response.sendRedirect(".do");
		//response.sendRedirect("photo_modify_form.do");
		
		JSONObject json = new JSONObject();
		json.put("p_filename", p_filename);
		
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json.toString());
		
	}

}
