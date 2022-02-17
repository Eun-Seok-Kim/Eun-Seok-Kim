package action.photo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoOneAction
 */
@WebServlet("/photo/photo_one.do")
public class PhotoOneAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /photo/photo_one.do?p_idx=1
		
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		//p_idx�� �ش�Ǵ� ��ü 1�� ���ϱ�
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		
		//JSON�����ϱ�
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
		
		//����
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json.toString());
		

	}
}