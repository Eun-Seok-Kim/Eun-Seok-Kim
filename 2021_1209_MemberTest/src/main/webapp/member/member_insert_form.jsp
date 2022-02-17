<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	width: 600px;
	margin: auto;
	margin-top: 50px;
	text-align: center;	
	
	}
h4{
	text-align: center;
	}
</style>
<script type="text/javascript">

function send(f) {
	var name	= f.m_name.value.trim();
	var id		= f.m_id.value.trim();
	var pwd		= f.m_pwd.value.trim();
	var zipcode	= f.m_zipcode.value;
	var addr	= f.m_addr.value;
	
	   if(name==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '이름을 입력하세요',
			   text: '이름이 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.name.value='';
					  f.name.focus();
				  }
			});   
	   return;
	   }
	   if(id==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '아이디를 입력하세요',
			   text: '아이디가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.id.value='';
					  f.id.focus();
				  }
			});   
	   return;
	   }
	   if(pwd==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '비밀번호를 입력하세요',
			   text: '비밀번호가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.pwd.value='';
					  f.pwd.focus();
				  }
			});   
	   return;
	   }
	   if(zipcode==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '우편번호를 입력하세요',
			   text: '우편번호가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.zipcode.value='';
					  f.zipcode.focus();
				  }
			});   
	   return;
	   }
	   if(addr==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '주소를 입력하세요',
			   text: '주소가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.addr.value='';
					  f.addr.focus();
				  }
			});   
	   return;
	   }
	   f.action="insert.do";
	   f.submit();
	   
}
</script>
	
		</head>
<body>
<form>
	<div id="box">
		<div class="panel panel-success">
	      <div class="panel-heading"><h4>회원가입</h4></div>
		      <div class="panel-body">
		      		<table class="table table-striped">
		      			<tr>
		      				<th>이름</th>
		      				<td><input name="m_name"></td>
		      			</tr>
		      			<tr>
		      				<th>아이디</th>
		      				<td><input name="m_id"></td>
		      			</tr>
		      			<tr>
		      				<th>비밀번호</th>
		      				<td><input name="m_pwd"></td>
		      			</tr>
		      			<tr>
		      				<th>우편번호</th>
		      				<td><input name="m_zipcode"></td>
		      			</tr>
		      			<tr>
		      				<th>주소</th>
		      				<td><input name="m_addr"></td>
		      			</tr>
		      			<tr>
		      				<td colspan="2" align="center"><input class="btn btn-success" type="button" value="가입"
		      							onclick="send(this.form);">
		      					<input class="btn btn-primary" type="button" value="취소"
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