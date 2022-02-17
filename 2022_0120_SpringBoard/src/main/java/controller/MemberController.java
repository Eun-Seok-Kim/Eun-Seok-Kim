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

import dao.MemberDao;
import vo.MemberVo;

@Controller
public class MemberController {

	//�ش���Ʈ�ѷ� ������� 
	//-�ڵ����� : O
	//-�������� : X => ��ġ : <context:annotation-config/>
	@Autowired
	HttpSession session;
	
	
	@Autowired
	HttpServletRequest request;
	
	MemberDao member_dao;

	public void setMember_dao(MemberDao member_dao) {
		this.member_dao = member_dao;
	}
	
	//�α���������
	@RequestMapping("/member/login_form.do")
	public String login_form() {
		return "member/member_login_form";
	}
	
	//�α���ó��
	// /member/login.do?m_id=one&m_pwd=1234
	// /member/login.do?m_id=one&m_pwd=1234&url=
	// /member/login.do?m_id=one&m_pwd=1234&url=http://....
	
	@RequestMapping("/member/login.do")
	public String login(String m_id,String m_pwd,
			            @RequestParam(value="url",defaultValue="") String url,
			            Model model) {
		
		System.out.println(url);
		
		//1.m_id�� �ش�Ǵ� Vo���´�
		MemberVo user = member_dao.selectOne(m_id);
		
		//���̵� Ʋ�����
		if(user==null) {
			
			//model�� ���ؼ� DS���� ���޵� ������
			//1.view������ȯ�� : request binding�뵵
			//2.redirect:�� ����� ��ȯ�ÿ��� query�� ���
			//  response.sendRedirect("login_form.do?reason=fail_id");
			model.addAttribute("reason", "fail_id");
						
			return "redirect:login_form.do";
		}
		
		//��й�ȣ Ʋ�����
		if(user.getM_pwd().equals(m_pwd)==false) {
			
			//model���ؼ� ���޵� �����ʹ� query�� �̿�
			model.addAttribute("reason", "fail_pwd");
			
			return "redirect:login_form.do";
		}
		
		//�α��� �Ȱ��->�α��������� ���ǿ� ����
		session.setAttribute("user", user);
		
		if(url.isEmpty()) {
			
			return "redirect:../board/list.do";
		}
		
		
		return "redirect:" + url;
	}
	
	//�α׾ƿ�
	@RequestMapping("/member/logout.do")
	public String logout() {
		
		//session���� �α����� user���� ����
		session.removeAttribute("user");
		
		return "redirect:../board/list.do";
	}
	
	//id check
	@RequestMapping("/member/check_id.do")
	@ResponseBody
	public Map check_id(String m_id) {
		
		MemberVo vo = member_dao.selectOne(m_id);
		boolean bResult = true; //��밡���ϸ� true �ƴϸ� false
		
		//m_id�� �̹� ������̸�...
		if(vo != null)
			bResult = false;
		
		Map map = new HashMap();
		
		map.put("result", bResult);
		//Map->JSON��ȯ
		return map;
	}
	
	
	@RequestMapping("/member/list.do")
	public String list(Model model) {
		
		//ȸ����� ��������
		List<MemberVo>  list = member_dao.selectList();
		
		model.addAttribute("list", list);
				
		return "member/member_list";
	}
	
	//ȸ��������
	@RequestMapping("/member/insert_form.do")
	public String insert_form() {
		
	    return "member/member_insert_form";
	}
	
	//ȸ������
	@RequestMapping("/member/insert.do")
	public String insert(MemberVo vo) {
		
		//1.ip���ϱ�
		String m_ip			= 	request.getRemoteAddr();
		vo.setM_ip(m_ip);
		
		//ȸ�����
		vo.setM_grade("�Ϲ�");
				
		//DB Insert
		int res = member_dao.insert(vo);
				
	    return "redirect:list.do";
	}
	
	//ȸ������
	@RequestMapping("/member/delete.do")
	public String delete(int m_idx) {
	
		int res = member_dao.delete(m_idx);
		
		return "redirect:list.do";
	}
	
	
	//ȸ��������
	@RequestMapping("/member/modify_form.do")
	public String modify_form(int m_idx, Model model) {
		
		MemberVo vo = member_dao.selectOne(m_idx);
		
		model.addAttribute("vo", vo);
		
	    return "member/member_modify_form";
	}
		
	//ȸ������
	@RequestMapping("/member/modify.do")
	public String modify(MemberVo vo) {
		
		//1.ip���ϱ�
		String m_ip			= 	request.getRemoteAddr();
		vo.setM_ip(m_ip);
				
				
		//DB update
		int res = member_dao.update(vo);
				
	    return "redirect:list.do";
	}
		
	
	
	
}




