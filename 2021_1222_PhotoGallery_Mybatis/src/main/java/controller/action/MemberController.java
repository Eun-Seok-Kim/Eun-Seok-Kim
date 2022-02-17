package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import annotation.RequestMapping;
import annotation.ResponseBody;
import dao.MemberDao;
import vo.MemberVo;

public class MemberController {

	public MemberController() {
		// TODO Auto-generated constructor stub
		System.out.println("Member");
	}
	@RequestMapping("/member/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
List<MemberVo>  list = MemberDao.getInstance().selectList();
		
		//request binding
        request.setAttribute("list", list);
        
		//forward
		return "member_list.jsp";
	}
	@RequestMapping("/member/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {
		
		return "member_insert_form.jsp";
	}
	@RequestMapping("/member/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		String m_name		=	request.getParameter("m_name");
		String m_id			= 	request.getParameter("m_id");
		String m_pwd		= 	request.getParameter("m_pwd");
		String m_zipcode	= 	request.getParameter("m_zipcode");
		String m_addr		= 	request.getParameter("m_addr");
		
		//2.ip구하기
		String m_ip			= 	request.getRemoteAddr();
		
		//3.포장
		MemberVo  vo = new MemberVo(m_name, m_id, m_pwd, m_zipcode, m_addr, m_ip, "일반");
		
		//4.DB Insert
		int res = MemberDao.getInstance().insert(vo);
		return "../photo/list.do";
	}
	@RequestMapping("/member/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		int m_idx = Integer.parseInt(request.getParameter("m_idx"));
		
		//2.DB delete
		int res = MemberDao.getInstance().delete(m_idx);
		return "redirect:list.do";
	}
	@RequestMapping("/member/modify_form.do")
	public String modify_form(HttpServletRequest request, HttpServletResponse response) {
	int m_idx  = Integer.parseInt(request.getParameter("m_idx"));
		
		//2.m_idx에 해당되는 Vo를 얻어온다
		MemberVo  vo = MemberDao.getInstance().selectOne(m_idx);
				
		//request binding
		request.setAttribute("vo", vo);
		return "member_modify_form.jsp";
	}
	@RequestMapping("/member/modify.do")
	public String modify(HttpServletRequest request, HttpServletResponse response) {
		int    m_idx	= Integer.parseInt(request.getParameter("m_idx"));
		String m_name	= request.getParameter("m_name");
		String m_id		= request.getParameter("m_id");
		String m_pwd	= request.getParameter("m_pwd");
		String m_zipcod = request.getParameter("m_zipcode");
		String m_addr	= request.getParameter("m_addr");
		String m_grade	= request.getParameter("m_grade");
		
		//2.ip얻기
		String m_ip		= request.getRemoteAddr();
		
		//3.포장
		MemberVo  vo = new MemberVo(m_idx, m_name, m_id, m_pwd, m_zipcod, m_addr, m_ip, m_grade);
		
		//4.DB update
		int res = MemberDao.getInstance().update(vo);
		return "redirect:list.do";
	}
	@RequestMapping("/member/login_form.do")
	public String login_form(HttpServletRequest request, HttpServletResponse response) {
		return "member_login_form.jsp";
	}
	@RequestMapping("/member/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String m_id  = request.getParameter("m_id");
		String m_pwd = request.getParameter("m_pwd");
		String url = request.getParameter("url");
		//2.m_id에 해당되는 Vo얻어온다
		MemberVo  user = MemberDao.getInstance().selectOne(m_id); 
		
		//아이디가 틀린경우
		if(user==null) {
			//GET(Query)을 이용한 SessionTracking
			return "redirect:login_form.do?reason=fail_id";
			
		}
		
		//비밀번호 틀린경우
		if(user.getM_pwd().equals(m_pwd)==false) {
			
			return "redirect:login_form.do?reason=fail_pwd";
			
		}
		
		//로그인 된경우->로그인정보를 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if(url.isEmpty()) { 
		//메인페이지 이동
			return "redirect:../product/list.do";
		}else {
		//url에 값이 있으면 들어온 url로 이동한다
			return "redirect:url";		
		}
		
	}
	@RequestMapping("/member/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session = request.getSession();
		
		//2.세션에서 user정보 지우기
		session.removeAttribute("user");
		return "redirect:../product/list.do";
	}
	@RequestMapping("/member/check_id.do")
	@ResponseBody
	public String check_id(HttpServletRequest request, HttpServletResponse response) {
		String m_id = request.getParameter("m_id");
		
		//2.m_id에 해당되는 객체가 존재하는지 여부
		MemberVo vo = MemberDao.getInstance().selectOne(m_id);
		
		boolean bResult = true; //사용가능하면 true 아니면 false
		
		//m_id가 이미 사용중이면...
		if(vo != null)
			bResult = false;
		
		//결과 전송
		//1.전송데이터 생성(JSON)
		String json_result = String.format("{\"result\":%b}", bResult);
		return json_result;
	}
	
}
