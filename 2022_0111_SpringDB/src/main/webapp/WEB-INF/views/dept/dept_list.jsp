<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/poper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<style type="text/css">
		#box{
			width : 500px;
			margin : auto;
			margin-top : 50px;
			border: 1px solid gray;
		}
		table{
			width : 100%;
		}
		
		</style>
	</head>
<body>
	<div id="box">
		<table class="table table-hover">
				<!-- title -->
			<tr class="table-info">
				<th>순번</th>
				<th>부서번호</th>
				<th>부서명</th>
				<th>위치</th>
			</tr>
				<!-- 데이터출력 -->
				<!-- for(DeptVo vo : list) 동일 -->
		<c:forEach var="vo" items="${list}" varStatus="i">		
			<tr>
				<td>${i.count }</td>
				<td>${vo.deptno }</td>
				<td>${vo.dname }</td>
				<td>${vo.loc }</td>			
			</tr>	
		</c:forEach>	
		</table>
	</div>
</body>
</html>