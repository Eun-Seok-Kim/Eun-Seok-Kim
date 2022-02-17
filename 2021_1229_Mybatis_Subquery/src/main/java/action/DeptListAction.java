package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeptDao;
import vo.DeptVo;

/**
 * Servlet implementation class DeptListActionSungListAction
 */
@WebServlet("/dept/list.do")
public class DeptListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<DeptVo> list=DeptDao.getInstance().selectList();
		//데이터 가져오기
		
		//request binding
		request.setAttribute("list", list);
		//forward
		String forward_page = "dept_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

