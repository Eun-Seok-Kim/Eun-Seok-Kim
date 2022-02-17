<%@page import="java.util.Properties"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%
  	Map map = new HashMap();
  	map.put("one", "1개를 뜻합니다");
  	map.put("two", "2개를 뜻합니다");
  	map.put("three", "3개를 뜻합니다");
  	
  	//String value = (String)map.get("one");
  	
  	Properties prop = new Properties();
  	prop.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
  	prop.put("url", "jdbc:oracle:thin:@localhost:1521:xe");
  	prop.put("user" , "scott");
  	prop.put("pwd","tiger");
	
  	//EL을 사용하기 위해 scope에 등록
	pageContext.setAttribute("map", map);  
  	pageContext.setAttribute("prop", prop);
  %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
map data 출력
<hr>
전체 : ${map}<br>
one : ${ pageScope.map.one }<br><!-- .표현방식 -->
two : ${ map['two'] }<br><!-- bracket -->
three : ${map["three"] }<br>
<hr>
prop
<hr>
driver : ${prop.driver }<br>
url    : ${prop.url }<br>
user   : ${prop.user }<br>
pwd    : ${prop.pwd }<br>
</body>
</html>