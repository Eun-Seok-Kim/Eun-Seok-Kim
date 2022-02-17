package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.VisitDao;
import vo.VisitVo;

@Controller
public class VisitController {
	
	//Auto-Detect�� �����ɰ�� @Autowired�� ��
	//���������� @Autowired�� �ȵȴ�
	//�������� �ڵ� ���� ������ �ڵ带 �־�д�. <context:annotation-config/>
	@Autowired
	HttpServletRequest request;
	
	
	
	//interface�� Injection
	VisitDao visit_dao;

	public void setVisit_dao(VisitDao visit_dao) {
		this.visit_dao = visit_dao;
	}
	
	//��ü�����ȸ(���Ǻ���ȸ ����)
	@RequestMapping("/visit/list.do")
	public String list(@RequestParam(value="search",defaultValue = "all") String search, 
					   @RequestParam(value="search_text",defaultValue = "")String search_text, Model model) {
		
		Map map = new HashMap();
		if(search.equals("all")==false) {
				
			if(search.equals("name_content")) {
				map.put("name", search_text);
				map.put("content", search_text);
				}
			else if(search.equals("name")) {
				map.put("name", search_text);
				}
			else if(search.equals("content")) {
				map.put("content", search_text);
				}
		}
		//�˻����Ǻ� ����Ʈ ��������
		
		List<VisitVo> list = visit_dao.selectList(map);
		
		//����������
		model.addAttribute("list",list);
		
		return "visit/visit_list";
	}
	//����� ����
	@RequestMapping("/visit/insert_form.do")
	public String insert_form() {
		
		return "visit/visit_insert_form";
	}
	//����ϱ�
	@RequestMapping("/visit/insert.do")
	public String insert(VisitVo vo) {
		
		String content = vo.getContent().replaceAll("\r\n", "<br>");
		vo.setContent(content);
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = visit_dao.insert(vo);
		
		//DispatcherServlet�� redirect:uri ����� ��ȯ
		//DS�� ���� uri�� response.sendRedirect("##");
		return "redirect:list.do";
	}
	@RequestMapping("/visit/delete.do")
	public String delete(int idx) {
		idx =Integer.parseInt(request.getParameter("idx"));
		int res = visit_dao.delete(idx);
		return "redirect:list.do";
	}
	
	@RequestMapping("/visit/check_pwd.do")
	@ResponseBody
	public String check_pwd(int idx, String c_pwd) {
		idx =Integer.parseInt(request.getParameter("idx"));
		c_pwd	= request.getParameter("c_pwd");
		VisitVo vo = visit_dao.selectOne(idx);		
		boolean bSame = vo.getPwd().equals(c_pwd);
		String json_result = String.format("{\"result\":%b}", bSame);		
		return json_result;
	}
	
	@RequestMapping("/visit/modify_form.do")
	public String modify_form(int idx,Model model) {
		idx =Integer.parseInt(request.getParameter("idx"));
		VisitVo vo = visit_dao.selectOne(idx);
		String content = vo.getContent().replaceAll("\r\n", "<br>");
		vo.setContent(content);
		model.addAttribute("vo",vo);
		return "visit/visit_modify_form";
	}
	
	@RequestMapping("/visit/modify.do")
	public String update(VisitVo vo) {
		String content = vo.getContent().replaceAll("\r\n", "<br>");
		String ip = request.getRemoteAddr();
		vo.setContent(content);
		vo.setIp(ip);
		int res = visit_dao.update(vo);
		return "redirect:list.do#vo_"+vo.getIdx();
	}
	
}
