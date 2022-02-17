package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DeptDao;
import vo.DeptVo;

@Controller
public class DeptController {

	DeptDao dept_dao;

	public DeptController(DeptDao dept_dao) {
		super();
		this.dept_dao = dept_dao;
	}
	
	
	@RequestMapping("/dept/list.do")
	public String list(Model model) {
		
		//부서목록을 dept_dao에 요청
		List<DeptVo> list = dept_dao.selectList();
		
		//model을 통해서 list을 DispatcherServlet에게 전달
		model.addAttribute("list",list);
		
		return "dept/dept_list";
	}
	
}
