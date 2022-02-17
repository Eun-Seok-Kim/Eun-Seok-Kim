package action.product.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import vo.CartVo;

/**
 * Servlet implementation class CartUpdateActionSungListAction
 */
//@WebServlet("/product/cart_update.do")
public class X_CartUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		int c_cnt = Integer.parseInt(request.getParameter("c_cnt"));
		CartVo vo = new CartVo();
		vo.setC_idx(c_idx);
		vo.setC_cnt(c_cnt);
		int res = CartDao.getInstance().update(vo);

		response.sendRedirect("cart_list.do");
		
	}
}

