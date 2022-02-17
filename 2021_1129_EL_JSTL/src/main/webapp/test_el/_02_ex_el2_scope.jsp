<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    //각 scope에 값을 넣어본다
    pageContext.setAttribute("data", "pageScope's data");
    request.setAttribute("data", "requestScope's data");
    session.setAttribute("data", "sessionScope's data");
    application.setAttribute("data", "applicationScope's data");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- ${영역.변수}<br> 
	EL에서 영역명을 생략하면 참조되는 순서 자신과 가까운 순서대로 참조
	pageScope => requestScope -> sessionScope -> applicationScope
--%>
pageScope data : ${pageScope.data }<br>
requestScope data : ${requestScope.data }<br>
sessionScope data : ${sessionScope.data }<br>
applicationScope data : ${applicationScope.data }<br>

</body>
</html>