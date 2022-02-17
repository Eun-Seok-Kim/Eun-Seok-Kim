package action.product.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import vo.CartVo;
import vo.MemberVo;

/**
 * Servlet implementation class CartListActionSungListAction
 */
//@WebServlet("/product/cart_list.do")
public class X_CartListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//장바구니 목록 조회
		HttpSession session = request.getSession();
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			response.sendRedirect("list.do?reason=logout");
			return;
		}
		//로그인한 유저의 m_idx구하기
		int m_idx = user.getM_idx();
		List<CartVo> list =CartDao.getInstance().selectList(m_idx);
		//데이터 가져오기
		//request binding
		int total_amount = CartDao.getInstance().selectTotalAmount(m_idx);
		
		request.setAttribute("list", list);
		request.setAttribute("total_amount", total_amount);
		
		//forward
		String forward_page = "cart_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

