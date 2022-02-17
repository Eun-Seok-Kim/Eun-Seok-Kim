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

		//���Ǹ���ÿ� ���� ��ġ
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			
			//Session Tracking: GET(Query���)
			response.sendRedirect("list.do?reason=logout");
			
			return;
		}
		
		
		//������
		String webPath = "/upload/";
		
		//�����->������
		ServletContext application =  request.getServletContext();
		String saveDir = application.getRealPath(webPath);
		int    maxSize = 1024*1024 * 100;  // 100MB
		
		MultipartRequest mr = new MultipartRequest(
				                                request,   //����
				                                saveDir,   //������ġ
				                                maxSize,   //�����ִ�ũ��(byte����)
				                                "utf-8",   //�������ڵ�
				                                new DefaultFileRenamePolicy());
		
		
		//���ε�� ȭ������ ���ϱ�
		String p_filename = "no_file";
		
		//���1)
		File  f = mr.getFile("photo");
		if(f != null) {
			//���ε�ȭ���� �����ϸ�
			p_filename = f.getName(); //���ε�� ȭ�ϸ��� ���Ѵ�
		}
		
		//(����)���ε�ȭ�Ͽ� ������ �Ķ���ʹ� ��� �޾ƾߵǳ�?
		//: mr�� ���ؼ� �޾ƾ� �ȴ�(request�κ��� ���ӹ޾ұ� ������)
		String p_subject = mr.getParameter("p_subject");//(O)
		String p_content = mr.getParameter("p_content").replaceAll("\r\n", "<br>");//(O)
		
		//�� ip������ request�� �̿�
		String p_ip = request.getRemoteAddr();
		
		int m_idx = Integer.parseInt(mr.getParameter("m_idx"));
		
		//����
		PhotoVo  vo = new PhotoVo(p_subject, p_content, p_filename, p_ip, m_idx);
	
		//DB insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//��Ϻ���
		response.sendRedirect("list.do");
	
	
	}
}
