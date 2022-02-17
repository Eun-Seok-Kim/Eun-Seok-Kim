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
		list.add(new MemberVo("�ϱ浿", "one", "1234", "one@naver.com", "010-111-1234", "����� ���Ǳ� �Ÿ�1��"));
		list.add(new MemberVo("�̱浿", "two", "1234", "two@naver.com", "010-222-1234", "��⵵ ���Ǽ� �Ÿ�1��"));
		list.add(new MemberVo("��浿", "three", "1234", "three@naver.com", "010-333-1234", "�λ�� ���Ǽ� �Ÿ�1��"));
		list.add(new MemberVo("��浿", "four", "1234", "four@naver.com", "010-444-1234", "��õ ���Ǽ� �Ÿ�1��"));
		list.add(new MemberVo("���浿", "five", "1234", "five@naver.com", "010-555-1234", "���� ���Ǽ� �Ÿ�1��"));
		
		model.addAttribute("list",list);
		return "member/member_list";
	}
}
