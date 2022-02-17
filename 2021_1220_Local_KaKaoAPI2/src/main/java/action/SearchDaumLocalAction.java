package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DaumSearchUtils;
import vo.DaumLocalVo;

/**
 * Servlet implementation class SearchDaumLocalAction
 */
@WebServlet("/daum_search.do")
public class SearchDaumLocalAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//현재 자신의 위치정보 얻기
		String s_lat = request.getParameter("latitude");
		String s_lon = request.getParameter("longitude");
		String search = request.getParameter("search");
		double latitude = 0,longitude=0;
		
		int page = 1; //min=1 max=45
		int size = 10;//min=1 max=15
		
		
		List<DaumLocalVo> list = null;
		//검색반경
		int range = 1000;
		
		if (search != null && !search.isEmpty()  && !s_lat.isEmpty() && !s_lon.isEmpty() ){
			latitude =  Double.parseDouble(s_lat);
			longitude = Double.parseDouble(s_lon);
			
			try {
				list = DaumSearchUtils.getLocalListFromXml(search, latitude, longitude, range,page,size);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//System.out.println(list.size());
		//request binding
		request.setAttribute("list", list);
		
		
		//dispatcher(forward)
		String forward_page = "daum_local_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}
}