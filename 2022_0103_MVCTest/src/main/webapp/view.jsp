<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	상세보기<br><br>
<%-- 	<c:forEach var="list" items="${list}">
	<li><a>${list }</a></li>
	</c:forEach><br> --%>
	
	<a>${book}</a><br>
	<a>${content}</a>
	<br>
	
	<a href="list.do">목록보기</a>
</body>
</html>