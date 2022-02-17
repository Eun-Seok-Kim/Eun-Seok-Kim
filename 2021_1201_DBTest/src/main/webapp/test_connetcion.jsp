<%@page import="service.DBService"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	int count = 0;
%>

<%
	Connection conn = DBService.getInstance().getConnection();
	System.out.printf("--success connection(%d)--\n",++count);
	conn.close();//자원 반환
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>

</body>
</html>