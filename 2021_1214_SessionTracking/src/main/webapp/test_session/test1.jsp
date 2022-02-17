<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = session.getId();
System.out.printf("Session ID : %s\n",id);

HttpSession session1 = request.getSession();
System.out.printf("re id : %s\n",session1.getId());

//유효시간지정
session.setMaxInactiveInterval(60);

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