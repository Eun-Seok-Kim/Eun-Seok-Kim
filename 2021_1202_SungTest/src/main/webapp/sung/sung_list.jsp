<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
	width: 900px;
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

#empty_msg{
	text-align: center;
	color: red;
	font-weight: bold;
		}
		th,td{
			text-align: center;
		}
</style>
<script type="text/javascript">

	function del(idx) {
		/* alert('idx='+ idx + ' Data 삭제합니다.') */
		//삭제여뷰 확인
		if(confirm("정말 삭제하시겠습니까?")==false){
			return;			
		}
		//자바스크립트를 이용해서 서블릿 호출 : SungDeleteAction
		location.href = "delete.do?idx=" + idx;
			
	}
	
	
	function modify_form(idx) {
		//수정폼 띄우기
		location.href = "modify_form.do?idx="+idx;
	}
	
	function del_all(f) {
		//체크항목
		if($('input:checkbox[name=idx]:checked').length==0){
			alert('삭제할 항목을 선택하세요.')
			return;
		}
		
		f.action="delete_all.do;" //SungDeleteAllAction
		f.submit(;)
	}
	$(document).ready(function() {
		$("check_all").click(function(){
			var checked = $(this).is(":checked");
			if(checked==true){
				alert('모두 체크')
				$("input:checkbox[name='idx']").prop("checked",true);
			}else{
				alert('모두 체크해제')	
				$("input:checkbox[name='idx']").prop("checked",false);
			}
			
		});
	});
	
</script>
		<meta charset="UTF-8">
		<title>Insert title here</title>

	</head>
<body>
<form>
	<div id="box">
		<h1>::::성적관리::::</h1>	
		<div style="text-align: right; margin-bottom: 10px;">
		<input class="btn btn-primary" type="button" value="등록하기"
				onclick="location.href='insert_form.do'">
		</div>
		
		<div>
			<input type="checkbox" id="check_all">전체&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="선택삭제" onclick="del_all(this.form);">
		</div>
			<div>
				<table class="table table-hover table-striped">
					<!-- title -->
					<tr class="table-primary">
						<th>번호</th>
						<th>이름</th>
						<th>국어</th>
						<th>영어</th>
						<th>수학</th>
						<th>총점</th>
						<th>평균</th>
						<th>등수</th>
						<th width="150">관리</th>
					</tr>
					<!-- Data가 없을경우 -->
					<c:if test="${empty requestScope.list}">
						<tr>
							<td colspan="9">
								<div id="empty_msg">데이터가 없습니다.</div>
							</td>
						</tr>
					</c:if>
					<!-- Data가 있는경우 -->
					<!-- for(SungVo vo : list) 동일 -->
					<c:forEach var="vo" items="${requestScope.list}">
						<tr>
						<td><input type="checkbox" name="idx" value="${vo.idx }">&nbsp;&nbsp;${vo.idx }</td>
							<td>${ vo.name}</td>
							<td>${ vo.kor}</td>
							<td>${ vo.eng}</td>
							<td>${ vo.mat}</td>
							<td>${ vo.tot }</td>
							<td>${ vo.avg }</td>
							<td>${ vo.rank }</td>
							<td align="center">
							<input class="btn btn-info" type="button" value="수정" onclick="modify_form('${vo.idx}');">
							<!--
																						삭제를 위한 del()홤수 호출
							-->
							<input class="btn btn-danger" type="button" value="삭제" onclick="del('${vo.idx}');">
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>	
	</div>
</form>	
</body>
</html>