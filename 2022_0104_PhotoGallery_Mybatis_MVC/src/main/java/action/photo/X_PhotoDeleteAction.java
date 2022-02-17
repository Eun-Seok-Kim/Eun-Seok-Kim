package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoDeleteActionSungListAction
 */
//@WebServlet("/photo/delete.do")
public class X_PhotoDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//데이터 가져오기
		String webPath = "/upload/";
		String absPath = request.getServletContext().getRealPath(webPath);
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		//p_idx로 값 불러오기
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		//vo를 통하여 filename 가져오기
		//실제데이터 삭제
		File f = new File(absPath,vo.getP_filename());
		f.delete();
		//DB에서 삭제
		int res = PhotoDao.getInstance().delete(p_idx);
		
		response.sendRedirect("list.do");
	}

}

