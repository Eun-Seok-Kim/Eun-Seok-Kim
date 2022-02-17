package controller.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import annotation.RequestMapping;
import dao.ProductDao;
import vo.ProductVo;

public class ProductController {

	public ProductController() {
		// TODO Auto-generated constructor stub
		System.out.println("Product");
	}
	
	@RequestMapping("product/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");
		if(category==null) category="com001";
		
		//ī�װ��� ��ǰ���
		List<ProductVo> list = ProductDao.getInstance().selectList(category);

		//request binding
		request.setAttribute("list", list);
		//FrontController�� ���ؼ� forward
		return "product_list.jsp";
	}
	@RequestMapping("/product/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {
		
		return "product_reg_form.jsp";
	}
	@RequestMapping("/product/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			
			//Session Tracking: GET(Query���)
		
			return "list.do?reason=logout";
		}
		String webPath = "/images/";
		
		//�����->������
		ServletContext application =  request.getServletContext();
		String saveDir = application.getRealPath(webPath);
		int    maxSize = 1024*1024 * 100;  // 100MB
		
		MultipartRequest mr = new MultipartRequest(
				                                request,   //����
				                                saveDir,   //������ġ
				                                maxSize,   //�����ִ�ũ��(byte����)
				                                "utf-8",   //�������ڵ�
				                                new DefaultFileRenamePolicy());			
		//���ε�� ȭ������ ���ϱ�
		String p_image_s = "no_file";
		String p_image_l = "no_file";
		File f = mr.getFile("p_image_s");
		if(f != null) {
			//���ε�ȭ���� �����ϸ�
			p_image_s = f.getName(); //���ε�� ȭ�ϸ��� ���Ѵ�
		}
		f = mr.getFile("p_image_l");
		if(f != null) {
			//���ε�ȭ���� �����ϸ�
			p_image_l = f.getName(); //���ε�� ȭ�ϸ��� ���Ѵ�
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
		return "redirect:list.do?category=" + category;
	}
	@RequestMapping("/product/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		ProductVo vo= ProductDao.getInstance().selectOne(p_idx);
		
		//������ ��������

		//request binding
		request.setAttribute("vo", vo);
		return "product_content.jsp";
	}
	
}
