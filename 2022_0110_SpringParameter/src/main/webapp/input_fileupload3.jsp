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

	
	if(title==''){
		alert("제목을 입력하세요");
		f.title.value='';
		f.title.focus();
		return;
	}
	if(document.getElementById("photo").files.length==0){
		alert("업로드할 파일을 선택하세요");
		return;
	}
	
	f.action ="multi_fileupload2.do";
	f.submit();
}



</script>
</head>
<body>
	<form method="POST" enctype="multipart/form-data">
		제목 :<input name="title"><br>
		사진 :<input type="file" name="photo" id="photo" multiple="multiple"><br>		
		<input type="button" value="멀티파일받기"  onclick="send(this.form)">

	
	</form>
</body>
</html>