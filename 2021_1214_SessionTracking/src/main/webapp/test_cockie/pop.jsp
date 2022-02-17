<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//쿠키정보 읽어오기
	Cookie [] cookie_array = request.getCookies();
	StringBuffer sb = new StringBuffer("[최근방문페이지]<br>");
	if(cookie_array != null){
		for(Cookie c1 : cookie_array){
			String name =c1.getName();
			String value = c1.getValue();					
			if(name.equals("JSESSIONID")==false){
				System.out.printf("%s : %s\n",name,value);
				sb.append(String.format("<a href='%s'>%s</a><br>",value,name));
			}//if-end
		}//for-end
		
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#pop_box{
	width: 200px;
	height: 200px;
	background: gray;
	color: white;
	position: absolute;
	top: 50px;
	right: 50px;
	padding: 30px;
}
a{
	text-decoration: none; /* 하이퍼링크텍스트 밑줄 없애기 */
	color: white;
	text-shadow: 1px 1px 1px white;
}
</style>
</head>
<body>
<div id="pop_box"><%=sb.toString() %></div>
</body>
</html>