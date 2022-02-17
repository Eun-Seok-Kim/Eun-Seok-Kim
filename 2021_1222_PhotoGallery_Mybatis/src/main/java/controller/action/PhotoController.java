package controller.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import annotation.RequestMapping;
import annotation.ResponseBody;
import dao.PhotoDao;
import vo.PhotoVo;

public class PhotoController {

	
	@RequestMapping("/photo/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		List<PhotoVo> list = PhotoDao.getInstance().selectList();
		
		//request binding
		request.setAttribute("list", list);

		//forward
		return "photo_list.jsp";
		
	}
	@RequestMapping("/photo/photo_one.do")
	@ResponseBody
	public String one(HttpServletRequest request, HttpServletResponse response) {
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		//p_idx에 해당되는 객체 1건 구하기
		PhotoVo  vo = PhotoDao.getInstance().selectOne(p_idx);
		
		
		
		//JSON생성하기
		JSONObject json = new JSONObject();
		
		if(vo==null) {
			json.put("p_idx", 0);
		}else {
		
			json.put("p_idx", vo.getP_idx());
			json.put("p_subject", vo.getP_subject());
			json.put("p_content", vo.getP_content());
			json.put("p_filename", vo.getP_filename());
			json.put("p_ip", vo.getP_ip());
			json.put("p_regdate", vo.getP_regdate());
			json.put("p_modifydate", vo.getP_modifydate());
			json.put("m_idx", vo.getM_idx());
		}
		return json.toString();
	}
	
	@RequestMapping("/photo/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {
		return "photo_insert_form.jsp";
		
	}
	@RequestMapping("/photo/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			
			//Session Tracking: GET(Query방식)
			return "redirect:list.do?reason=logout";
			
			
		}
		
		
		//웹상경로
		String webPath = "/upload/";
		
		//웹경로->절대경로
		ServletContext application =  request.getServletContext();
		String saveDir = application.getRealPath(webPath);
		int    maxSize = 1024*1024 * 100;  // 100MB
		
		MultipartRequest mr = new MultipartRequest(
				                                request,   //위임
				                                saveDir,   //저장위치
				                                maxSize,   //저장최대크기(byte단위)
				                                "utf-8",   //수신인코딩
				                                new DefaultFileRenamePolicy());
		
		
		//업로드된 화일정보 구하기
		String p_filename = "no_file";
		
		//방법1)
		File  f = mr.getFile("photo");
		if(f != null) {
			//업로드화일이 존재하면
			p_filename = f.getName(); //업로드된 화일명을 구한다
		}
		
		//(주의)업로드화일외 나머지 파라메터는 어떻게 받아야되냐?
		//: mr을 통해서 받아야 된다(request로부터 위임받았기 때문에)
		String p_subject = mr.getParameter("p_subject");//(O)
		String p_content = mr.getParameter("p_content").replaceAll("\r\n", "<br>");//(O)
		
		//단 ip정보는 request을 이용
		String p_ip = request.getRemoteAddr();
		
		int m_idx = Integer.parseInt(mr.getParameter("m_idx"));
		
		//포장
		PhotoVo  vo = new PhotoVo(p_subject, p_content, p_filename, p_ip, m_idx);
	
		//DB insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//목록보기
		return "redirect:list.do";		
	}
	@RequestMapping("/photo/modify_form.do")
	public String modift_form(HttpServletRequest request, HttpServletResponse response) {
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));	
		//데이터 가져오기
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		//request binding
		//p_content : "<br>" > \r\n변경
		String p_content = vo.getP_content().replaceAll("<br>", "\n\r");
		vo.setP_content(p_content);
		request.setAttribute("vo", vo);
		//forward
		return "photo_modify_form.jsp";
	}
	@RequestMapping("/photo/modify.do")
	public String modify(HttpServletRequest request, HttpServletResponse response) {
		String p_subject = request.getParameter("p_subject");
		String p_content = request.getParameter("p_content").replaceAll("\n\r","<br>");
		String p_ip = request.getRemoteAddr();
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		//request binding
		PhotoVo vo = new PhotoVo(p_idx, p_subject, p_content, p_ip);
		//forward
		int res = PhotoDao.getInstance().update(vo);
		return "redirect:list.do";
	}
	@RequestMapping("/photo/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String webPath = "/upload/";
		String absPath = request.getServletContext().getRealPath(webPath);
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		//p_idx로 값 불러오기
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		//vo를 통하여 filename 가져오기
		//실제데이터 삭제
		File f = new File(absPath,vo.getP_filename());
		f.delete();
		//DB에서 삭제
		int res = PhotoDao.getInstance().delete(p_idx);
		
		return "redirect:list.do";
	}
	
	
}
