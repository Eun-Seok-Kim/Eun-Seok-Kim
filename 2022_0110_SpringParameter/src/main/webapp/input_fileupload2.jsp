<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">


function send(f) {
		
	var title = f.title.value.trim();
	var photo1 = f.photo[0].value;
	var photo2 = f.photo[1].value;
	if(title==''){
		alert("제목을 입력하세요");
		f.title.value='';
		f.title.focus();
		return;
	}
	if(photo1==''){
		alert("사진1을 선택하세요");
		return;
	}
	if(photo2==''){
		alert("사진2을 선택하세요");
		return;
	}
	
	
	f.action ="multi_fileupload1.do";
	f.submit();
}



</script>
</head>
<body>
	<form method="POST" enctype="multipart/form-data">
		제목 :<input name="title"><br>
		사진1 :<input type="file" name="photo"><br>
		사진2 :<input type="file" name="photo"><br>			
		<input type="button" value="멀티파일받기"  onclick="send(this.form)">

	
	</form>
</body>
</html>