package action.product.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.CartDao;
import vo.CartVo;

/**
 * Servlet implementation class CartInsertActionSungListAction
 */
//@WebServlet("/product/cart_insert.do")
public class X_CartInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /product/cart_insert.do?p_idx=4&m_idx=5
		//1. 파라메타 받기
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		int m_idx = Integer.parseInt(request.getParameter("m_idx"));
		//2. CartVo 포장 : p_idx, m_idx
		CartVo paramVo = new CartVo();
		paramVo.setP_idx(p_idx);
		paramVo.setM_idx(m_idx);
		//3. p_idx,m_idx만족하는 객체정보 vo로 얻기(ONE,paramVo)
		CartVo vo = CartDao.getInstance().selectOne(paramVo);
		// select * from cart where p_idxt=#{p_idx} and where m_idx=#{m_idx}		
		//4-1. 얻어온 vo가 null이 아니면 등록된상태(exist)전송
		JSONObject json = new JSONObject();

		if(vo!=null) {
			json.put("result", "exist");		
		}else{
			int res = CartDao.getInstance().insert(paramVo);
			json.put("result", "success");
		}
		response.setContentType("text/json: charset=utf-8;");
		response.getWriter().print(json.toString());
	}	

}

