<%@page import="myutil.Jumin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");	
String jumin_no = request.getParameter("jumin_no");


//기존에 완성된 파일 myutil/Jumin.java 파일을 객체로 불러온다.
Jumin jumin = new Jumin();
jumin.setJumin_no(jumin_no);

int year		= jumin.getYear();
int age			= jumin.getAge();
String tti		= jumin.getTti();
String season	= jumin.getSeason();
String gender	= jumin.getGender();
String ganji	= jumin.getGanji();
String local	= jumin.getLocal();    
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
			<style type="text/css">
			#box{
				width: 500px;
				margin : auto;
				margin-top : 50px;
				
			}
			</style>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/poper.js/1.16.0/umd/popper.min.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>			
	</head>
<body>
	<div id="box">
		<table class="table table-striped table-bordered">
			<!-- <caption>::::결과::::</caption> -->
			<tr>
				<th>출생년도</th>
				<td><%=year %>(<%=ganji %>)</td>
			</tr>
			<tr>
				<th>나이</th>
				<td><%=age %></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><%=gender %></td>
			</tr>
			<tr>
				<th>띠</th>
				<td><%=tti %></td>
			</tr>
			<tr>
				<th>출생계절</th>
				<td><%=season %></td>
			</tr>
			<tr>
				<th>출생지역</th>
				<td><%=local %></td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" class="btn btn-info"
				value="다시하기" onclick="location.href='jumin.html'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>