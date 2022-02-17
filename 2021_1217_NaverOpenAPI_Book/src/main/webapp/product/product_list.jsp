<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	widows: 800px;
	margin: auto;

}
img{
	width: 120px;
	height: 80px;
}
</style>
</head>
<div id="box">
	<table class="table table-striped table-hover">
		<tr>
			<th>이미지</th>
			<th>상품명</th>
			<th>최저가</th>
			<th>최고가</th>
			<th>쇼핑몰</th>
		</tr>
		<c:forEach var="vo" items="${list }">
		<tr>
			<td><img src="${vo.image }"></td>
			<td><a href="${vo.link }" target="_blank">${vo.title }</a></td>
			<td><fmt:formatNumber type="currency" value="${vo.lprice}"/></td>
			<td><fmt:formatNumber type="currency" value="${vo.hprice}"/></td>
			<td>${vo.mallName }</td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>