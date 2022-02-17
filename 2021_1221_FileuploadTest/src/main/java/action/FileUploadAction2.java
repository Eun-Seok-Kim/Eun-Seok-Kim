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
		MultipartRequest mr1 = new MultipartRequest(request, saveDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
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
		String filename1 = "no_file1";
		//���� ���ϳ���
		String original_filename1 = mr1.getOriginalFileName("photo1");
		
		File f = mr1.getFile("photo1");
		if(f != null) {
			//���ε�� ������ �����Ҷ�
			filename1 = f.getName();//���ε�� ���ϸ��� ���Ѵ�
		}
		String filename2 = "no_file2";
		//���� ���ϳ���
		String original_filename2 = mr1.getOriginalFileName("photo2");
		
		f = mr1.getFile("photo2");
		if(f != null) {
			//���ε�� ������ �����Ҷ�
			filename2 = f.getName();//���ε�� ���ϸ��� ���Ѵ�
		}
		//���� ���� �Ҷ�
		//f.delete();
		
		//���2	
		//�Ʒ� ����� ���ε�� ������ �����Ҷ�����
		//filename = mr.getFilesystemName("photo");
		
		//���ε� ���� �� ������ �Ķ���ʹ� ��� �޾ƾ��ϳ�
		//���ε�� ���Ϸ� ���Ͽ� mr�� ������ ���ӹ޾ұ⶧���� mr�� �޾ƾ���
		String title1 = mr1.getParameter("title1");
		String title2 = mr1.getParameter("title2");
		//ip�� request��
		String ip = request.getRemoteAddr();
		//System.out.printf("����:%s\n���ϸ� : %s\n",title1,filename1);
		//System.out.printf("����:%s\n���ϸ� : %s\n",title2,filename2);
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

