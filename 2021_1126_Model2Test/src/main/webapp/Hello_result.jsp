<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String ip = (String)request.getAttribute("ip");
String title = (String)request.getAttribute("title");
String message = (String)request.getAttribute("message");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
<body>
	<div>
		<table>
		<tr>
		<td><%=ip %></td>
		<th>님</th>
		</tr>
		<tr>
		<td><%=title %></td>
		<th><%=message %>입니다.</th>
		</tr>
		<tr>
		<td><a href='Hello.html'>다시하기</a></td>
		</tr>
		</table>
	</div>


</body>
</html>
