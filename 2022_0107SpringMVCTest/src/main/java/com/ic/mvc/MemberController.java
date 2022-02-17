package com.ic.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vo.MemberVo;

@Controller
public class MemberController {

	@RequestMapping("/member/list.do")
	public String list(Model model) {
		List<MemberVo> list = new ArrayList<MemberVo>();
		list.add(new MemberVo("일길동", "one", "1234", "one@naver.com", "010-111-1234", "서울시 관악구 신림1동"));
		list.add(new MemberVo("이길동", "two", "1234", "two@naver.com", "010-222-1234", "경기도 관악수 신림1동"));
		list.add(new MemberVo("삼길동", "three", "1234", "three@naver.com", "010-333-1234", "부산시 관악수 신림1동"));
		list.add(new MemberVo("사길동", "four", "1234", "four@naver.com", "010-444-1234", "인천 관악수 신림1동"));
		list.add(new MemberVo("오길동", "five", "1234", "five@naver.com", "010-555-1234", "대전 관악수 신림1동"));
		
		model.addAttribute("list",list);
		return "member/member_list";
	}
}
