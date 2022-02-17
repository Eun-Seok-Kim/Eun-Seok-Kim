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
	// /board/list.do?search=name&search_text=�浿
	// /board/list.do?page=1&search=name&search_text=�浿
	// /board/list.do?page=2
    //  @RequestParam(name="page")
	//  @RequestParam(value="page") <-2�߿� ��ɼ��� ����ص� �������
	@RequestMapping("/board/list.do")
	public String list(@RequestParam(name="page",       defaultValue="1")     int nowPage,  
			           @RequestParam(value="search",     defaultValue="all")   String search, 
			           @RequestParam(value="search_text",defaultValue="")      String search_text,
			           Model model) {
		
		//System.out.println(nowPage);
		
		//�Խù����� ������ ���� ���
		int start = (nowPage-1) * MyConstant.Board.BLOCK_LIST + 1;
		int end   = start + MyConstant.Board.BLOCK_LIST - 1;
		
		//�˻����� �� ������ ���� ��ü
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		
		//�˻������� map�� ��´�
		if(!search.equals("all")) {//��ü�˻��� �ƴϸ�
			
			if(search.equals("name_subject_content")) {
				//�̸�+����+����
				map.put("name", search_text);
				map.put("subject", search_text);
				map.put("content", search_text);
			}else if(search.equals("name")) {
				//�̸��˻�
				map.put("name", search_text);
				
			}else if(search.equals("subject")) {
				//����˻�
				map.put("subject", search_text);
				
			}else if(search.equals("content")) {
				//����˻�
				map.put("content", search_text);
				
			}
		}
		
		
		
		
		//��ü�Խù���
		int rowTotal = board_dao.selectRowTotal(map);
				
		//����¡ �޴� ����
		//�˻�����
		String search_filter = String.format("search=%s&search_text=%s", search,search_text);
		
		String pageMenu = Paging.getPaging("list.do", 
				                            search_filter,
				                            nowPage, 
				                            rowTotal, 
				                            MyConstant.Board.BLOCK_LIST,
				                            MyConstant.Board.BLOCK_PAGE
				                            );
		
		//System.out.println(pageMenu);
		
		
		//�Խñ� ��ϰ�������
		List<BoardVo> list = board_dao.selectList(map);
		
		//session�� �ôٴ� ������ ����
		session.removeAttribute("show");
		
		//model���ؼ� request binding
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		return "board/board_list";
	}
	
	//  /board/view.do?b_idx=1
	@RequestMapping("/board/view.do")
	public String view(int b_idx,Model model) {
		
		BoardVo  vo = board_dao.selectOne(b_idx);
		
		//���� �Խñ��� �ó� ��������� ���ǿ��� �˻�
		if(session.getAttribute("show")==null) {
		
			//��ȸ�� ����
			int res = board_dao.update_readhit(b_idx);
			
			//session�� �ôٴ� ���� ���
			session.setAttribute("show", true);
		}
		
		model.addAttribute("vo", vo);
		
		return "board/board_view";
	}
	
	//�۾��� ��
	@RequestMapping("/board/insert_form.do")
	public String insert_form() {
		
		return "board/board_insert_form";
	}
	
	//���۾���
	
	// /board/insert.do?b_subject=����&b_content=����
	
	@RequestMapping("/board/insert.do")
	public String insert(BoardVo vo,Model model) {
		
		//�α��ε� ���� ���� ������
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user==null) {
			
			model.addAttribute("reason", "session_timeout");
			
			return "redirect:list.do";
		}
		
		//ȸ��idx�� ȸ������ vo�� �ִ´�
		vo.setM_idx(user.getM_idx());
		vo.setM_name(user.getM_name());
		
		
		//IP���ϱ�
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
		
		//DB insert
		int res = board_dao.insert(vo);
		
		
		return "redirect:list.do";
	}
	
	
	//��۾��� ��
	// /board/reply_form.do?b_idx=5
	@RequestMapping("/board/reply_form.do")
	public String reply_form() {
		
		return "board/board_reply_form";
	}
	
	
	//��۾���
	// /board/reply.do?b_idx=5&b_subject=����&b_content=����
	
	@RequestMapping("/board/reply.do")
	public String reply(BoardVo vo,int page,Model model) {
		
		//�α��ε� ���� ���� ������
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user==null) {
			
			model.addAttribute("reason", "session_timeout");
			
			return "redirect:list.do";
		}
		
		//ȸ��idx�� ȸ������ vo�� �ִ´�
		vo.setM_idx(user.getM_idx());
		vo.setM_name(user.getM_name());
		
		
		//IP���ϱ�
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
			
		
		//b_ref b_step  b_depth ����
		
		
		//��۾����� ���ر� ���� ���´�
		BoardVo baseVo = board_dao.selectOne(vo.getB_idx());
		
		//���ο� ����� �� �� �ֵ��� ���رۺ��� b_step�� ū�Խù����� b_step = b_step + 1 �ؾ� �Ѵ�
		int res = board_dao.update_step(baseVo);
		
		//����� �� ���� ����(���ر� �ٷ� �Ʒ����ü� �ֵ��� ����)
		vo.setB_ref(baseVo.getB_ref());
		vo.setB_step(baseVo.getB_step()+1);
		vo.setB_depth(baseVo.getB_depth()+1);
		
		//DB reply(��۾���)
		res = board_dao.reply(vo);
		
		
		//model
		model.addAttribute("page", page);
		
		return "redirect:list.do";
	}
	
	
	//�����ϱ�
	// /board/delete.do?b_idx=5&page=3&search=all&search_text=
	@RequestMapping("/board/delete.do")
	public String delete(int b_idx,
			              int page,
			              String search,
			              String search_text,
			              Model model) {
		
		int res = board_dao.update_use_yn(b_idx);
		
		//��뵵? query�� ���
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("search_text",search_text);
		
		return "redirect:list.do"; //   list.do?page=3&search=all&search_text=
	}
	
	
	//������ ����
	// /board/modify_form.do?b_idx=5
	@RequestMapping("/board/modify_form.do")
	public String modify_form(int b_idx,Model model) {
		
		//������ �Խù� ������
		BoardVo  vo = board_dao.selectOne(b_idx);
		
		//model���ؼ� request binding
		model.addAttribute("vo", vo);
		
		return "board/board_modify_form";
	}
	
	//�����ϱ�
	@RequestMapping("/board/modify.do")
	public String modify(BoardVo vo,
						  int page,
				          String search,
				          String search_text,
			              Model model) {
		
		//�α��ε� ���� ���� ������
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user==null) {
			
			model.addAttribute("reason", "session_timeout");
			model.addAttribute("page", page);
			model.addAttribute("search", search);
			model.addAttribute("search_text",search_text);
			
			return "redirect:list.do";
		}
		
		
		//IP���ϱ�
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
