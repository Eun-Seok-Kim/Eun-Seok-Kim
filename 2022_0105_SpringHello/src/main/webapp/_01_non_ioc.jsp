<%@page import="vo.PersonVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//1.생성자를 통한 초기화
	PersonVo p1 = new PersonVo("일길동",20,"010-1111-1234");

	//2.setter를 이용한 값 설정
	PersonVo p2 = new PersonVo();
	p2.setName("이길동");
	p2.setAge(20);
	p2.setTel("010-2222-1234");
	
	//3. 외부생성 객체를 넣는 개념
	String name = new String("삼길동");
	Integer age = new Integer("20");
	String tel	= new String("010-3333-1234");
	
	PersonVo p3 = new PersonVo(name,age,tel);
	
	
	//pageContext에 binding (EL사용을 위한 값 넣기)
	pageContext.setAttribute("p1", p1);
	pageContext.setAttribute("p2", p2);
%>

<hr>
p1's info
<hr>
이름 : ${p1.name}<br>
나이 : ${p1.age}<br>
번호 : ${p1.tel}<br>

<hr>
p2's info
<hr>
이름 : ${p2.name}<br>
나이 : ${p2.age}<br>
번호 : ${p2.tel}<br>