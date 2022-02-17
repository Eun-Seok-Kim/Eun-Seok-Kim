package com.ic.auto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SawonDao;
import vo.SawonVo;

@Controller

public class SawonController {

	@Autowired
	SawonDao sawon_dao;
	
	@RequestMapping("/sawon/list.do")
	public String list(Model model) {
		
		List<SawonVo> list = sawon_dao.selectList();
		
		model.addAttribute("list",list);
		return "sawon/sawon_list";
	}
}
