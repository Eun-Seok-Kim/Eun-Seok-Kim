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
<!-- SweetAlert2사용설정  -->
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
	var m_name	= f.m_name.value.trim();
	var m_id	= f.m_id.value;
	var m_pwd		= f.m_pwd.value.trim();
	var m_zipcode	= f.m_zipcode.value;
	var m_addr	= f.m_addr.value;
	
	   if(m_name==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '이름을 입력하세요',
			   text: '이름이 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_name.value='';
					  f.m_name.focus();
				  }
			});   
	   return;
	   }

	   if(m_pwd==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '비밀번호를 입력하세요',
			   text: '비밀번호가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_pwd.value='';
					  f.m_pwd.focus();
				  }
			});   
	   return;
	   }
	   if(m_zipcode==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '우편번호를 입력하세요',
			   text: '우편번호가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_zipcode.value='';
					  f.m_zipcode.focus();
				  }
			});   
	   return;
	   }
	   if(m_addr==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '주소를 입력하세요',
			   text: '주소가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_addr.value='';
					  f.m_addr.focus();
				  }
			});   
	   return;
	   }
	   
	   f.action="modify.do";
	   f.submit();
	   
}
</script>
	
		</head>
<body>
<form>
<input type="hidden"  name="m_idx"  value="${ vo.m_idx }">
	<div id="box">	
		<div class="panel panel-success">
	      <div class="panel-heading"><h4>회원가입</h4></div>
		      <div class="panel-body">
		      		<table class="table table-striped">
		      			<tr>
		      				<th>이름</th>
		      				<td><input name="m_name" value="${vo.m_name }"></td>
		      			</tr>
		      			<tr>
		      				<th>아이디</th>
		      				<td><input name="m_id" value ="${vo.m_id }" readonly="readonly"></td>
		      			</tr>
		      			<tr>
		      				<th>비밀번호</th>
		      				<td><input name="m_pwd" value ="${vo.m_pwd }"></td>
		      			</tr>
		      			<tr>
		      				<th>우편번호</th>
		      				<td><input name="m_zipcode" value = "${vo.m_zipcode }"></td>
		      			</tr>
		      			<tr>
		      				<th>주소</th>
		      				<td><input name="m_addr" value = "${vo.m_addr }"></td>
		      			</tr>
		      			<tr>
		      				<td colspan="2" align="center"><input class="btn btn-success" type="button" value="수정"
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