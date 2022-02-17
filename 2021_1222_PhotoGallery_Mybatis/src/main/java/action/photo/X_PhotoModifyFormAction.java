package action.photo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoModifyFormActionSungListAction
 */
//@WebServlet("/photo/modify_form.do")
public class X_PhotoModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));	
		//데이터 가져오기
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		//request binding
		//p_content : "<br>" > \r\n변경
		String p_content = vo.getP_content().replaceAll("<br>", "\n\r");
		vo.setP_content(p_content);
		request.setAttribute("vo", vo);
		//forward
		String forward_page = "photo_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

