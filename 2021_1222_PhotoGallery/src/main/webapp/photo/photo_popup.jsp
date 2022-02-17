<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap 3버젼 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">

	#popup{
		width: 400px;
		height: auto;
		padding: 10px;
		border: 1px solid gray;
		background: #333333;
		
		position: absolute; /* 절대좌표: 부모를 기준으로 삼는 좌표 */

		
		display: none; /* hidden */
	}
	
	#pop_close{
		text-align: right;
		margin-bottom: 5px;
	}
	
	#pop_img{
		width: 378px;
		height: 378px;
		border: 1px solid black;
		outline: 1px solid gray;
	}
	
	#pop_subject{
		border: 1px solid gray;
		margin-top: 5px;
		margin-bottom: 5px;
		padding: 5px;
		box-shadow: 1px 1px 1px black;
		
		color: white;
	}
	
	#pop_content{
		border: 1px solid gray;
		margin-bottom: 5px;
		padding: 5px;
		box-shadow: 1px 1px 1px black;
		min-height: 60px;
		height: auto;
		
		color: white;
	}
	
	#pop_name_date{
		border: 1px solid gray;
		margin-bottom: 5px;
		padding: 5px;
		box-shadow: 1px 1px 1px black;
		
		color: white;
	}
	
	#pop_button{
		text-align: center;
	}

</style>

</head>
<body>

	<div id="popup">
		<div id="pop_close">
			<input type="button" value="x" onclick="hide_popup();">
		</div>
		<img id="pop_img" src="">
		<div id="pop_subject">제목</div>
		<div id="pop_content">내용</div>
		<div id="pop_name_date">홍길동 2021-12-22</div>
		<div id="pop_button">
		
			
			<input class="btn btn-danger" type="button" value="삭제" onclick="delete_photo();">
			<input class="btn btn-info"   type="button" value="수정" onclick="modify_photo();">
			
		</div>
	
	</div>


</body>
</html>