<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie c = new Cookie("C","C.jsp");
	
	//쿠키유효시간 설정
	c.setMaxAge(60*10);

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
C.jsp

<a href="A.jsp">A로 이동</a>
</body>
</html>