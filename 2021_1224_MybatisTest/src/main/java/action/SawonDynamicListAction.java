package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@WebServlet("/sawon/dynamic_list.do")
public class SawonDynamicListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String sajob;
		int deptno = 0;
		int year = 0;
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
		
		//검색 조건을 담을 Map 
		Map map = new HashMap();
		//deptno=0이 전체 sajob 는 all 전체
	/*	if(deptno!=0 && !sajob.equals("all")) {//부서,직급조회
			map.put("deptno", deptno);
			map.put("sajob", sajob);
		}*/
		if(deptno!=0){		//부서명조회
			map.put("deptno", deptno);
		}
		if(!sajob.equals("all")){	//직급별조회
			map.put("sajob", sajob);
		}
		if(year!=0) {
			map.put("year", year);
		}
		//Map으로 검색
		list = SawonDao.getInstance().selectList(map);
	
		request.setAttribute("list", list);
		//forward
		String forward_page = "sawon_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

