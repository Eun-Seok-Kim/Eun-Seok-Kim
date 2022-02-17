package controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DeptDao;
import vo.DeptVo;

@Controller
public class DeptController {

	//자동연결
	@Autowired
	DeptDao dept_dao;
	
	@Autowired
	HttpServletRequest requese;
	@Autowired
	HttpSession session;
	@Autowired
	ServletContext application;
	
	@RequestMapping("/dept/list.do")
	public String list(Model model) {
		
		List<DeptVo> list = dept_dao.selectList();

		model.addAttribute("list",list);
		return "dept/dept_list";
	}
}
