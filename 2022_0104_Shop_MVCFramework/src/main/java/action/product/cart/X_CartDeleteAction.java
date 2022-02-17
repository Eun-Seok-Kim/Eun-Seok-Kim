package action.product.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;

/**
 * Servlet implementation class CartDeleteActionSungListAction
 */
//@WebServlet("/product/cart_delete.do")
public class X_CartDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		int res = CartDao.getInstance().delete(c_idx);
		//데이터 가져오기
		
		//request binding
		
		//forward
		response.sendRedirect("cart_list.do");
	}

}

