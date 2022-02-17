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

<!-- 다음 우편번호 검색 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- SweetAlert2 사용설정 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


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
	//현재 html문서가 배치완료되면
	$(document).ready(function(){
		
		$("#m_id").keyup(function(event){
			
			var m_id = $(this).val();
			console.log(m_id);
			
			if(m_id.length<3){
				$("#m_id_message").html("아이디는 3자리 이상 영문자/숫자조합 이어야 합니다").css("color", "red");
				//등록버튼을 사용불가능하도록 설정  attr은 Attribute
				$("#bt_register").attr("disabled", true);
				return;
			}
			
			//$("#m_id_message").html(""); //메시지 지우기 서버 갔다오면 자동으로 지워지긴 함
			
			//ajax를 이용해서 m_id를 서버로 전송해서 사용가능여부 체크
			$.ajax({
				url      : 'check_id.do',     //MemberCheckIdAction
				data     : {'m_id' : m_id },   //parameter  => check_id.do?m_id=hong 요청
				datatype : 'json',            //서버로부터 받을 데이터 타입
				success  : function(result_data){
					// result_data = { "result" : true  } <-사용가능한 경우
					// result_data = { "result" : false } <-사용불가능한경우(이미 사용중...)
					
					if(result_data.result){
						//사용가능한 경우
						$("#m_id_message").html("사용가능한 아이디 입니다").css("color", "blue");
						
						//등록버튼을 사용가능하도록 설정
						$("#bt_register").attr("disabled", false);
						
					}else{
						//사용불가능한 경우
						$("#m_id_message").html("이미 사용중인 아이디 입니다").css("color", "red");
						
						//등록버튼을 사용불가능하도록 설정
						$("#bt_register").attr("disabled", true);
					}

				},
				error    : function(err){
					alret(err.responseText);
				} 
				
			});
			
		});
		
	});

	
	//회원가입버튼 클릭시
	function send(f) {
		//입력값 체크     아이디는 앞에서 체크함,,
		var m_name    = f.m_name.value.trim();
		var m_pwd     = f.m_pwd.value.trim();
		var m_zipcode = f.m_zipcode.value.trim();
		var m_addr    = f.m_addr.value.trim();
		
		if(m_name==''){
			/* alert('이름을 입력하세요');
			f.m_name.value='';
			f.m_name.focus(); */
			
			Swal.fire({
				  icon: 'info',
				  title: '이름을 입력하세요',
				  text: '이름이 비어있습니다',
				  confirmButtonText: '확인',
				  returnFocus: false
				  }).then((result) => {	
						// result = { "isComrirmed" , true }
						if (result.isConfirmed) {
							//확인버튼을 누르면
							f.m_name.value='';
							f.m_name.focus();
						}
				  }) 
			
			return;
		}
			
		if(m_pwd==''){
			/* alert('비밀번호를 입력하세요');
			f.m_pwd.value='';
			f.m_pwd.focus();  */
				
			Swal.fire({
				  icon: 'info',
				  title: '비밀번호를 입력하세요',
				  text: '비밀번호가 비어있습니다',
				  confirmButtonText: '확인',
				  returnFocus: false
				  }).then((result) => {	
						// result = { "isComrirmed" , true }
						if (result.isConfirmed) {
							//확인버튼을 누르면
							f.m_pwd.value='';
							f.m_pwd.focus();
						}
				  }) 
				
			return;
			
		}
		
		if(m_zipcode==''){
			/* alert('우편번호를 입력하세요');
			f.m_zipcode.value='';
			f.m_zipcode.focus(); */
			
			Swal.fire({
				  icon: 'info',
				  title: '우편번호를 입력하세요',
				  text: '우편번호가 비어있습니다',
				  confirmButtonText: '확인',
				  returnFocus: false
				  }).then((result) => {	
						// result = { "isComrirmed" , true }
						if (result.isConfirmed) {
							//확인버튼을 누르면
							f.m_zipcode.value='';
							f.m_zipcode.focus();
						}
				  }) 
				
			return;
		}
		
		if(m_addr==''){
			/* alert('주소를 입력하세요');
			f.m_addr.value='';
			f.m_addr.focus(); */
			
			Swal.fire({
				  icon: 'info',
				  title: '주소를 입력하세요',
				  text: '주소가 비어있습니다',
				  confirmButtonText: '확인',
				  returnFocus: false
				  }).then((result) => {	
						// result = { "isComrirmed" , true }
						if (result.isConfirmed) {
							//확인버튼을 누르면
							f.m_addr.value='';
							f.m_addr.focus();
						}
				  }) 
			
			return;
		}
		
		f.action="insert.do"  //MemberInsertAction
		f.submit();
	}
	

	//주소찾기 함수
	function find_addr() {
		
		var width = 500; //팝업의 너비
		var height = 600; //팝업의 높이
		
        new daum.Postcode({
        	 width: width, //생성자에 크기 값을 명시적으로 지정해야 합니다.
        	 height: height,
             oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // data = {"zonecode":13494, "address":"서울시 관악구...", "roadAddress":"서울시 관악구..", "jibunAddress":"서울시 관악구.."}
                //선택주소의 우편번호와 주소를 넣어준다
                $("#m_zipcode").val(data.zonecode);
                $("#m_addr").val(data.address);
                

            }
        }).open({
        	//화면정중앙에 띄워라
        	left: (window.screen.width / 2) - (width / 2),
            top: (window.screen.height / 2) - (height / 2)
        	
        });
    }
	


</script>

</head>
<body>

<form>
   <div id='box'>
		<div class="panel panel-primary">
			<div class="panel-heading"><h4>회원가입</h4></div>
			<div class="panel-body">
				<form>
				<table class="table table-striped">
					<tr>
						<th>이름</th>
						<td><input name="m_name"></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>
							<input name="m_id" id="m_id">
							<span id="m_id_message"></span>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input name="m_pwd" type="password"></td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>
							<input name="m_zipcode" type="text" id="m_zipcode" placeholder="우편번호">
							<input class="btn btn-success" type="button" value="주소찾기" onclick="find_addr();">
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input name="m_addr" type="text" size="70" id="m_addr" placeholder="주소"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="btn btn-primary" type="button" value="회원가입" id="bt_register" disabled="disabled"
							       onclick="send(this.form);">
					        <input class="btn btn-success" type="button" value="메인보기" 
							       onclick="location.href='../photo/list.do'">
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</form>


</body>
</html>