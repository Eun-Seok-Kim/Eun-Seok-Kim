<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
제목 : ${title}
<div>
	<c:forEach var="list" items="${file_list }">
	<div>
		<img src="resources/upload/${list}" width="300">
	</div>
	</c:forEach>
</div>
</body>
</html>