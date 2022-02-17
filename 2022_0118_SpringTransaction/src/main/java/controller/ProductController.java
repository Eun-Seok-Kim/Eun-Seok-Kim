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
		
		//model통해서 데이터전달->결과적으로 request binding
		model.addAttribute("map", map);
		
		return "product/product_list";
	}
	
	//입고
	// /product/insert_in.do?name=tv&cnt=10
	@RequestMapping("/product/insert_in.do")
	public String insert_in(ProductVo vo) {
		
		try {
			
			//서비스객체에게 입고처리요청
			int res = service.insert_in(vo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:list.do";
	}
	
	//출고
	// /product/insert_out.do?name=tv & cnt=10
	@RequestMapping("/product/insert_out.do")
	public String insert_out(ProductVo vo, Model model) {
		
		try {
			//서비스객체에게 출고처리요청
			int res = service.insert_out(vo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String message = e.getMessage();
			
			//redirect:반환시 Query로 이용
			model.addAttribute("error",message);
		}
		
		
		return "redirect:list.do";
	}
	
	//입고취소
	// /product/delete_in.do?idx=1
	@RequestMapping("/product/delete_in.do")
	public String delete_in(ProductVo vo,Model model){

		try {
			//입고취소
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
