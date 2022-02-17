<%@page import="myutil.Jumin"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    //JuminAction Servelet이 request,response를 넘겨줬다
    //request내에 저장된 데이터 존재한다->request내의 데이터을 읽어온다
    String jumin_no 	= (String)request.getAttribute("jumin_no");
    int  year       	= (Integer)request.getAttribute("year");
    int  age        	= (Integer)request.getAttribute("age");
    String tti			= (String)request.getAttribute("tti");
    String season		= (String)request.getAttribute("season");
    String ganji		= (String)request.getAttribute("ganji");
    String gender		= (String)request.getAttribute("gender");
    String local		= (String)request.getAttribute("local");
  
    
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap 설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



<style type="text/css">
  #box{
     width: 500px;
     margin: auto;
     margin-top:50px; 
     border: 1px solid gray;
     box-shadow: 3px 3px 5px black;
  }
</style>
</head>
<body>
   <div id="box">
        <table class="table table-striped table-hover">
            <tr>
                <th>주민번호</th>
                <td>${ requestScope.jumin_no }</td>
            </tr>
            <tr>
                <th>출생년도</th>
                <td>${year}(${ganji})</td>
            </tr>
            <tr>
                <th>나이</th>
                <td>${age}</td>
            </tr>
            <tr>
                <th>띠</th>
                <td>${tti}</td>
            </tr>
            <tr>
                <th>출생계절</th>
                <td>${season }</td>
            </tr>
            <tr>
                <th>출생지역</th>
                <td>${local }</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${gender }</td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="jumin.html">다시하기</a>
                </td>
            </tr>
        </table>    
   </div>

</body>
</html>