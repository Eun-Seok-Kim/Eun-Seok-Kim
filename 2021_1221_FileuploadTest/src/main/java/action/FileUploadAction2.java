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
@WebServlet("/upload2.do")
public class FileUploadAction2 extends HttpServlet {
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
		MultipartRequest mr1 = new MultipartRequest(request, saveDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
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
		String filename1 = "no_file1";
		//원래 파일네임
		String original_filename1 = mr1.getOriginalFileName("photo1");
		
		File f = mr1.getFile("photo1");
		if(f != null) {
			//업로드된 파일이 존재할때
			filename1 = f.getName();//업로드된 파일명을 구한다
		}
		String filename2 = "no_file2";
		//원래 파일네임
		String original_filename2 = mr1.getOriginalFileName("photo2");
		
		f = mr1.getFile("photo2");
		if(f != null) {
			//업로드된 파일이 존재할때
			filename2 = f.getName();//업로드된 파일명을 구한다
		}
		//파일 삭제 할때
		//f.delete();
		
		//방법2	
		//아래 방법은 업로드된 파일을 삭제할때힘듬
		//filename = mr.getFilesystemName("photo");
		
		//업로드 파일 외 나머지 파라메터는 어떻게 받아야하나
		//업로드된 파일로 인하여 mr이 수신을 위임받았기때문에 mr로 받아야함
		String title1 = mr1.getParameter("title1");
		String title2 = mr1.getParameter("title2");
		//ip는 request로
		String ip = request.getRemoteAddr();
		//System.out.printf("제목:%s\n파일명 : %s\n",title1,filename1);
		//System.out.printf("제목:%s\n파일명 : %s\n",title2,filename2);
		//request binding
		request.setAttribute("title1", title1);
		request.setAttribute("filename1", filename1);
		
		request.setAttribute("title2", title2);
		request.setAttribute("filename2", filename2);

		
		//forward
		String forward_page = "result_upload2.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

