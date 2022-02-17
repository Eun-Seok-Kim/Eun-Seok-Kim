<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap 3버젼 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- SweetAlert2 사용설정 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style type="text/css">

	#box{
		width: 600px;
		margin: auto;
		margin-top: 50px;
	}
	
	textarea{
		width: 100%;
		resize: none;
	}
	
	input[type='button']{
		width: 100px;
	}

</style>

<script type="text/javascript">

	function send(f) {
		
		var p_subject = f.p_subject.value.trim();
		var p_content = f.p_content.value.trim();
		var photo     = f.photo.value;
		
		//입력값 체크
		if(p_subject==''){
			alert('제목을 입력하세요');
			f.p_subject.value='';
			f.p_subject.focus();
			return;
		}
		
		if(p_content==''){
			alert('내용을 입력하세요');
			f.p_content.value='';
			f.p_content.focus();
			return;
		}
		
		if(photo==''){
			alert('사진을 선택하세요');
			f.photo.value='';
			f.photo.focus();
			return;
		}
		

		f.action = "insert.do";  //PhotoInsertAcion
		f.submit();
	}

</script>

</head>
<body>

<form method="post" enctype="multipart/form-data">

  <input type="hidden" name="m_idx" value="${ user.m_idx }">

  <div id="box">
		<div class="panel panel-primary">
			<div class="panel-heading"><h4>사진등록</h4></div>
			<div class="panel-body">
				<table class="table table-striped">
					<tr>
						<th>제목</th>
						<td><input name="p_subject"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea name="p_content" rows="5" cols=""></textarea>
						</td>
					</tr>
					<tr>
						<th>사진</th>
						<td><input type="file" name="photo"></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input class="btn btn-primary" type="button" value="사진올리기" 
							       onclick="send(this.form);">
							<input class="btn btn-success" type="button" value="메인으로.." 
							       onclick="location.href='list.do'">
						</td>
					</tr>
				
				</table>
			</div>
		</div>
  </div>
</form>

</body>
</html>