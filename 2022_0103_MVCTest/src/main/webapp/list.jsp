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
<hr>
Book리스트
<hr>
<ul>
	<!-- for(String Book : list) -->
	<c:forEach var="book" items="${list }">
			<li><a href="view.do?book=${book}">${book}</a></li>
	</c:forEach>
</ul>
</body>
</html>