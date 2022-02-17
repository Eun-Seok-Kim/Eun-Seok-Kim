package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SawonDao;
import vo.SawonVo;

/**
 * Servlet implementation class SawonListActionSungListAction
 */
@WebServlet("/sawon/list.do")
public class SawonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /sawon/list.do
		// /sawon/list.d?daptno=10
		request.setCharacterEncoding("utf-8");
		String sajob;
		int deptno = 0;
		sajob = request.getParameter("sajob");
		if(sajob==null) {
			sajob="all";
		}
		
		try {
			deptno = Integer.parseInt(request.getParameter("deptno"));
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		List<SawonVo> list = null;
		
		//deptno=0이 전체 sajob 는 all 전체
		if(deptno==0 && sajob.equals("all")) {
		list = SawonDao.getInstance().selectList();
		}//전체
		else if(deptno!=0 && sajob.equals("all")){
			list = SawonDao.getInstance().selectListDeptno(deptno);				
		}//deptno 기준
		else if(deptno==0 && !sajob.equals("all")){
			list = SawonDao.getInstance().selectListSajob(sajob);				
		}//sajob기준
		
		else if(deptno!=0 && !sajob.equals("all")) {
			SawonVo vo = new SawonVo();
			vo.setDeptno(deptno);
			vo.setSajob(sajob);
			list = SawonDao.getInstance().selectListDeptnoSajob(vo);
		}//deptno 및 sajob
	
		request.setAttribute("list", list);
		//forward
		String forward_page = "sawon_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

