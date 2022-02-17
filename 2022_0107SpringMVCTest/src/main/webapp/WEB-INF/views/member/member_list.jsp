<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div >
<table class="table table-striped">
<tr>
           	   <th>이름</th>
               <th>아이디</th>
               <th>비밀번호</th>
               <th>이메일</th>
               <th>전화번호</th>
               <th>주소</th>
</tr>
<c:forEach var="member" items="${list }">
<tr>
		   	    <th>${ member.name}</th>
               <th>${ member.id}</th>
               <th>${ member.pwd}</th>
               <th>${ member.email}</th>
               <th>${ member.tel}</th>
               <th>${ member.addr}</th>
</tr>
</c:forEach>               
</table>

</div>


</body>
</html>