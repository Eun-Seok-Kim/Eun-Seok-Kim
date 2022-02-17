<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL을 사용하려면 아래와 같이 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	/* 
		JSTL(JSP Standard Tag Library)
	1. 사용설정
		C:\apache-tomcat-8.5.72\webapps\examples\WEB-INF\lib
		C:\apache-tomcat-8.5.72\lib에 복사해 넣는다
		
		2.Core Library		 : if,forEach,choose,set
		  Formatter Library	 : 날짜 숫자 포맷
		  Functions Library	 : 문자열편집기능(substring,replace...)
		  
	3. JSTL에서 사용되는 값은 EL 표현식만 사용가능하다	  
	*/

	int money = 123000000;
	Date now = new Date();
	
	//EL등록
	pageContext.setAttribute("money", money);
	pageContext.setAttribute("now", now);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
Core Library 연습
<hr>
<c:forEach begin="1" end="5" step="1" varStatus="i">
	<%-- <c:if test="조건"> --%>
	<c:if test="${ i.index%2 == 1 }">	
	${ pageScope.i.index } : <font color=red>안녕</font><br>
	</c:if>
	<c:if test="${ i.index%2 ==0 }">
	${i.index } : <font color=blue>안녕</font><br>
	</c:if>
</c:forEach>
<hr>
Format Library 연습
<hr>
보유금액 : <fmt:formatNumber type="currency" value="${money}"/><br>
현재날짜 : <fmt:formatDate pattern="YYYY년 MM월 dd일" value="${now}"/><br>
<!-- 
	012345678901234567890123456789  <= index
	Tue Nov 30 09:44:51 KST 2021	<= to.String
 -->
현재날짜 : ${now.toString() }<br>
현재시간 : ${fn:substring(now.toString(),11,11+8) }



</body>
</html>