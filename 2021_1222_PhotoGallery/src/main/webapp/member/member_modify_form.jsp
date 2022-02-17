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

<!-- 다음 우편번호검색 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- SweetAlert2 사용설정 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style type="text/css">

	#box{
		width: 800px;
		margin: auto;
		margin-top: 50px;
	}
	

</style>

<script type="text/javascript">

	function send(f) {
		var m_idx     = f.m_idx.value;
		var m_name    = f.m_name.value.trim();
		var m_id      = f.m_id.value.trim();
		var m_pwd     = f.m_pwd.value.trim();
		var m_zipcode = f.m_zipcode.value.trim();
		var m_addr    = f.m_addr.value.trim();
		var m_grade   = f.m_grade.value;
		

		if(m_name==''){

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
		
	
		f.action="modify.do" //MemberModifyAction
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
	
	//jQuery초기화
	$(document).ready(function(){
		
		//회원구분 값 설정
		$("select[name='m_grade']").val('${ vo.m_grade }');
		
		
	});
	

</script>

</head>
<body>

<form>
   <input type="hidden" name="m_idx" value="${ vo.m_idx }">
   <div id='box'>
		<div class="panel panel-primary">
			<div class="panel-heading"><h4>회원수정</h4></div>
			<div class="panel-body">

				<table class="table table-striped">
					<tr>
						<th>이름</th>
						<td><input name="m_name" value="${ vo.m_name }"></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td><input name="m_id" value="${ vo.m_id }" readonly="readonly"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input name="m_pwd" type="password" value="${ vo.m_pwd }"></td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>
							<input name="m_zipcode" type="text" id="m_zipcode" placeholder="우편번호" value="${ vo.m_zipcode }">
							<input class="btn btn-success" type="button" value="주소찾기" onclick="find_addr();">
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input name="m_addr" type="text" size="70" id="m_addr" placeholder="주소" value="${ vo.m_addr }"></td>
					</tr>
					<tr>
						<th>회원구분</th>
						<td>
							<select name="m_grade">
								<option value="일반">일반</option>
								<option value="관리자">관리자</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="btn btn-info" type="button" value="회원수정" 
							       onclick="send(this.form);">
					        <input class="btn btn-info" type="button" value="목록보기" 
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