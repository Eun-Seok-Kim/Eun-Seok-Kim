package com.ic.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	public TestController() {
		// TODO Auto-generated constructor stub
	System.out.println("testController");
	}
	
	@RequestMapping("/test.do")
	public String test(Model model) {
		String msg ="���� ���ؼ� ����";
		
		model.addAttribute("msg",msg);
		return "test";
	}
	
	
	@RequestMapping("/test2.do")
	public ModelAndView test2() {
		String  msg = "HI";
		//Data + View �� ���Խ�Ų ��ü
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg",msg);
		mv.addObject("info","ModelAndView��� ��ü�� ���� ������ ����");
		
		//View �ֱ�
		
		mv.setViewName("test2");
		return mv;
	}
}
