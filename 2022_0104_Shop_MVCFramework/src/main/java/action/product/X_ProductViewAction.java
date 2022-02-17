package action.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import vo.ProductVo;

/**
 * Servlet implementation class ProductViewActionSungListAction
 */
//@WebServlet("/product/view.do")
public class X_ProductViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub	
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		ProductVo vo= ProductDao.getInstance().selectOne(p_idx);
		
		//데이터 가져오기

		//request binding
		request.setAttribute("vo", vo);
		//forward
		String forward_page = "product_content.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

