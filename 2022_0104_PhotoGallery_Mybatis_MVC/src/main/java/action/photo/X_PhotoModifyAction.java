package action.photo;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoModifyActionSungListAction
 */
//@WebServlet("/photo/modify.do")
public class X_PhotoModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//데이터 가져오기
		String p_subject = request.getParameter("p_subject");
		String p_content = request.getParameter("p_content").replaceAll("\n\r","<br>");
		String p_ip = request.getRemoteAddr();
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		//request binding
		PhotoVo vo = new PhotoVo(p_idx, p_subject, p_content, p_ip);
		//forward
		int res = PhotoDao.getInstance().update(vo);
		response.sendRedirect("list.do");
	}

}

