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

	//자동연결코드
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
	
	
	
	//Map -> JSON으로 변환
	
	@RequestMapping("/convert_map.do")
	@ResponseBody
	public Map convert_map() {
		
		Map map = new HashMap();
		map.put("result", true);
		map.put("name", "맵길동");
		map.put("age", 20);
		map.put("addr", "서울시 관악수 신림동");
		return map;
	}
	//Object -> JSON변환
	@RequestMapping("/convert_object.do")
	@ResponseBody
	public DeptVo convert_object() {
		DeptVo vo = new DeptVo();
		vo.setDeptno(10);
		vo.setDname("총무부");
		vo.setLoc("101호");
		
		
		return vo;
	}
	
	//ArrayList -> JSON변환
	@RequestMapping("/convert_list.do")
	@ResponseBody
	public List convert_list() {
		
		List list = new ArrayList();
		
		list.add("사과");
		list.add("딸기");
		list.add("수박");
		list.add("참외");
		
		
		return list;
	}
	@RequestMapping("/convert_list2.do")
	@ResponseBody
	public Map convert_list2() {
		
		List list = new ArrayList();
		
		list.add("사과");
		list.add("딸기");
		list.add("수박");
		list.add("참외");
		
		Map map = new HashMap();
		map.put("fruit_list", list);
		
		return map;
	}
	
	
	
	
}
