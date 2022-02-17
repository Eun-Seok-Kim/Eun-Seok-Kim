package action.product;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.ProductDao;
import vo.ProductVo;

/**
 * Servlet implementation class ProductInsertActionSungListAction
 */
@WebServlet("/product/insert.do")
public class ProductInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			
			//Session Tracking: GET(Query방식)
			response.sendRedirect("list.do?reason=logout");
			
			return;
		}
		String webPath = "/images/";
		
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
		String p_image_s = "no_file";
		String p_image_l = "no_file";
		File f = mr.getFile("p_image_s");
		if(f != null) {
			//업로드화일이 존재하면
			p_image_s = f.getName(); //업로드된 화일명을 구한다
		}
		f = mr.getFile("p_image_l");
		if(f != null) {
			//업로드화일이 존재하면
			p_image_l = f.getName(); //업로드된 화일명을 구한다
		}
		String category = mr.getParameter("category");
		String p_num	= mr.getParameter("p_num");
		String p_name	= mr.getParameter("p_name");
		String p_company= mr.getParameter("p_company");
		int p_price		= 0;
		try {
		p_price = Integer.parseInt(mr.getParameter("p_price"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		int p_saleprice	= 0; 
		try {
		p_saleprice=Integer.parseInt(mr.getParameter("p_saleprice"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String p_content= mr.getParameter("p_content");
		
		ProductVo vo = new ProductVo(p_price, p_saleprice, category, p_num, p_name, p_company, p_image_s, p_image_l, p_content);
				
		int res = ProductDao.getInstance().insert(vo);
		
		//request binding

		//forward
		response.sendRedirect("list.do?category=" + category);
	}

}

