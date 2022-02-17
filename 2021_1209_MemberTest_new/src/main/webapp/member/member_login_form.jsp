<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#box{
	width: 400px;
	height: 400px;
	margin: auto;
	margin-top: 100px;
}
input[type='button']{
	width: 80px;
}
</style>
<script type="text/javascript">
function send(f) {
	var m_id = f.m_id.value.trim();
	var m_pwd = f.m_pwd.value.trim();
	
	if(m_id ==''){
		   Swal.fire({
			   icon: 'error',
			   title: '아이디를 입력하세요',
			   text: '아이디가 비어있습니다.',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  //f.m_id.value='';
					  f.m_id.focus();
				  }
			});
		return;
	}
	if(m_pwd ==''){
		   Swal.fire({
			   icon: 'error',
			   title: '패스워드를 입력하세요',
			   text: '패스워드가 비어있습니다.',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  //f.m_pwd.value='';
					  f.m_pwd.focus();
				  }
			});
		return;
	}
	
	f.action = "login.do";
	f.submit();	
}
$(document).ready(function(){
	setTimeout(show_message,100);
});
function show_message(){
	//member/login_form.do?reasion=fail_id
	if("${param.reasion == 'fail_id'}"=="true"){
		   Swal.fire({
			   icon: 'error',
			   title: '아이디가 틀렸습니다',
			   text: '아이디를 확인해주세요',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  //f.m_id.value="";
					  f.m_id.focus();
				  }
			});
		
	}
	if("${param.reasion == 'fail_pwd'}"=="true"){
		 Swal.fire({
			   icon: 'error',
			   title: '비밀번호가 틀렸습니다',
			   text: '비밀번호를 확인해주세요',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_id.value="";
					  f.m_id.focus();
				  }
			});
		
	}
	
	//로그인 된 겨웅 -> 세션에 로그인정보 저장
	
}

</script>
</head>
<body>
<form>
<div id="box">
	 <div class="panel panel-danger">
      <div class="panel-heading">로그인</div>
      <div class="panel-body">
      	<table class="table table striped">
      		<tr>
      			<th>아이디</th>
      			<td><input name="m_id"></td>
      		</tr>
      		<tr>
      			<th>비밀번호</th>
      			<td><input type="password" name="m_pwd"></td>
      		</tr>
      		<tr>
      			<td colspan="2" align="center">
      				<input class="btn btn-primary" type="button" value="로그인" onclick="send(this.form)">
      				<input class="btn btn-success" type="button" value="목록" onclick="location.href='list.do'">
      				<input class="btn btn-info" type="button" value="회원가입" onclick="location.href='insert_form.do'">
      		</tr>
      		
      	</table>
      </div>
    </div>
</div>
</form>
</body>
</html>