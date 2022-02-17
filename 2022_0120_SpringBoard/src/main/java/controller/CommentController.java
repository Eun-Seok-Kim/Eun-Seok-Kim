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

import dao.CommentDao;
import myutil.MyConstant;
import myutil.Paging;
import vo.CommentVo;

@Controller
public class CommentController {

	
	@Autowired
	HttpServletRequest request;
	
	
	CommentDao comment_dao;

	public void setComment_dao(CommentDao comment_dao) {
		this.comment_dao = comment_dao;
	}
	
	@RequestMapping("/comment/insert.do")
	@ResponseBody
	public Map insert(CommentVo vo) {
		
		//c_content:  "\n" -> "<br>" : ajax통해서 전달된 데이터는 \n전송
		String c_content = vo.getC_content().replaceAll("\n", "<br>");
		vo.setC_content(c_content);
		//ip구하기
		String c_ip = request.getRemoteAddr();
		vo.setC_ip(c_ip);
		
		Map map = new HashMap();
				
		int res;
		try {
			
			res = comment_dao.insert(vo);
			
			map.put("result", "success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "fail");
		}
		
		return map;
	}
	
	
	// //comment/delete.do?c_idx=3
	
	@RequestMapping("/comment/delete.do")
	@ResponseBody
	public Map delete(int c_idx,int b_idx) {
		
		Map map = new HashMap();
				
		int res;
		try {
			
			res = comment_dao.delete(c_idx);
			
			//totalPage구하기
			int rowTotal = comment_dao.selectRowTotal(b_idx);
			
			int totalPage = rowTotal / MyConstant.Comment.BLOCK_LIST;
			if(rowTotal%MyConstant.Comment.BLOCK_LIST !=0)
				totalPage++;
			
			map.put("result", "success");
			map.put("totalPage", totalPage);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "fail");
		}
		
		return map;
	}
	
			
	//  /comment/list.do?b_idx=5&page=1
	@RequestMapping("/comment/list.do")
	public String list(int b_idx, 
			           @RequestParam(value="page",defaultValue="1") int nowPage,
			           Model model) {
		
		int start = (nowPage-1) * MyConstant.Comment.BLOCK_LIST + 1;
		int end   = start + MyConstant.Comment.BLOCK_LIST - 1;
		
		//페이징 조건을 담을 맵
		Map map = new HashMap();
		map.put("b_idx", b_idx);
		map.put("start", start);
		map.put("end", end);
				
		List<CommentVo> list = comment_dao.selectList(map);
		
		//메뉴만들기
		
		//b_idx별 댓글수 구하기
		int rowTotal = comment_dao.selectRowTotal(b_idx);
		
		String pageMenu = Paging.getPaging( "board/list.do",
											nowPage, 
				                            rowTotal, 
				                            MyConstant.Comment.BLOCK_LIST,
				                            MyConstant.Comment.BLOCK_PAGE
				                            );
		//System.out.println(pageMenu);
		
					
		//결과적으로 request binding
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		return "board/comment_list";
	}
	
	
}








