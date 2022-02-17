<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html>
	<head>
	<!-- bootstrap 3버젼 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>		
		<meta charset="UTF-8">
		<title>Insert title here</title>
<style type="text/css">
#main_box{
	width: 600px;
	margin: auto;
	margin-top: 50px;
}
#title{
	text-align: center;
	text-shadow: 1px 1px 1px black;
	font-size: 40px;
	font-weight: bold;
	font-family: 휴먼매직체,굴림체;
	color: #3366aa;
}
#empty_message{
	text-align: center;
	margin-top: 100px;
	color: red;
	font-size: 85px;
	font-family: 휴먼매직체,굴림체;
	text-shadow: 1px 1px 1px black;
}

.visit_style{
	margin: 5px;
	padding: 5px;
	box-shadow: -1px -1px 1px #cccccc;
	border: 1px solid #eeeeee;
}

.content_style{
	min-height: 80px;
}

.pwd_style > input[name='c_pwd']{
	height: 34px;
}

.regdate_style{
	text-align: right;
	font-size: 15px;
	color: white;
	background: #cccccc;
}
</style>
<script type="text/javascript">
	
function del(f){
	
	var pwd = f.pwd.value;
	var c_pwd = f.c_pwd.value.trim();
	if(c_pwd==''){
		alert("비밀번호를 입력하세요");
		f.c_pwd.value='';
		f.c_pwd.focus();
		return;		
	}
	
	if(pwd != c_pwd){
		alert("비밀번호가 틀립니다");
		f.c_pwd.value='';
		f.c_pwd.focus();
		return;
	}
	if(confirm("정말 삭제하시겠습니까?")==false)return;
	f.action="delete.do"
	f.submit();
}
function modify(f) {
	var pwd = f.pwd.value;
	var c_pwd = f.c_pwd.value.trim();
	if(c_pwd==''){
		alert("비밀번호를 입력하세요");
		f.c_pwd.value='';
		f.c_pwd.focus();
		return;		
	}
	
	if(pwd != c_pwd){
		alert("비밀번호가 틀립니다");
		f.c_pwd.value='';
		f.c_pwd.focus();
		return;
	}
	
	f.action="modify_form.do"
	f.submit();
}

</script>
	</head>
<body>
	<div id="main_box">
		<h1 id=title>::::방명록::::</h1>
			<div style="text-align: right; margin-top: 20px; margin-bottom: 10px;">
				<input class="btn btn-primary" type="button" value="새글쓰기"
						onclick="location.href='insert_form.do'">
			</div>
	<!-- 데이터 없는경우 -->
		<c:if test="${empty list }">
			<div id="empty_message">등록된 데이터가 없습니다.</div>
		</c:if>
	<!-- 데이터 있는경우 -->
		<c:forEach var="vo" items="${list }">
		<form>
			<input type="hidden" name="idx" value="${vo.idx}">
			<input type="hidden" name="pwd" value="${vo.pwd}">
			<div class="panel panel-primary">
					<div class="panel-heading"><h4><b>${vo.name }</b>님의 글</h4></div>
					<div class="panel-body">
						<div class="visit_style content_style">${vo.content }</div>
						<div class="visit_style regdate_style">작성일자 : ${fn:substring(vo.regdate,0,16) }(${vo.ip}) </div>
						<div class="visit_style pwd_style">
									비밀번호 : <input type="password" id="c_pwd" name="c_pwd">
												
											   <input class="btn btn-info" type="button" value="수정"
											    onclick="modify(this.form);">
											   <input class="btn btn-danger" type="button" value="삭제"
											   onclick="del(this.form);">
						</div>
					</div>
			</div>	
		</form>
		</c:forEach>
	</div>
</body>
</html>