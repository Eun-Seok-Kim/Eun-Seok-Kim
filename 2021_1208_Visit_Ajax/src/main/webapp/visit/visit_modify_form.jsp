<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		<meta charset="UTF-8">
		<title>Insert title here</title>
<style type="text/css">
#box{
	width: 600px;
	margin: auto;
	margin-top: 50px;
}
textarea{
	width: 100%;
	height: 200px;
	resize: none;
}
input[value='수정'],input[value='목록']{
	width: 100px;
	font-family: 휴먼편지체;
	font-size: 15px;
}
</style>
<script type="text/javascript">

function send(f) {
	var name	= f.name.value.trim();
	var content	= f.content.value.trim();
	var pwd		= f.pwd.value.trim();
	
	if(name==''){
		alert("이름을 입력하세요")
		f.name.value=""
		f.name.focus();
		return;
	}
	if(content==''){
		alert("내용을 입력하세요")
		f.content.value=""
		f.content.focus();
		return;
	}
	
	if(pwd==''){
		alert("비밀번호를 입력하세요")
		f.pwd.value=""
		f.pwd.focus();
		return;
	}
	
	f.action="modify.do"
	f.submit();
	
	
}
</script>

	</head>
<body>
<form>
<input type="hidden"  name="idx"  value="${ vo.idx }">
	<div id="box">
		<div class="panel panel-info">
		<div class="panel-heading"><h4>방명록 수정</h4></div>
		<div class="panel-body">
			<table class="table table-striped"S>
				<tr>
					<th>작성자</th>
					<td><input name="name"  value="${vo.name}" ></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content">${vo.content }</textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" value=${vo.pwd }></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input class="btn btn-primary" type="button" value="수정" 
						onclick="send(this.form);">
						<input class="btn btn-success" type="button" value="목록" 
						onclick="location.href='list.do'">
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</form>	
</body>
</html>