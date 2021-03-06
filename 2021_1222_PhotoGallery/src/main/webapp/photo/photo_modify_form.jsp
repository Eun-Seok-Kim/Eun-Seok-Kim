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
	
	#photo{
		width: 200px;
		height:200px;
		text-align: center;
		
	}
	
	#img{
		width: 200px;
		height: 200px;
	
	}

</style>

<script type="text/javascript">

	function send(f) {
		var p_idx     = f.p_idx.value;
		var p_subject = f.p_subject.value.trim();
		var p_content = f.p_content.value.trim();
		
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
			

		f.action = "modify.do";  //PhotoModifyAcion
		f.submit();
		
		//location.href="modify.do/p_idx=" + p_idx + "&p_subject=" + p_subject + "&p_content=" + p_content + "&m_idx=" + m_idx;
	}
	
	
	//파일 업로드
	function ajaxFileUpload() {
       // 업로드 버튼이 클릭되면 파일 찾기 창을 띄운다.
       $("#ajaxFile").click();
   }

   function ajaxFileChange() {
       // 파일이 선택되면 업로드를 진행한다.
       photo_upload();
   }


   
   function photo_upload() {

	   var form = jQuery("ajaxForm")[0];
       var formData = new FormData(form);
       
       //photo_upload?p_idx=22&photo=aa.png
       formData.append("p_idx", '${ vo.p_idx }');
       formData.append("photo", $("#ajaxFile")[0].files[0]);

       $.ajax({
             url : "photo_upload.do", //PhotoUploadAction
             type : "POST",
             data : formData,
             processData : false,
             contentType : false,
             dataType : 'json',
             success:function(result_data) {
            	 location.href='';
             },
             error : function(err){
            	 alert(err.responseText);
             }
       });
   }
   
</script>
</head>
<body>


<!--화일업로드용 폼  -->
<form enctype="multipart/form-data" id="ajaxForm" method="post">
    <input type="file" id="ajaxFile" style="display:none;"  onChange="ajaxFileChange();">
</form>


<form>
  <input type="hidden" name="p_idx" value="${ vo.p_idx }">
  <div id="box">
		<div class="panel panel-primary">
			<div class="panel-heading"><h4>수정하기</h4></div>
			<div class="panel-body">
				<table class="table table-striped">
				
					<tr>
						<td colspan="2" align="center">
	                       <img id="img" src="../upload/${ vo.p_filename }">
	                       <br>
	                       <input  class="btn  btn-info" type="button"  value="이미지편집"  onclick="ajaxFileUpload();">
	                   </td>
					</tr>
				
					<tr>
						<th>제목</th>
						<td><input name="p_subject" value="${ vo.p_subject }"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea name="p_content" rows="5">${ vo.p_content }</textarea>
						</td>
					</tr>

					
					<tr>
						<td colspan="2" align="center">
							<input class="btn btn-primary" type="button" value="수정하기" 
							       onclick="send(this.form);">
							<input class="btn btn-success" type="button" value="메인으로" 
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