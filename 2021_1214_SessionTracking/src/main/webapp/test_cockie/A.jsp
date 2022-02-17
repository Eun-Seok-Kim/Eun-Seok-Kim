<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie c = new Cookie("A","A.jsp");
	//쿠키등록
	response.addCookie(c);
%>
<%@ include file="pop.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

A.jsp

<a href="B.jsp">B로 이동</a>
</body>
</html>