<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
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
	width: 1000px;
	margin: auto;
}
</style>
<script type="text/javascript">
function place_view(url) {
	Swal.fire({
		  title: '위치보기',
		  icon: 'info',
		  width:1000,
		  html:"<iframe src='" + url+ "' width='800' height='800'></iframe>",
		  showCloseButton: true,
		  focusConfirm: false,
		  confirmButtonText:'확인',
		});
}

</script>
</head>
<body> 

<div id="box">
	<table class="table table-striped table-hover">
	   <tr>
	   		<th width="20%">상호</th>
	   		<th width="15%">전화</th>
	   		<th width="30%">주소</th>
	   		<th width="10%">거리(m)</th>
	   		<th width="10%">위치(위도/경도)</th>
	   		<th width="20%">길찾기</th>
	   </tr>
	   <!--  for(DaumLocalVo vo : list ) -->
	   <c:forEach var="vo" items="${ list }">
	       <tr>
	       		<td>${ vo.place_name }</td>
	       		<td>${ vo.phone }</td>
	       		<td>${ vo.address }</td>
	       		<td>${ vo.distance }</td>
	       		<td>${ vo.latitude }<br>${ vo.longitude }</td>
	       		<td>
	       		    <a href="${ vo.place_url }" target="_blank">위치</a>
	       		    <input type="button" value="View" onclick="place_view('${vo.place_url}');">
	       		</td>
	       </tr> 
	   </c:forEach>
	   
	</table>   
</div>
</body>
</html>