package controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.ProductService;
import vo.ProductVo;

@Controller
public class ProductController {

	ProductService service;
	
	
	
	
	public void setService(ProductService service) {
		this.service = service;
	}


	@RequestMapping("/product/list.do")
	public String list(Model model) {
		
		Map map = service.selectList();
		
		//model���ؼ� ����������->��������� request binding
		model.addAttribute("map", map);
		
		return "product/product_list";
	}
	
	//�԰�
	// /product/insert_in.do?name=tv&cnt=10
	@RequestMapping("/product/insert_in.do")
	public String insert_in(ProductVo vo) {
		
		try {
			
			//���񽺰�ü���� �԰�ó����û
			int res = service.insert_in(vo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:list.do";
	}
	
	//���
	// /product/insert_out.do?name=tv & cnt=10
	@RequestMapping("/product/insert_out.do")
	public String insert_out(ProductVo vo, Model model) {
		
		try {
			//���񽺰�ü���� ���ó����û
			int res = service.insert_out(vo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String message = e.getMessage();
			
			//redirect:��ȯ�� Query�� �̿�
			model.addAttribute("error",message);
		}
		
		
		return "redirect:list.do";
	}
	
	//�԰����
	// /product/delete_in.do?idx=1
	@RequestMapping("/product/delete_in.do")
	public String delete_in(ProductVo vo,Model model){

		try {
			//�԰����
			int res = service.delete_in(vo);
		} catch (Exception e) {
			// TODO: handle exception
			String message = e.getMessage();
			model.addAttribute("error",message);
		}		
		return "redirect:list.do";
	}
	@RequestMapping("/product/delete_out.do")
	public String delete_out(ProductVo vo, Model model){
		try {
				int res = service.delete_out(vo);
		} catch (Exception e) {
			// TODO: handle exception
			String message = e.getMessage();
			model.addAttribute("error",message);
		}		
		return "redirect:list.do";
	}
		
}
