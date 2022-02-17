<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//배열
	String [] sido_array = {"서울","경기","인천","대전"};

	//ArrayList
	List<String> fruit_list = new ArrayList<String>();
	fruit_list.add("사과");
	fruit_list.add("참외");
	fruit_list.add("수박");
	fruit_list.add("딸기");
	

	//Set : 순서없이 저장되는 자료구조 / 중복허용 안된다.
	Set set = new HashSet();
	set.add("하나");
	set.add("둘");
	set.add("셋");
	set.add("하나");
	
	//Map
	Map map = new HashMap();
	
	map.put("kr","대한민국");
	map.put("us","미국");
	map.put("jp","일본");
	map.put("ch","중국");
	
	//pageScope binding
	
	/* pageContext.setAttribute("sido_array", sido_array); */
	request.setAttribute("sido_array", sido_array);
	pageContext.setAttribute("fruit_list", fruit_list);
	pageContext.setAttribute("set", set);
	pageContext.setAttribute("map", map);
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
	<hr>
		Map 출력
	<hr>
	<ul>
		<!-- for(String su : set) 동
			 items안에 기록되는 데이터타입 : 배열 또는 컬렉션(Set,List,Map)
		 -->
	<c:forEach var="k" items="${map}">
		<li>${k.key	} 국가는 ${k.value}</li>
	</c:forEach>
	</ul>
	<hr>
		Set 출력
	<hr>
	<ul>
		<!-- for(String su : set) 동
			 items안에 기록되는 데이터타입 : 배열 또는 컬렉션(Set,List,Map)
		 -->
	<c:forEach var="su" items="${set}">
		<li>${su}</li>
	</c:forEach>
	</ul>
	<hr>
		배열출력	
	<hr>
		<ul>
			<!-- for(String sido : sido_array 
				JSTL의 표현은 EL표현식으로
			-->
			<%-- <c:forEach var="sido" items="${pageScope.sido_array}"> --%>
			<c:forEach var="sido" items="${sido_array}">
			<li>${pageScope.sido}</li>
			</c:forEach>
		</ul>
		<hr>
			ArrayList출력
		<hr>
		<!-- for(String fruit : fruit_list) 
			 varStatus <= forEach 문이 반복될때 index(첨자), count(반복횟수)관리
		-->
		<ul>
		<c:forEach var="fruit" items="${fruit_list}" varStatus="i">
			<li>${i.count} : ${i.index } : ${ fruit}</li>
					
		</c:forEach>
		</ul>
</body>
</html>