package controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.TestService;

@Controller
public class TestController {

	TestService test_service;

	public void setTest_service(TestService test_service) {
		this.test_service = test_service;
	}
	
	@RequestMapping("/test.do")
	@ResponseBody
	public String test() {
		test_service.test();
		
		return "test() call";
	}
	@RequestMapping("/list.do")
	public String list(Model model) {
		
		Map map = test_service.selectlist();
		
		model.addAttribute("map",map);
		
		return "test_list";  //test_list.jsp
	}
	
}
