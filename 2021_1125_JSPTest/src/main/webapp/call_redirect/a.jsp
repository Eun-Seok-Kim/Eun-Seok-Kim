<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//Java Code
	System.out.println("--1.a.jsp영역입니다--");
	
	//내장객체 : request(요청처리)	response(응답처리)
	//응답처리 : 클라이언트에게 재접속 정보를 전달
	response.sendRedirect("b.jsp");//b.jsp를 호출함
	//아래쪽 내용은 무시됨(진행이안됨)
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
	여기는 a.jsp영역입니다.
</body>
</html>