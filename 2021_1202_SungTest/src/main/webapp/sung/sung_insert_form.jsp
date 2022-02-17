<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/poper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style type="text/css">
#box{
	width: 350px;
	margin: auto;
	margin-top: 50px;
	/* background: #cccccc; */
	}
#box > h1{
	font-size: 40px;
	font-weight: bold;
	font-family: 굴림체;
	color: green;
	text-align: center;	
		 }
th,td{
	text-align: center;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	var regular_number = /^[0-9]{1,3}$/;

	function send(f) {
		var name = f.name.value.trim();
		var kor  = f.kor.value;
		var eng  = f.eng.value;
		var mat  = f.mat.value;
		
		if(name==''){
			alert('이름을 입력하세요');
			f.name.value='';
			f.name.focus();
			return;
		}//end-name
		if(regular_number.test(kor)==false|| kor<0 || kor>100){
			alert('국어점수 입력값을 확인하세요');
			f.kor.value='';
			f.kor.focus();
			return;		
		}
		if(regular_number.test(eng)==false|| eng<0 || eng>100){
			alert('영어점수 입력값을 확인하세요');
			f.eng.value='';
			f.eng.focus();
			return;		
		}
		if(regular_number.test(mat)==false|| mat<0 || mat>100){
			alert('수학점수 입력값을 확인하세요');
			f.mat.value='';
			f.mat.focus();
			return;		
		}
		
		f.action="insert.do"; //전송대상 SungInsertAction
		f.submit();
		
		
		
		
	}
</script>
</head>
<body>
	<form action="">
		<div id="box">
			<h1>::::성적입력::::</h1>
			<table class="table table-hover" >
				<tr>
					<th>이름</th>
					<td><input name="name"></td>
				</tr>
				<tr>
					<th>국어</th>
					<td><input name="kor"></td>
				</tr>
				<tr>
					<th>영어</th>
					<td><input name="eng"></td>
				</tr>
				<tr>
					<th>수학</th>				
					<td><input name="mat"></td>
				</tr>
				<tr>				
					<td colspan="2">
						<input class="btn btn-info" type="button" value="저장" 
								onclick="send(this.form)">
						<input class="btn btn-primary" type="button" value="취소" 
								onclick="location.href='list.do'">
					</td>
				</tr>
			</table>
		</div>
	</form>	
</body>
</html>