package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import annotation.RequestMapping;
import annotation.ResponseBody;
import dao.CartDao;
import vo.CartVo;
import vo.MemberVo;

public class CartController {

		public CartController() {
			// TODO Auto-generated constructor stub
			System.out.println("Cart");
		}
		
	@RequestMapping("/product/cart_list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			return "redirect:list.do?reason=logout";
		}
		//로그인한 유저의 m_idx구하기
		int m_idx = user.getM_idx();
		List<CartVo> list =CartDao.getInstance().selectList(m_idx);
		//데이터 가져오기
		//request binding
		int total_amount = CartDao.getInstance().selectTotalAmount(m_idx);
		
		request.setAttribute("list", list);
		request.setAttribute("total_amount", total_amount);
		return "cart_list.jsp";
	}
	
	@RequestMapping("/product/cart_insert.do")
	@ResponseBody
	public String insert(HttpServletRequest request, HttpServletResponse response) {
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
			return json.toString();
	}
	@RequestMapping("/product/cart_delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		int res = CartDao.getInstance().delete(c_idx);
		
		return "redirect:cart_list.do";
	}
	@RequestMapping("/product/cart_update.do")
	public String update(HttpServletRequest request, HttpServletResponse response) {
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		int c_cnt = Integer.parseInt(request.getParameter("c_cnt"));
		CartVo vo = new CartVo();
		vo.setC_idx(c_idx);
		vo.setC_cnt(c_cnt);
		int res = CartDao.getInstance().update(vo);
		return "redirect:cart_list.do";
	}
}
