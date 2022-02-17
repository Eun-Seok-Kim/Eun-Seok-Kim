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
		
		//����ȭ�ϸ� ������ ������� VO����
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		//ȭ���� ����� -> ������ ���ϱ�
		String webPath = "/upload/";
		String absPath = request.getServletContext().getRealPath(webPath);
		
		//������ ȭ������ ����
		//                ���͸�, ȭ�ϸ�
		File f = new File(absPath, vo.getP_filename());
		
		//ȭ�ϻ���
		f.delete();
		
		//DB insert
		int res = PhotoDao.getInstance().delete(p_idx);

		//��Ϻ���
		response.sendRedirect("list.do");
	}
}