package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SungTBDao;
import vo.SungVo;

/**
 * Servlet implementation class SungModifyFormActionSungListAction
 */
@WebServlet("/sung/modify_form.do")
public class SungModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//1. parameter 받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//2.idx에 해당되는 VO객체 1개 얻어오기		
		SungVo vo = SungTBDao.getInstance().selectOne(idx);
		
		//request binding
		request.setAttribute("vo", vo);
		
		String forward_page = "sung_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

