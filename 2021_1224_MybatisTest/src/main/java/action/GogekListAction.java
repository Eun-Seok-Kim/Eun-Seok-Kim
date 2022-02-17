package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GogekDao;
import vo.GogekVo;

/**
 * Servlet implementation class GogekListActionSungListAction
 */
@WebServlet("/gogek/list.do")
public class GogekListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<GogekVo> list = GogekDao.getInstance().selectList();
		//데이터 가져오기
		request.setAttribute("list", list);
		//request binding

		//forward
		String forward_page = "gogek_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

