package com.ic.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

	@RequestMapping("/product/list.do")
	public String list(Model model) {
		
		List list = new ArrayList();
		list.add("DB");
		list.add("�׳� ����");
		list.add("�ϴ°ɷ�");
		list.add("�ϰ�ʹ�");
		
		model.addAttribute("list",list);
		
		
		return "product/product_list";
	}
}
