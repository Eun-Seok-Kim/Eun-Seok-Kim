<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.println("--1.a.jsp영역입니다--");
	//Dispatcher방식 또는 forward 방식 (서버 내부에서 호출하는 방식)
	//최초수신된 request(내장객체)이용해서 호출
	RequestDispatcher disp = request.getRequestDispatcher("b.jsp");
	
	disp.forward(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
aaaaaaaaaaaaaaaaaaaaaa
</body>
</html>