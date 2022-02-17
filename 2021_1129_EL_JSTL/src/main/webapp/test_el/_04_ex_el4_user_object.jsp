<%@page import="vo.PersonVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PersonVo p = new PersonVo("홍길동",30,"101-111-1234");
	
	PersonVo [] p_array = {
			new PersonVo("일길동",31,"010-111-1234"),
			new PersonVo("이길동",32,"010-222-1234"),
			new PersonVo("삼길동",33,"010-333-1234")
	};
	//EL 표현식으로 사용하기 위해
	pageContext.setAttribute("p", p);
	pageContext.setAttribute("p_array", p_array);
%>    
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
PersonVo 데이터 출력
<hr>
<%-- ${vo.property명} --%>
이름 : ${pageScope.p.name }<br><!-- 내부적으로 p.getName()이 호출 -->
나이 : ${pageScope.p.age }<br>
전화 : ${pageScope.p.tel }<br>

<hr>
	p_array데이터 출력
<hr>
1. 이름 : ${p_array[0].name}<br>
1. 나이 : ${p_array[0].age}<br>
1. tel  : ${p_array[0].tel}<br>
<hr>
	Person Date Table츨력
<hr>
<table border="1" width="400">
	
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>나이</th>
		<th>전화</th>
	</tr>
		<!-- for(PersonVo p : p_array) 
		-->
		<c:forEach var="p1" items="${p_array}" varStatus="i">
		<tr>
			<td>${i.count }</td>  <%-- ${i.index+1 } --%>
			<td>${pageScope.p1.name }</td>
			<td>${p_array[i.index].age }</td>
			<td>${p1.tel }</td>
		</tr>
		</c:forEach>
</table>





 









</body>
</html>