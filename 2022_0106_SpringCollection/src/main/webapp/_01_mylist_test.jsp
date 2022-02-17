<%@page import="myutil.MyProp"%>
<%@page import="java.util.Properties"%>
<%@page import="myutil.MyMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="myutil.MySet"%>
<%@page import="java.util.List"%>
<%@page import="myutil.MyList"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// JSP내장객체
	// ServletContext application
	// Spring Container영역에 대한 정보 구하기
	WebApplicationContext wc =
				WebApplicationContextUtils.getWebApplicationContext(application);

	// Container내에 등록된 객체정보 구하기
	MyList myList = wc.getBean("myListBean",MyList.class);
	// myList내의 list를 구한다: list는 spring생성하여 주입
	List list = myList.getList();
	//binding : EL사용을 위해 page에 넣기
	pageContext.setAttribute("list", list);
	
	
	MySet mySet = wc.getBean("mySetBean",MySet.class);
	
	Set set = mySet.getSet();
	
	pageContext.setAttribute("set", set);
	
	
	MyMap myMap = wc.getBean("myMapBean", MyMap.class);
	
	Map map = myMap.getMap();//생략가능
	
	pageContext.setAttribute("map", map);

	
	MyProp myProp = wc.getBean("myPropBean", MyProp.class);
	System.out.print(myProp);
	pageContext.setAttribute("myProp", myProp);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
	MyList의 목록
	<hr>
		
	<ul>
		<c:forEach var="str" items="${list}">
			<li>${str}</li>
		</c:forEach>	
	</ul> 
		<hr>
	과일 목록
	<hr>
		
	<ul>
		<c:forEach var="set" items="${set}">
			<li>${set}</li>
		</c:forEach>	
	</ul> 
	<hr>
	영어 한글
	<hr>
		
	<ul>
		<c:forEach var="lange" items="${map}">
			<li>영어는${lange.key} 한글로:${lange.value} 입니다</li>
		</c:forEach>	
	</ul> 
	
	<hr>
		Oracle Driver info
	<hr>		
	<ul>
		<c:forEach var="info" items="${myProp.prop}">
			<li>${info.key} : ${info.value} </li>
		</c:forEach>	
	</ul> 
</body>
</html>