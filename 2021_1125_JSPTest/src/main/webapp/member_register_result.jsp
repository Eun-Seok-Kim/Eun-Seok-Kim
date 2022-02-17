<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//수신인코딩 설정(전송방식이 POST일때 무조건 처리되어야함)

// _jspService(request,)
request.setCharacterEncoding("utf-8");

String name		= request.getParameter("name");
String id		= request.getParameter("id");
String pwd		= request.getParameter("pwd");
String gender	= request.getParameter("gender");
String blood	= request.getParameter("blood");

//\n -> <br>로 변경
	String profile	= request.getParameter("profile").replaceAll("\n","<br>");
	
	
	//클라이언트에서 같은이름으로 넘어왔을때 배열로데이터 받기
	// => check box는 체크된 항목만 전송된다.
	String [] hobby_array = request.getParameterValues("hobby");
	String 	  hobby_list="취미없음";
	if(hobby_array!=null)
		{
			StringBuffer sb = new StringBuffer();
				for(String hobby : hobby_array) {
				sb.append(hobby);//sb에 hobby값 넣기
				sb.append(" ");//띄어쓰기
				}//end_hobby_for
			hobby_list = sb.toString().trim(); // 공백제거된 hobby값이 저장
		}//end_hobby_if
	
	// => 전체 항목이 넘어온다
	String [] friend_array = request.getParameterValues("friend");
	String	  friend_list = "";
	StringBuffer sb = new StringBuffer();
		for(String friend : friend_array) {
		sb.append(friend);
		sb.append(" ");				
		}//end_for
	friend_list = sb.toString().trim();
		if(friend_list.isEmpty()) {
			friend_list="친구없음";
		}//end_if
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
			<style type="text/css">
			#box{
				widows: 500px;
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
			<caption>::::회원가입정보::::</caption>
			<tr>
				<th class="table-primary">이름</th>
				<td><%=name %></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><%=id %></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><%=pwd %></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><%=gender %></td>
			</tr>
			<tr>
				<th>혈액형</th>
				<td><%=blood %></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><%=hobby_list %></td>
			</tr>
			<tr>
				<th>친구</th>
				<td><%=friend_list%></td>
			</tr>
			<tr>
				<th>자기소개</th>
				<td><%=profile %></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="다시하기" onclick="location.href='member_input_form.html'">
				</td>
			</tr>
		</table>
	</div>



</body>
</html>