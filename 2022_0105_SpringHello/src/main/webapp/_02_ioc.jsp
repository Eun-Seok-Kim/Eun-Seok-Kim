<%@page import="vo.PersonVo"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//Spring Container정보를 구한다
	WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(application);
	
	//Spring Container에 생성된 bean(객체)정보를 id를 통해얻어온다
	PersonVo p1 = (PersonVo)wc.getBean("p1");
	
	PersonVo p2 = wc.getBean("p2",PersonVo.class);
	PersonVo p3 = (PersonVo)wc.getBean("p3");
	PersonVo p4 = (PersonVo)wc.getBean("p4");
	
	
	PersonVo p5 = (PersonVo)wc.getBean("p5");
	
	pageContext.setAttribute("p1", p1);
	pageContext.setAttribute("p2", p2);
	pageContext.setAttribute("p3", p3);
	pageContext.setAttribute("p4", p4);


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
<hr>
p3's info
<hr>
이름 : ${p3.name}<br>
나이 : ${p3.age}<br>
번호 : ${p3.tel}<br>
<hr>
p4's info
<hr>
이름 : ${p4.name}<br>
나이 : ${p4.age}<br>
번호 : ${p4.tel}<br>