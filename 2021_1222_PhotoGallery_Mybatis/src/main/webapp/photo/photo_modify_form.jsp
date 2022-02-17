<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- SweetAlert2사용설정  -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style type="text/css">
  #box{
      width: 600px;
      margin: auto;
      margin-top: 50px;  
  }

  textarea {
	  width: 100%;
	  resize: none;
  }
  
  input[type='button']{
      width: 100px;
  }
   img{
       width: 400px;
       height: 400px;
       border: 1px solid black;
       outline: 1px solid gray;
   }
</style>


<script type="text/javascript">
   
   function send(f){
	   
	   //입력값 체크....
	   
	   var p_subject = f.p_subject.value.trim();
	   var p_content = f.p_content.value.trim();
	   
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
	   
	   f.action = "modify.do"; 
	   f.submit();
	   
   }
 

</script>



</head>
<body>
<form>
  
  <input type="hidden"  name="p_idx"  value="${ vo.p_idx }">

  <div id="box">
        <div class="panel panel-primary">
	      <div class="panel-heading"><h4>사진수정</h4></div>
	      <div class="panel-body">
	          <table class="table  table-striped">
	               <tr>
	                  <td colspan="2" align="center">
	                  	<img src="../upload/${vo.p_filename}">
	   					<br>
	   					 <input class="btn btn-info" type="button" value="편집" >
	                  </td>
	               </tr>
	               <tr>
	                  <th>제목</th>
	                  <td><input name="p_subject" value="${vo.p_subject}"></td>
	               </tr>
	               
	               <tr>
	                  <th>내용</th>
	                  <td>
	                      <textarea  name="p_content"  rows="5" cols="">${vo.p_content }</textarea>
	                  </td>
	               </tr>
	               <tr>
	                   <td colspan="2" align="center">
	                       <input class="btn  btn-primary" type="button"  value="수정하기" 
	                              onclick="send(this.form);">
	                       <input class="btn  btn-success" type="button"  value="메인으로" 
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