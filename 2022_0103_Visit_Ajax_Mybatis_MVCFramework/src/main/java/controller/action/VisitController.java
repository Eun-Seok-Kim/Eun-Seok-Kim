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

	//전체목록 조회
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
	//insert폼
	@RequestMapping("/visit/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {
		
		//return값이 forward시킬 파일
		return "visit_insert_form.jsp";
	}
	//insert
	@RequestMapping("/visit/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		//\r\n -> <br>로 변환시켜서 저장
		content = content.replaceAll("\r\n", "<br>");
	
		//2. vo객체로 포장
		VisitVo vo = new VisitVo(name, content, pwd, ip);
	
		//3. DB insert
		int res = VisitDao.getInstance().insert(vo);
		//return값이 forward시킬 파일
		return "redirect:list.do";
	}
	//삭제
	@RequestMapping("/visit/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		//데이터 가져오기
		int idx = Integer.parseInt(request.getParameter("idx"));
				//request binding
		int res =VisitDao.getInstance().delete(idx);
		//return값이 forward시킬 파일
		//FronController에서 다음내용을 Redirect 코드 작성
		return "redirect:list.do";
	}
	//pwd체크
	//@RequestMapping(value="/visit/check_pwd.do", produces="text/json:charset=utf-8;")
	@RequestMapping("/visit/check_pwd.do")
	@ResponseBody
	public String check_pwd(HttpServletRequest request, HttpServletResponse response) {
		//데이터 가져오기
		int idx			= Integer.parseInt(request.getParameter("idx"));
		String c_pwd	= request.getParameter("c_pwd");
		//idx에 해당되는 게시물 정보 얻어오기
		
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		//비밀번호 비교
		boolean bSame = vo.getPwd().equals(c_pwd);
		
		//결과를 요청한 데이터 타입으로 포장(json){"result" : true}
		//										  {"result" : false}
		String json_result = String.format("{\"result\":%b}", bSame);
		//return값이 forward시킬 파일
		//FronController에서 다음내용을 Redirect 코드 작성
		//@ResponseBody붙어 있으면 결과데이터 바로 전송
		return json_result;
	}
	//modify폼
	@RequestMapping("/visit/modify_form.do")
	public String modify_form(HttpServletRequest request, HttpServletResponse response) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//데이터 가져오기
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		String content = vo.getContent().replace("<br>", "\r\n");
		vo.setContent(content);
		//request binding
		request.setAttribute("vo", vo);
		//return값이 forward시킬 파일
		return "visit_modify_form.jsp";
	}
	//modify폼
		@RequestMapping("/visit/modify.do")
		public String modify(HttpServletRequest request, HttpServletResponse response) {
			//데이터 가져오기			
			int idx			= Integer.parseInt(request.getParameter("idx"));
			String name		= request.getParameter("name");
			//저장과 동시에                          변경되었던 <br>을 다시 원복해서 저장
			String content	= request.getParameter("content").replaceAll("\r\n", "<br>");
			String pwd		= request.getParameter("pwd");
			String ip		= request.getRemoteAddr();

			//request binding
			VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
	
			int res = VisitDao.getInstance().update(vo);
			//return값이 forward시킬 파일
			return "redirect:list.do";
		
		}
}
