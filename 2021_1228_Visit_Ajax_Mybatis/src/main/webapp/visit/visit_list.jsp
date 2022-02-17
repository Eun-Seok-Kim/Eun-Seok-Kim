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
	
	var idx = f.idx.value;
	var c_pwd = f.c_pwd.value.trim();
	if(c_pwd==''){
	
		   Swal.fire({
			   icon: 'info',
			   title: '비밀번호를 입력하세요',
			   text: '비밀번호가 비어있습니다',
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
	//ajax를 통해서 비번 확인
	$.ajax({
		type:'GET',			 				//전송방식 GET(default) , POST
		url: 'check_pwd.do', 				//VisitCheckPwdAction
		data: {'idx':idx, 'c_pwd':c_pwd},	//요청 파라메타
		//응답옵션
		dataType: 'json',					//서버로부터 응답된 데이터 종류
		//응답성공시
		success: function(result_data){
			//result_data = {"result":true} or {"result":false}
			if(result_data.result==false){
				
				   Swal.fire({
					   icon: 'error',
					   title: '비밀번호가 틀려습니다',
					   text: '비밀번호를 확인하세요',
					   confirmButtonText:'확인',
					   returnFocus:false
				   }).then((result) => {
						  if (result.isConfirmed) {
							  f.c_pwd.value='';
							  f.c_pwd.focus();
						  }
					});  

					return;
			}
			if(confirm("정말 삭제하시겠습니까?")==false)return;
	
			//f.action="delete.do"
			//f.submit();
			location.href="delete.do?idx=" + idx;			
		},										
		error: function(err){
			alert(err.responseText);
		}
	
	});

	}
	//최종확인
	//if(confirm("정말 삭제하시겠습니다?")==false)return;
	//f.action="delete.do"
	//f.submit();
	
function modify(f) {
	var idx = f.idx.value;
	var c_pwd = f.c_pwd.value.trim();
	if(c_pwd==''){
		Swal.fire({
			   icon: 'info',
			   title: '비밀번호를 입력하세요',
			   text: '비밀번호가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.c_pwd.value='';
					  f.c_pwd.focus();
				  }
			});  

		return;		
	}
	$.ajax({
		type:'GET',			 				//전송방식 GET(default) , POST
		url: 'check_pwd.do', 				//VisitCheckPwdAction
		data: {'idx':idx, 'c_pwd':c_pwd},	//요청 파라메타
		//응답옵션
		dataType: 'json',					//서버로부터 응답된 데이터 종류
		//응답성공시
		success: function(result_data){
			//result_data = {"result":true} or {"result":false}
			if(result_data.result==false){
				   Swal.fire({
					   icon: 'error',
					   title: '비밀번호가 틀려습니다',
					   text: '비밀번호를 확인하세요',
					   confirmButtonText:'확인',
					   returnFocus:false
				   }).then((result) => {
						  if (result.isConfirmed) {
							  f.c_pwd.value='';
							  f.c_pwd.focus();
						  }
					});  

					return
			}
			//if(confirm("정말 삭제하시겠습니다?")==false)return;
			//f.action="modify_form.do"
			//f.submit();
			location.href="modify_form.do?idx="	+ idx;		
		},										
		error: function(err){
			alert(err.responseText);
		}
	
	});
	
	//f.action="modify_form.do"
	//f.submit();
}
function find() {
	var search = $("#search").val();
	var search_text = $("#search_text").val().trim();
	if(search!='all'&& search_text==''){
		alert('검색어를 입력하세요');
		$("#search_text").val('');
		$("#search_text").focus;
		return;
	}
	location.href="list.do?search="+ search + "&search_text="+encodeURIComponent(search_text);
/*  	
	$.ajax({
		url : "visit/visit_condition_list.do",
		data : {"search":search, "search_text":search_text},
		success : function(result_data){
			$("#disp").html(result_data);
		},
		error : function(err) {
			alert(err.gesponseText);
		}
	
	}); */
}
$(document).ready(function(){
	if("${not empty param.search}"=="true"){
	$("#search").val("${param.search}");
	}
	if("${(empty param.search) or (param.search eq 'all')}"=="true"){
		$("#search_text").val('');
	}
});

</script>
	</head>
<body>
	<div id="main_box">
		<h1 id=title>::::방명록::::</h1>
			<div style="text-align: right; margin-top: 20px; margin-bottom: 10px;"> 
				<input class="btn btn-primary" type="button" value="새글쓰기"
						onclick="location.href='insert_form.do'">
			</div>
	<!-- 검색기능 -->		
		<hr>
			<div style="text-align: center;">
				<select id="search">
					<option value="all">전체</option>
					<option value="name">이름</option>
					<option value="content">내용</option>
					<option value="name_content">이름+내용</option>
				</select>
				<input id="search_text" value="${param.search_text }">
				<input type="button" value="검색" onclick="find();">
			</div>
		<hr>
	<!-- 데이터 없는경우 -->
		<c:if test="${empty list }">
			<div id="empty_message">등록된 데이터가 없습니다.</div>
		</c:if>
	<!-- 데이터 있는경우 -->
		<c:forEach var="vo" items="${list }">
		<form>
			<input type="hidden" name="idx" value="${vo.idx}">
			<div class="panel panel-primary">
					<div class="panel-heading"><h4><b>${vo.name }</b>님의 글</h4></div>
					<div class="panel-body">
						<div class="visit_style content_style">${vo.content }</div>
						<div class="visit_style regdate_style">작성일자 : ${fn:substring(vo.regdate,0,16) }(${vo.ip}) </div>
						<div class="visit_style pwd_style">
									비밀번호 : <input type="password" id="c_pwd" name="c_pwd">
												
											   <input class="btn btn-info" type="button" value="수정"
											    onclick="modify(this.form)">
											   <input class="btn btn-danger" type="button" value="삭제"
											   onclick="del(this.form)">
						</div>
					</div>
			</div>	
		</form>
		</c:forEach>
	</div>
</body>
</html>