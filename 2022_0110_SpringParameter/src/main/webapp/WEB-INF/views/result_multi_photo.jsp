<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

제목 : ${vo.title }
<img src="resources/upload/${vo.filename1}" width="300">
<img src="resources/upload/${vo.filename2}" width="300">
<a href="input_fileupload.jsp">다시하기</a>
</body>
</html>