<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jstl -->
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
#box{
	width: 700px;
	margin: auto;
	margin-top: 50px;
	}
table{
	width: 100%;
	}
th{
	text-align: center;
	}
#title{
	text-align: center;
	font-family: 휴면편지체;
	font-size: 25px;
	}
#empty_message{
	text-align: center;
	color: red;
	font-size: 40px;
	font-family: 휴먼옛체;
	}
input[value='수정']{
	border: 1px solid #eeeeee;
	background: skyblue;
}	

input[value='삭제']{
	border: 1px solid #eeeeee;
	background: pink;
}	
</style>
<script type="text/javascript">

function del(m_idx) {
	if(confirm("정말 삭제하시겠습니까?")==false){
	return;	
	}
	location.href = "delete.do?m_idx=" + m_idx;
}

function modify_form(m_idx) {
	location.href = "modify_form.do?m_idx=" + m_idx;
}


</script>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
	<div id="bpx">
		<h1 id="title">::::회원목록::::</h1>
			<div style="text-align: right; maargin-top: 20px; margin-bottom: 20px;">
				<input class="btn btn-success" type="button" value="로그인" onclick="">
				<input class="btn btn-primary" type="button" value="회원가입" 
						onclick="location.href='insert_form.do'">
			</div>
		<table class="table table-striped">
			<tr class="success">
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>우편번호</th>
				<th>주소</th>
				<th>아이피</th>
				<th>가입일자</th>
				<th>회원구분</th>
				<th>편집</th>
			</tr>
			<c:if test="${empty list }">
				<tr>
					<td id="empty_message" colspan="10">회원정보가 없습니다.</td>
				</tr>
			</c:if>	
			<c:forEach var="vo" items="${list }">
			<form>
				<tr align="center">
					<td>${vo.m_idx }</td>
					<td>${vo.m_name }</td>
					<td>${vo.m_id }</td>
					<td>${vo.m_pwd_hidden }</td>
					<td>${vo.m_zipcode }</td>
					<td>${vo.m_addr }</td>
					<td>${vo.m_ip }</td>
					<td>${fn:substring(vo.m_regdate,0,10) }</td>
					<td>${vo.m_grade }</td>
					<td>
						<input type="button" value="수정" 
								onclick="modify_form('${vo.m_idx}');">
						<input type="button" value="삭제" 
								onclick="del('${vo.m_idx}');">
					</td>
				</tr>
			</form>
			</c:forEach>			
		</table>			
	</div>	
</body>
</html>