<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/main.css">
		<link rel="stylesheet" href="css/main_menu.css">
		<link rel="stylesheet" href="css/sub_menu.css">
	</head>
<body>
	<div id="main_box">
		<div id="header">
		<!-- 외부 파일 포함시키기 -->
			<%@ include file="header/header.jsp" %>	
		</div>
		<div id="aside">
			<%@ include file="menu/submenu_company.jsp"%>		
		</div>
		<div id="content">
			<c:if test="${(empty param.menu)or(param.menu=='intro')}">
				<%@include file="/content/company/intro.jsp" %>		
			</c:if>
			
			<c:if test="${param.menu eq 'history' }">
				<%@include file="/content/company/history.jsp" %>	
			</c:if>
			
			<c:if test="${param.menu eq 'location' }">
				<%@include file="/content/company/location.jsp" %>
			</c:if>		
		</div>
		<div id="footer">
			<%@ include file="footer/footer.jsp" %>
		</div>
	</div>
</body>
</html>