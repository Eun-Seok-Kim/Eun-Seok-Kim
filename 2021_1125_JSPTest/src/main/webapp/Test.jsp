<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! //선언부(Java Code영역)
    String name ="Test JSP";
	int age= 10;
	Random rand = new Random();
	Calendar c = Calendar.getInstance();
	public void display(){
		System.out.println(name + "님 안녕하세요");
	}
%>

<% //scriptlet
   //Java Code영역
   //_jspService()내에 들어가는 코드영역
   int a = 10;
	//JSP내장객체(내부에 존재하는 객체)
	// request, session, application, out , pageContext
	String ip = request.getRemoteAddr();
	out.println("IP->" + ip + "<br>");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
<%
int b = 20;
%>
나이 : <%=age %><br>
a:<%=a %><br>
b:<%=b %><br>
요청자 IP : <%=ip %><br>
</body>
</html>