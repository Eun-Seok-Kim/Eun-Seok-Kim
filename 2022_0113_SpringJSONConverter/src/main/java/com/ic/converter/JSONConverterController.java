package com.ic.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DeptDao;
import vo.DeptVo;

@Controller
public class JSONConverterController {

	//�ڵ������ڵ�
	@Autowired
	DeptDao dept_dao;
	
	//DB ArrayList -> JSON
	@RequestMapping("/convert_dept_list.do")
	@ResponseBody
	public List dept_list() {
		
		
		return dept_dao.selectList();
	}
	
	@RequestMapping("/convert_dept_list2.do")
	@ResponseBody
	public Map dept_list2() {
		
		List list = dept_dao.selectList();
		
		Map map =new HashMap();
		
		map.put("dept_list", list);
		return map;
	}
	
	
	
	//Map -> JSON���� ��ȯ
	
	@RequestMapping("/convert_map.do")
	@ResponseBody
	public Map convert_map() {
		
		Map map = new HashMap();
		map.put("result", true);
		map.put("name", "�ʱ浿");
		map.put("age", 20);
		map.put("addr", "����� ���Ǽ� �Ÿ���");
		return map;
	}
	//Object -> JSON��ȯ
	@RequestMapping("/convert_object.do")
	@ResponseBody
	public DeptVo convert_object() {
		DeptVo vo = new DeptVo();
		vo.setDeptno(10);
		vo.setDname("�ѹ���");
		vo.setLoc("101ȣ");
		
		
		return vo;
	}
	
	//ArrayList -> JSON��ȯ
	@RequestMapping("/convert_list.do")
	@ResponseBody
	public List convert_list() {
		
		List list = new ArrayList();
		
		list.add("���");
		list.add("����");
		list.add("����");
		list.add("����");
		
		
		return list;
	}
	@RequestMapping("/convert_list2.do")
	@ResponseBody
	public Map convert_list2() {
		
		List list = new ArrayList();
		
		list.add("���");
		list.add("����");
		list.add("����");
		list.add("����");
		
		Map map = new HashMap();
		map.put("fruit_list", list);
		
		return map;
	}
	
	
	
	
}
