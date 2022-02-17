package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MySearchUtil;
import vo.ProductVo;

/**
 * Servlet implementation class ProductListActionSungListAction
 */
@WebServlet("/product/list.do")
public class ProductListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String p_name = "노트북";
		//int start =1;
		//int display = 10;
		request.setCharacterEncoding("utf-8");
		String p_name = request.getParameter("p_name");
		int start = Integer.parseInt(request.getParameter("start"));
		int display = Integer.parseInt(request.getParameter("display"));
		
		
		List<ProductVo> list = MySearchUtil.search_shop(p_name, start, display);
		/*
		 * for(ProductVo vo : list) { System.out.println(vo.getTitle()); }
		 */
		//데이터 가져오기

		//request binding
		request.setAttribute("list", list);
		//forward
		String forward_page = "product_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

