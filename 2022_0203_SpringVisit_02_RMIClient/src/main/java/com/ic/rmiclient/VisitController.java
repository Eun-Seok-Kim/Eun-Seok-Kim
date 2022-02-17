package com.ic.rmiclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.VisitDao;
import vo.VisitVo;

@Controller
public class VisitController {

	@Autowired
	VisitDao visit_dao;
	
	
		@RequestMapping("/visit/list.do")
		@ResponseBody
		public String list() {
			
			//���ݼ����κ��� ������ ��������(RMI ���)
			List<VisitVo> list = visit_dao.selectList();
			
			System.out.printf("--�� : %d(��)\n",list.size());
			for(VisitVo vo : list) {
				System.out.println(vo.getName());
				
			}
			return "success";
		}
}
