package action;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUploadActionSungListAction
 */
@WebServlet("/upload.do")
public class FileUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /upload.do?title=사과&photo=apple.jpg
		
		//web경로
		String webPath = "/upload";
		
		//web경로를 >> 절대경로로 변경
		ServletContext application = request.getServletContext();
		String saveDir = application.getRealPath(webPath);
		System.out.println(saveDir);
		
		//Upload 된 파일 저장위치 설정
		//String saveDir = "C:\\Work\\upload"; //<절대경로
		// 최대 가는용량 100MB
		int maxSize	= 1024*1024 * 100; 
		
		// enctype="multypart/form-data" MultipartRequest로 수신
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		/*
		MultipartRequest mr = new MultipartRequest(
													request,//수신위임
													saveDir,//저장위치
													maxSize,//저장최대크기(byte단위)
													"utf-8",//인코딩
													//동일파일명 존재시 파일명 변경하여 업로드
													new DefaultFileRenamePolicy());
		*/
		//업로드된 파일정보 구하기
		//방법1
		String filename = "no_file";
		//원래 파일네임
		String original_filename = mr.getOriginalFileName("photo");
		
		File f = mr.getFile("photo");
		if(f != null) {
			//업로드된 파일이 존재할때
			filename = f.getName();//업로드된 파일명을 구한다
		}
		//파일 삭제 할때
		//f.delete();
		
		//방법2	
		//아래 방법은 업로드된 파일을 삭제할때힘듬
		//filename = mr.getFilesystemName("photo");
		
		//업로드 파일 외 나머지 파라메터는 어떻게 받아야하나
		//업로드된 파일로 인하여 mr이 수신을 위임받았기때문에 mr로 받아야함
		String title = mr.getParameter("title");
		//ip는 request로
		String ip = request.getRemoteAddr();
		System.out.printf("제목:%s\n파일명 : %s\n",title,filename);
		//request binding
		request.setAttribute("title", title);
		request.setAttribute("filename", filename);
		
		//forward
		String forward_page = "result_upload.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

