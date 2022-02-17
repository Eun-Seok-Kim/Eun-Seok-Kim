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
		
		//�μ������ dept_dao�� ��û
		List<DeptVo> list = dept_dao.selectList();
		
		//model�� ���ؼ� list�� DispatcherServlet���� ����
		model.addAttribute("list",list);
		
		return "dept/dept_list";
	}
	
}
