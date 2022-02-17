<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
과일목록
<hr>
	<ul>
		<c:forEach var="frult" items="${map.list1 }">
			<li>${frult}</li>
		</c:forEach>
	</ul>
	
	
<hr>
시도 목록
<hr>
	<ul>
		<c:forEach var="sido" items="${map.list2 }">
			<li>${sido}</li>
		</c:forEach>
	</ul>

</body>
</html>