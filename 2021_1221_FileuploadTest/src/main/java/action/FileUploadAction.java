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
		// /upload.do?title=���&photo=apple.jpg
		
		//web���
		String webPath = "/upload";
		
		//web��θ� >> �����η� ����
		ServletContext application = request.getServletContext();
		String saveDir = application.getRealPath(webPath);
		System.out.println(saveDir);
		
		//Upload �� ���� ������ġ ����
		//String saveDir = "C:\\Work\\upload"; //<������
		// �ִ� ���¿뷮 100MB
		int maxSize	= 1024*1024 * 100; 
		
		// enctype="multypart/form-data" MultipartRequest�� ����
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		/*
		MultipartRequest mr = new MultipartRequest(
													request,//��������
													saveDir,//������ġ
													maxSize,//�����ִ�ũ��(byte����)
													"utf-8",//���ڵ�
													//�������ϸ� ����� ���ϸ� �����Ͽ� ���ε�
													new DefaultFileRenamePolicy());
		*/
		//���ε�� �������� ���ϱ�
		//���1
		String filename = "no_file";
		//���� ���ϳ���
		String original_filename = mr.getOriginalFileName("photo");
		
		File f = mr.getFile("photo");
		if(f != null) {
			//���ε�� ������ �����Ҷ�
			filename = f.getName();//���ε�� ���ϸ��� ���Ѵ�
		}
		//���� ���� �Ҷ�
		//f.delete();
		
		//���2	
		//�Ʒ� ����� ���ε�� ������ �����Ҷ�����
		//filename = mr.getFilesystemName("photo");
		
		//���ε� ���� �� ������ �Ķ���ʹ� ��� �޾ƾ��ϳ�
		//���ε�� ���Ϸ� ���Ͽ� mr�� ������ ���ӹ޾ұ⶧���� mr�� �޾ƾ���
		String title = mr.getParameter("title");
		//ip�� request��
		String ip = request.getRemoteAddr();
		System.out.printf("����:%s\n���ϸ� : %s\n",title,filename);
		//request binding
		request.setAttribute("title", title);
		request.setAttribute("filename", filename);
		
		//forward
		String forward_page = "result_upload.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

