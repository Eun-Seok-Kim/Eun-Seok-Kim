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
			width : 700px;
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
			<th>이름</th>
			<th>성별</th>
			<th>사번</th>
			<th>직급</th>
			<th>입사날짜</th>
			<th>SAMGR</th>
			<th>년봉</th>	
		</tr>
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.sabun}</td>
				<td>${vo.saname }</td>
				<td>${vo.sasex }</td>
				<td>${vo.deptno }</td>
				<td>${vo.sajob }</td>
				<td>${vo.sahire }</td>
	<%-- String으로 받았을때 형변환 <td>${fn:substring(vo.sahire,0,10 }</td> --%>
				<td>${vo.samgr }</td>
				<td>${vo.sapay }</td>
	<!--<fmt:formatNumber type="currency" value=${vo.sapay * 10000}"/>  -->
			</tr>
		</c:forEach>	
	</table>
	</div>
</body>
</html>