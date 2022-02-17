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
		  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		  <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
			 <script>
			 $(document).ready(function(){
			 /* $("#accordion").hide(); */
				  $( function() {
				   	 $( "#accordion" ).accordion();
				  } );
			 });
		  </script>
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
		</div>
		<div id ="end_right">
			<div id="accordion">
			  <h3>홈페이지추가</h3>
				  <div>
				    <p>
						링크넣는곳
				    </p>
				  </div>
			  <h3>홈페이지삭제</h3>
				  <div>
				    <p>
						링크넣는곳
				    </p>
				  </div>
			  <h3>건의게시판</h3>
				 	<div>
				    <p>
				 		개발자에게 건의
				    </p>
				    <p>
				    	개인작성코드 건의
				    </p>
					</div>
			</div>
		</div>
		<div id="footer">
			<%@ include file="footer/footer_line.jsp" %>
		</div>
	</div>
</body>
</html>