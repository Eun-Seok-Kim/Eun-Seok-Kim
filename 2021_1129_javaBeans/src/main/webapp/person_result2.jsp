<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
/* 
	JavaBeans : TagLibrary를 통해서 생성된 객체
	bean() <= 객체에대한 다른표현
	TagLibrary : Java 명령을 Tag형식으로 만들어 놓은 명령
	Action Tag : tag library
*/



%>
<!-- PersonVo p = new PersonVo() -->
<jsp:useBean id="p" class="vo.PersonVo">
	<jsp:setProperty name="p" property="*" />
</jsp:useBean>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
수신데이터2 출력
<hr>
이름 : <jsp:getProperty property="name" name="p"/><br>
나이 :  <jsp:getProperty property="age" name="p"/><br>
전화번호 :  <jsp:getProperty property="tel" name="p"/><br>

<a href="input.html">다시하기</a>
</body>
</html>