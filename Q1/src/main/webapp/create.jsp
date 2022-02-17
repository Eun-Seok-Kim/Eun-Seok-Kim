<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div id="head_line">
			<%@ include file="head_line/head_line.jsp" %>
		</div>	
		<div id="site">
			<ul class="submenu_style">
				<li><a href="serch/serch.jsp">검색사이트</a></li>
				<li><a href="game/game.jsp">게임사이트</a></li>
				<li><a href="market/market.jsp">마켓</a></li>
			</ul>
		</div>
			
		<div id ="link">
		북마크에 의존하지 않기 위해서 제작
		</div>
	
		<div id="footer">
		<%@ include file="footer/footer_line.jsp" %>
		</div>
	</div>
</body>
</html>