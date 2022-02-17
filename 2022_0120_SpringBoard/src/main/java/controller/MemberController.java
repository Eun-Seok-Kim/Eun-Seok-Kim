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

	//해당컨트롤러 생성방법 
	//-자동생성 : O
	//-수동생성 : X => 조치 : <context:annotation-config/>
	@Autowired
	HttpSession session;
	
	
	@Autowired
	HttpServletRequest request;
	
	MemberDao member_dao;

	public void setMember_dao(MemberDao member_dao) {
		this.member_dao = member_dao;
	}
	
	//로그인폼띄우기
	@RequestMapping("/member/login_form.do")
	public String login_form() {
		return "member/member_login_form";
	}
	
	//로그인처리
	// /member/login.do?m_id=one&m_pwd=1234
	// /member/login.do?m_id=one&m_pwd=1234&url=
	// /member/login.do?m_id=one&m_pwd=1234&url=http://....
	
	@RequestMapping("/member/login.do")
	public String login(String m_id,String m_pwd,
			            @RequestParam(value="url",defaultValue="") String url,
			            Model model) {
		
		System.out.println(url);
		
		//1.m_id에 해당되는 Vo얻어온다
		MemberVo user = member_dao.selectOne(m_id);
		
		//아이디가 틀린경우
		if(user==null) {
			
			//model을 통해서 DS에게 전달된 데이터
			//1.view정보반환시 : request binding용도
			//2.redirect:로 결과값 반환시에는 query로 사용
			//  response.sendRedirect("login_form.do?reason=fail_id");
			model.addAttribute("reason", "fail_id");
						
			return "redirect:login_form.do";
		}
		
		//비밀번호 틀린경우
		if(user.getM_pwd().equals(m_pwd)==false) {
			
			//model통해서 전달된 데이터는 query로 이용
			model.addAttribute("reason", "fail_pwd");
			
			return "redirect:login_form.do";
		}
		
		//로그인 된경우->로그인정보를 세션에 저장
		session.setAttribute("user", user);
		
		if(url.isEmpty()) {
			
			return "redirect:../board/list.do";
		}
		
		
		return "redirect:" + url;
	}
	
	//로그아웃
	@RequestMapping("/member/logout.do")
	public String logout() {
		
		//session에서 로그인한 user정보 삭제
		session.removeAttribute("user");
		
		return "redirect:../board/list.do";
	}
	
	//id check
	@RequestMapping("/member/check_id.do")
	@ResponseBody
	public Map check_id(String m_id) {
		
		MemberVo vo = member_dao.selectOne(m_id);
		boolean bResult = true; //사용가능하면 true 아니면 false
		
		//m_id가 이미 사용중이면...
		if(vo != null)
			bResult = false;
		
		Map map = new HashMap();
		
		map.put("result", bResult);
		//Map->JSON변환
		return map;
	}
	
	
	@RequestMapping("/member/list.do")
	public String list(Model model) {
		
		//회원목록 가져오기
		List<MemberVo>  list = member_dao.selectList();
		
		model.addAttribute("list", list);
				
		return "member/member_list";
	}
	
	//회원가입폼
	@RequestMapping("/member/insert_form.do")
	public String insert_form() {
		
	    return "member/member_insert_form";
	}
	
	//회원가입
	@RequestMapping("/member/insert.do")
	public String insert(MemberVo vo) {
		
		//1.ip구하기
		String m_ip			= 	request.getRemoteAddr();
		vo.setM_ip(m_ip);
		
		//회원등급
		vo.setM_grade("일반");
				
		//DB Insert
		int res = member_dao.insert(vo);
				
	    return "redirect:list.do";
	}
	
	//회원삭제
	@RequestMapping("/member/delete.do")
	public String delete(int m_idx) {
	
		int res = member_dao.delete(m_idx);
		
		return "redirect:list.do";
	}
	
	
	//회원수정폼
	@RequestMapping("/member/modify_form.do")
	public String modify_form(int m_idx, Model model) {
		
		MemberVo vo = member_dao.selectOne(m_idx);
		
		model.addAttribute("vo", vo);
		
	    return "member/member_modify_form";
	}
		
	//회원수정
	@RequestMapping("/member/modify.do")
	public String modify(MemberVo vo) {
		
		//1.ip구하기
		String m_ip			= 	request.getRemoteAddr();
		vo.setM_ip(m_ip);
				
				
		//DB update
		int res = member_dao.update(vo);
				
	    return "redirect:list.do";
	}
		
	
	
	
}




