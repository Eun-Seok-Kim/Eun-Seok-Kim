<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap 설정 4 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Bootstrap 설정 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- SweetAlert2사용설정  -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


  
<style type="text/css">
   #box{
       width: 400px;
       margin: auto;
       margin-top: 50px;
       /* border: 1px solid gray; */
       /* box-shadow: 1px 1px 2px black; */
   } 

</style>

<script type="text/javascript">

   var regular_number = /^[0-9]{1,3}$/;

   function send(f){
	   
	   var name = f.name.value.trim();
	   var kor  = f.kor.value;
	   var eng  = f.eng.value;
	   var mat  = f.mat.value;
	   
	   
		 
	   if(name==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '이름을 입력하세요',
			   text: '이름이 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.name.value='';
					  f.name.focus();
				  }
			});   
		   
		   
			  
		   return;
	   }
	   
	   //0~100사이의 숫자 체크
	   //국어
	   if(regular_number.test(kor)==false || kor<0 || kor>100 ){
		   //alert('국어점수는 0~100사이의 숫자만 입력하세요');
		   Swal.fire({
			   icon: 'info',
			   title: '국어점수를 입력하세요',
			   html: '<h5><b><font color=red>점수의 범위는 0~100입니다</font></b></h5>',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.kor.value='';
					  f.kor.focus();
				  }
			}); 
		   
		   return;
	   }
	   //영어
	   if(regular_number.test(eng)==false || eng<0 || eng>100 ){
		   //alert('영어점수는 0~100사이의 숫자만 입력하세요');
		   Swal.fire({
			   icon: 'info',
			   title: '영어점수를 입력하세요',
			   html: '<h5><b><font color=red>점수의 범위는 0~100입니다</font></b></h5>',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.eng.value='';
					  f.eng.focus();
				  }
			}); 
		   
		   return;
	   }
	   //수학
	   if(regular_number.test(mat)==false || mat<0 || mat>100 ){
		   //alert('수학점수는 0~100사이의 숫자만 입력하세요');
		   Swal.fire({
			   icon: 'info',
			   title: '수학점수를 입력하세요',
			   html: '<h5><b><font color=red>점수의 범위는 0~100입니다</font></b></h5>',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.mat.value='';
					  f.mat.focus();
				  }
			}); 
		   
		   
		   return;
	   }
	   
	   f.action="insert.do";//전송대상  SungInsertAction call
	   
	   f.submit();//전송
	   
	   
   }


</script>



</head>
<body>
   <form>
	  <div id="box">
	      
	      <div class="panel panel-primary">
		      <div class="panel-heading"><h4>성적등록하기</h4></div>
		      <div class="panel-body">
		      		<table class="table  table-striped">
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
		                   <td colspan="2" align="center">
		                        <input  class="btn  btn-primary"  type="button"  value="등록하기" 
		                                onclick="send(this.form);"> 
		                        <input  class="btn  btn-success"  type="button"  value="목록보기" 
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