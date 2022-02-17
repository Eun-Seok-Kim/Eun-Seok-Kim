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
	// parameter�̸� = ���ŵǴº������� �����ϸ� Spring��ü�� �˾Ƽ� �־��ش�.
	// Ʋ�����  @RequestParam("name") �̷������� ����
	// /insert1.do?name=ȫ�浿&age=30&tel=010-111-1234
	System.out.printf("�̸�:%s\n",irum);
	System.out.printf("����:%d\n",age);
	System.out.printf("��ȣ:%s\n",tel);
	return "������ �ޱ� �Ϸ�"; //@ResponseBody : return ���� DispatcherServlet�� �ٷ� ��ȯ�Ѵ�
	}


@RequestMapping(value="/insert2.do", produces="text/html;charset=utf-8;")
@ResponseBody
public String insert2(PersonVo vo ,HttpServletRequest request) {
	System.out.printf("�̸�:%s\n",vo.getName());
	System.out.printf("����:%d\n",vo.getAge());
	System.out.printf("��ȣ:%s\n",vo.getTel());
	return "��ü�ιޱ� �Ϸ�";
}

@RequestMapping(value="/insert3.do", produces="text/html;charset=utf-8;")
@ResponseBody
public String insert3(@RequestParam Map map) {
		
	//parameter���� key�� Map��ü�� ����
	System.out.printf("�̸�:%s\n",map.get("name"));
	System.out.printf("����:%s\n",map.get("age"));//<<%s��
	System.out.printf("��ȣ:%s\n",map.get("tel"));
	return "MAP���ιޱ� �Ϸ�";
}

}
