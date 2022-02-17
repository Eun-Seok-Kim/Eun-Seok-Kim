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

import dao.BoardDao;
import myutil.MyConstant;
import myutil.Paging;
import vo.BoardVo;
import vo.MemberVo;

@Controller
public class BoardController {

	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	
	
	BoardDao  board_dao;
	
	public void setBoard_dao(BoardDao board_dao) {
		this.board_dao = board_dao;
	}


	// /board/list.do
	// /board/list.do?search=name&search_text=길동
	// /board/list.do?page=1&search=name&search_text=길동
	// /board/list.do?page=2
    //  @RequestParam(name="page")
	//  @RequestParam(value="page") <-2중에 어떤옵션을 사용해도 상관없다
	@RequestMapping("/board/list.do")
	public String list(@RequestParam(name="page",       defaultValue="1")     int nowPage,  
			           @RequestParam(value="search",     defaultValue="all")   String search, 
			           @RequestParam(value="search_text",defaultValue="")      String search_text,
			           Model model) {
		
		//System.out.println(nowPage);
		
		//게시물에서 가져올 범위 계산
		int start = (nowPage-1) * MyConstant.Board.BLOCK_LIST + 1;
		int end   = start + MyConstant.Board.BLOCK_LIST - 1;
		
		//검색범위 및 조건을 담을 객체
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		
		//검색조건을 map에 담는다
		if(!search.equals("all")) {//전체검색이 아니면
			
			if(search.equals("name_subject_content")) {
				//이름+제목+내용
				map.put("name", search_text);
				map.put("subject", search_text);
				map.put("content", search_text);
			}else if(search.equals("name")) {
				//이름검색
				map.put("name", search_text);
				
			}else if(search.equals("subject")) {
				//제목검색
				map.put("subject", search_text);
				
			}else if(search.equals("content")) {
				//내용검색
				map.put("content", search_text);
				
			}
		}
		
		
		
		
		//전체게시물수
		int rowTotal = board_dao.selectRowTotal(map);
				
		//페이징 메뉴 생성
		//검색필터
		String search_filter = String.format("search=%s&search_text=%s", search,search_text);
		
		String pageMenu = Paging.getPaging("list.do", 
				                            search_filter,
				                            nowPage, 
				                            rowTotal, 
				                            MyConstant.Board.BLOCK_LIST,
				                            MyConstant.Board.BLOCK_PAGE
				                            );
		
		//System.out.println(pageMenu);
		
		
		//게시글 목록가져오기
		List<BoardVo> list = board_dao.selectList(map);
		
		//session에 봤다는 정보는 삭제
		session.removeAttribute("show");
		
		//model통해서 request binding
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		return "board/board_list";
	}
	
	//  /board/view.do?b_idx=1
	@RequestMapping("/board/view.do")
	public String view(int b_idx,Model model) {
		
		BoardVo  vo = board_dao.selectOne(b_idx);
		
		//현재 게시글을 봤냐 라는정보를 세션에서 검사
		if(session.getAttribute("show")==null) {
		
			//조회수 증가
			int res = board_dao.update_readhit(b_idx);
			
			//session에 봤다는 정보 기록
			session.setAttribute("show", true);
		}
		
		model.addAttribute("vo", vo);
		
		return "board/board_view";
	}
	
	//글쓰기 폼
	@RequestMapping("/board/insert_form.do")
	public String insert_form() {
		
		return "board/board_insert_form";
	}
	
	//새글쓰기
	
	// /board/insert.do?b_subject=제목&b_content=내용
	
	@RequestMapping("/board/insert.do")
	public String insert(BoardVo vo,Model model) {
		
		//로그인된 유저 정보 얻어오기
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user==null) {
			
			model.addAttribute("reason", "session_timeout");
			
			return "redirect:list.do";
		}
		
		//회원idx와 회원명을 vo에 넣는다
		vo.setM_idx(user.getM_idx());
		vo.setM_name(user.getM_name());
		
		
		//IP구하기
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
		
		//DB insert
		int res = board_dao.insert(vo);
		
		
		return "redirect:list.do";
	}
	
	
	//답글쓰기 폼
	// /board/reply_form.do?b_idx=5
	@RequestMapping("/board/reply_form.do")
	public String reply_form() {
		
		return "board/board_reply_form";
	}
	
	
	//답글쓰기
	// /board/reply.do?b_idx=5&b_subject=제목&b_content=내용
	
	@RequestMapping("/board/reply.do")
	public String reply(BoardVo vo,int page,Model model) {
		
		//로그인된 유저 정보 얻어오기
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user==null) {
			
			model.addAttribute("reason", "session_timeout");
			
			return "redirect:list.do";
		}
		
		//회원idx와 회원명을 vo에 넣는다
		vo.setM_idx(user.getM_idx());
		vo.setM_name(user.getM_name());
		
		
		//IP구하기
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
			
		
		//b_ref b_step  b_depth 설정
		
		
		//답글쓰기의 기준글 정보 얻어온다
		BoardVo baseVo = board_dao.selectOne(vo.getB_idx());
		
		//새로운 답글이 들어갈 수 있도록 기준글보다 b_step이 큰게시물들은 b_step = b_step + 1 해야 한다
		int res = board_dao.update_step(baseVo);
		
		//답글이 들어갈 정보 셋팅(기준글 바로 아래들어올수 있도록 설정)
		vo.setB_ref(baseVo.getB_ref());
		vo.setB_step(baseVo.getB_step()+1);
		vo.setB_depth(baseVo.getB_depth()+1);
		
		//DB reply(답글쓰기)
		res = board_dao.reply(vo);
		
		
		//model
		model.addAttribute("page", page);
		
		return "redirect:list.do";
	}
	
	
	//삭제하기
	// /board/delete.do?b_idx=5&page=3&search=all&search_text=
	@RequestMapping("/board/delete.do")
	public String delete(int b_idx,
			              int page,
			              String search,
			              String search_text,
			              Model model) {
		
		int res = board_dao.update_use_yn(b_idx);
		
		//어떤용도? query로 사용
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("search_text",search_text);
		
		return "redirect:list.do"; //   list.do?page=3&search=all&search_text=
	}
	
	
	//수정폼 띄우기
	// /board/modify_form.do?b_idx=5
	@RequestMapping("/board/modify_form.do")
	public String modify_form(int b_idx,Model model) {
		
		//수정할 게시물 얻어오기
		BoardVo  vo = board_dao.selectOne(b_idx);
		
		//model통해서 request binding
		model.addAttribute("vo", vo);
		
		return "board/board_modify_form";
	}
	
	//수정하기
	@RequestMapping("/board/modify.do")
	public String modify(BoardVo vo,
						  int page,
				          String search,
				          String search_text,
			              Model model) {
		
		//로그인된 유저 정보 얻어오기
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user==null) {
			
			model.addAttribute("reason", "session_timeout");
			model.addAttribute("page", page);
			model.addAttribute("search", search);
			model.addAttribute("search_text",search_text);
			
			return "redirect:list.do";
		}
		
		
		//IP구하기
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
		
		//DB update
		int res = board_dao.update(vo);
		
		model.addAttribute("b_idx", vo.getB_idx());
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("search_text",search_text);
		
		
		return "redirect:view.do";
	}
	
	
	
	
}
