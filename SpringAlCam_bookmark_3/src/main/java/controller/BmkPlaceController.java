package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.BmkPlaceDao;
import vo.BmkPlaceVo;
import vo.MemberVo;


@Controller
public class BmkPlaceController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	BmkPlaceDao bmkplace_dao;

	public void setBmkplace_dao(BmkPlaceDao bmkplace_dao) {
		this.bmkplace_dao = bmkplace_dao;
	}
	
	//장소즐겨찾기 조회
	@RequestMapping("/member/bmkplace_list.do")
	public String list(Model model) {
	
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			model.addAttribute("reason","logout");
		
			return "rdirect:../main.do";
		}
		
		//회원일련번호 구하기
		int m_idx = user.getM_idx();
		
		
		//장바구니 목록 조회
		List<BmkPlaceVo> list = bmkplace_dao.selectList(m_idx);

		//request binding
		model.addAttribute("list",list);
			
		return "member/bookmark_place";
	}
	
	//장소즐겨찾기 추가
	@RequestMapping("/member/bmkplace_insert.do")
	@ResponseBody
	public Map bmkplace_insert(BmkPlaceVo paramVo) {
		
		BmkPlaceVo vo = bmkplace_dao.selectOne(paramVo);
		
		Map map = new HashMap();
		
		if(vo!=null) {
			
			map.put("result", "exist");
		}else {
			
			int res = bmkplace_dao.insert(paramVo);
			map.put("result", "success");
		}
		
		return map;
	}
	
	//장소즐겨찾기 삭제
	@RequestMapping("/member/bmkplace_delete.do")
	public String bmkplace_delete(int bmk_p_idx) {

		int res   = bmkplace_dao.delete(bmk_p_idx);
		
		return "redirect:bmkplace_list.do";
	}
	
	//장소즐겨찾기 선택 삭제
	@RequestMapping("/member/bmkplace_del_all.do")
	public String bmkplace_del_all(@RequestParam(value="check_box") String [] bmk_p_idx_array) {

		for(String str_bmk_p_idx : bmk_p_idx_array) {
			int bmk_p_idx = Integer.parseInt(str_bmk_p_idx);
			
			int res = bmkplace_dao.delete(bmk_p_idx);
		}
		
		return "redirect:bmkplace_list.do";
	}
	
	

}
