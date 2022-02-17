package controller.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import annotation.ResponseBody;
import dao.VisitDao;
import vo.VisitVo;

public class VisitController {

	//��ü��� ��ȸ
	@RequestMapping("/visit/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		String search = request.getParameter ("search");		
		if(search==null) {
			search="all";
		}
		String search_text =request.getParameter("search_text");
		
		
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
		System.out.println(map);
		
		List<VisitVo> list = VisitDao.getInstance().selectList(map);
		
		//request binding
		request.setAttribute("list", list);
		
		return "visit_list.jsp";	
	}
	//insert��
	@RequestMapping("/visit/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {
		
		//return���� forward��ų ����
		return "visit_insert_form.jsp";
	}
	//insert
	@RequestMapping("/visit/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		//\r\n -> <br>�� ��ȯ���Ѽ� ����
		content = content.replaceAll("\r\n", "<br>");
	
		//2. vo��ü�� ����
		VisitVo vo = new VisitVo(name, content, pwd, ip);
	
		//3. DB insert
		int res = VisitDao.getInstance().insert(vo);
		//return���� forward��ų ����
		return "redirect:list.do";
	}
	//����
	@RequestMapping("/visit/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		//������ ��������
		int idx = Integer.parseInt(request.getParameter("idx"));
				//request binding
		int res =VisitDao.getInstance().delete(idx);
		//return���� forward��ų ����
		//FronController���� ���������� Redirect �ڵ� �ۼ�
		return "redirect:list.do";
	}
	//pwdüũ
	//@RequestMapping(value="/visit/check_pwd.do", produces="text/json:charset=utf-8;")
	@RequestMapping("/visit/check_pwd.do")
	@ResponseBody
	public String check_pwd(HttpServletRequest request, HttpServletResponse response) {
		//������ ��������
		int idx			= Integer.parseInt(request.getParameter("idx"));
		String c_pwd	= request.getParameter("c_pwd");
		//idx�� �ش�Ǵ� �Խù� ���� ������
		
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		//��й�ȣ ��
		boolean bSame = vo.getPwd().equals(c_pwd);
		
		//����� ��û�� ������ Ÿ������ ����(json){"result" : true}
		//										  {"result" : false}
		String json_result = String.format("{\"result\":%b}", bSame);
		//return���� forward��ų ����
		//FronController���� ���������� Redirect �ڵ� �ۼ�
		//@ResponseBody�پ� ������ ��������� �ٷ� ����
		return json_result;
	}
	//modify��
	@RequestMapping("/visit/modify_form.do")
	public String modify_form(HttpServletRequest request, HttpServletResponse response) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//������ ��������
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		String content = vo.getContent().replace("<br>", "\r\n");
		vo.setContent(content);
		//request binding
		request.setAttribute("vo", vo);
		//return���� forward��ų ����
		return "visit_modify_form.jsp";
	}
	//modify��
		@RequestMapping("/visit/modify.do")
		public String modify(HttpServletRequest request, HttpServletResponse response) {
			//������ ��������			
			int idx			= Integer.parseInt(request.getParameter("idx"));
			String name		= request.getParameter("name");
			//����� ���ÿ�                          ����Ǿ��� <br>�� �ٽ� �����ؼ� ����
			String content	= request.getParameter("content").replaceAll("\r\n", "<br>");
			String pwd		= request.getParameter("pwd");
			String ip		= request.getRemoteAddr();

			//request binding
			VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
	
			int res = VisitDao.getInstance().update(vo);
			//return���� forward��ų ����
			return "redirect:list.do";
		
		}
}
