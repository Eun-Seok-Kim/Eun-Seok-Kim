package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.GogekDao;
import vo.GogekVo;

@Controller
public class GogekController {

	GogekDao gogek_dao;

	public GogekController(GogekDao gogek_dao) {
		super();
		this.gogek_dao = gogek_dao;
	}
	
	
	@RequestMapping("/gogek/list.do")
	public String list(Model model) {
		List<GogekVo> list = gogek_dao.selectList();
		
		
		model.addAttribute("list",list);
		
		return "gogek/gogek_list";
	}
	
}
