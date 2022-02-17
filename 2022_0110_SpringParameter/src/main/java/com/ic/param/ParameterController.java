package com.ic.param;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.PersonVo;

@Controller
public class ParameterController {

		public ParameterController() {
			// TODO Auto-generated constructor stub
		}
@RequestMapping(value="/insert1.do", produces="text/html;charset=utf-8;")
@ResponseBody
public String insert1(@RequestParam("name") String irum, int age, String tel) {
	// parameter이름 = 수신되는변수명이 동일하면 Spring객체가 알아서 넣어준다.
	// 틀린경우  @RequestParam("name") 이런식으로 연결
	// /insert1.do?name=홍길동&age=30&tel=010-111-1234
	System.out.printf("이름:%s\n",irum);
	System.out.printf("나이:%d\n",age);
	System.out.printf("번호:%s\n",tel);
	return "낱개로 받기 완료"; //@ResponseBody : return 값을 DispatcherServlet이 바로 반환한다
	}


@RequestMapping(value="/insert2.do", produces="text/html;charset=utf-8;")
@ResponseBody
public String insert2(PersonVo vo ,HttpServletRequest request) {
	System.out.printf("이름:%s\n",vo.getName());
	System.out.printf("나이:%d\n",vo.getAge());
	System.out.printf("번호:%s\n",vo.getTel());
	return "객체로받기 완료";
}

@RequestMapping(value="/insert3.do", produces="text/html;charset=utf-8;")
@ResponseBody
public String insert3(@RequestParam Map map) {
		
	//parameter명을 key로 Map객체를 생성
	System.out.printf("이름:%s\n",map.get("name"));
	System.out.printf("나이:%s\n",map.get("age"));//<<%s임
	System.out.printf("번호:%s\n",map.get("tel"));
	return "MAP으로받기 완료";
}

}
