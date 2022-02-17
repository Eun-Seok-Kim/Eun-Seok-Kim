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
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- daum 우편번호 api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style type="text/css">
	#box{
	    width: 800px;
	    margin: auto;
	    margin-top: 50px; 
	}
	#m_id_message{
		color: red;
	}
</style>
<script type="text/javascript">

$(document).ready(function(){
	$("#m_id").keyup(function(event){
		var m_id = $(this).val();
		console.log(m_id);
		
		if(m_id.length<3){
			$("#m_id_message").html("아이디는 3자리이상 입력하세요");
			return;
		}
		//ajax 로 m_id 서버로 전송 및 사용여부가능 체크
		$.ajax({
			url		: 'check_id.do',	//MemberCheckIdAction
			data	: {'m_id':m_id},	//parameter	
			dataType: 'json',			//서버로부터 받을 Data 타입
			success	: function(result_data){
				if(result_data.result){
					$("#m_id_message").html("사용가능한 아이디").css("color","blue");
					//등록버튼 활성화
					$("#bt_register").attr("disabled",false);
				}else{
					$("#m_id_message").html("이미사용중인 아이디").css("color","red");
					$("#bt_register").attr("disabled",true);
				}
				
			},
			error	: function(err){
				alert(err.responseText);
			}
		});
		
	});
	
});//아이디 중복체크 끝
	//주소찾기
	function find_addr(){
		var width = 500; //팝업의 너비
		var height = 600; //팝업의 높이
	    new daum.Postcode({
	        width: width,
	        height: height,
	        oncomplete: function(data) {
				//data={"zonecode":12345}
	         	//선택주소의 우편번호와 주소값 넣어주기
	         	$("#m_zipcode").val(data.zonecode);
				$("#m_addr").val(data.address);
	        }
	    }).open({
	        left: (window.screen.width / 2) - (width / 2),
	        top: (window.screen.height / 2) - (height / 2)
	    	
	    });
	
}//주소찾기
//회원가입 버튼 클릭
function send(f) {
	var m_name	= f.m_name.value.trim();
	var m_pwd		= f.m_pwd.value.trim();
	var m_zipcode	= f.m_zipcode.value;
	var m_addr	= f.m_addr.value;
	   if(m_name==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '이름을 입력하세요',
			   text: '이름이 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_name.value='';
					  f.m_name.focus();
				  }
			});   
	   return;
	   }
	   if(m_pwd==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '비밀번호를 입력하세요',
			   text: '비밀번호가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_pwd.value='';
					  f.m_pwd.focus();
				  }
			});   
	   return;
	   }   
	   if(m_zipcode==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '우편번호를 입력하세요',
			   text: '우편번호가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_zipcode.value='';
					  f.m_zipcode.focus();
				  }
			});   
	   return;
	   }
	   if(m_addr==''){
		   //alert('이름을 입력하세요');
		   Swal.fire({
			   icon: 'error',
			   title: '주소를 입력하세요',
			   text: '주소가 비어있습니다',
			   confirmButtonText:'확인',
			   returnFocus:false
		   }).then((result) => {
				  if (result.isConfirmed) {
					  f.m_addr.value='';
					  f.m_addr.focus();
				  }
			});   
	   return;
	   }
	   f.action="insert.do";
	   f.submit();
}
</script>

</head>
<body>
<form>
  <div id="box">
   		<div class="panel panel-primary">
	      <div class="panel-heading"><h4>회원가입</h4></div>
	      <div class="panel-body">
	          <table class="table table-striped">
	               		<tr>
		      				<th>이름</th>
		      				<td><input name="m_name"></td>
		      			</tr>
		      			<tr>
		      				<th>아이디</th>
		      				<td><input name="m_id" id="m_id">
		      				<span id="m_id_message"></span>
		      				</td>
		      			</tr>
		      			<tr>
		      				<th>비밀번호</th>
		      				<td><input type="password" name="m_pwd"></td>
		      			</tr>
		      			<tr>
		      				<th>우편번호</th>
		      				<td><input name="m_zipcode" id="m_zipcode">
		      				<input type="button" value="주소찾기" onclick="find_addr();">
		      				</td>
		      			</tr>
		      			<tr>
		      				<th>주소</th>
		      				<td><input name="m_addr" id="m_addr" size="60"></td>
		      			</tr>
		      			<tr>
		      				<td colspan="2" align="center"><input class="btn btn-success" type="button" value="회원가입" id="bt_register"
		      							disabled="disabled"
		      							onclick="send(this.form);">
		      					<input class="btn btn-primary" type="button" value="취소"
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