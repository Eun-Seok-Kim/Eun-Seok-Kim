<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">

	#bmkplace_title{
		text-align: center;
		
		font-size: 20px;
		font-weight: bold;
		
		margin-bottom: 20px;
	}

	th{
		text-align: center;
	}
	
	#bmkplace_data_align > td{
		text-align: center;
		vertical-align: middle;
	}

</style>


<script type="text/javascript">
	
	function bmkplace_delete(f) {
		
		if(confirm('정말 삭제하시겠습니까?')==false) return;
		
		f.action = "bmkplace_delete.do";
		f.submit();
		
	}
	
	$(document).ready(function(){
		
		//전체선택 체크박스 선택 시
		$("#check_all").click(function(){
			var checked = $(this).is(":checked");
			if(checked){
				$("input:checkbox[name='check_box']").prop("checked", true);
			}else{
				$("input:checkbox[name='check_box']").prop("checked", false);
			}
		});
		
		//아래쪽 체크박스 클릭 시
		$("input:checkbox[name='check_box']").click(function(){
			var all = true;
			
			$("input:checkbox[name='check_box']").each(function(){
				if($(this).is(":checked")==false)
					all = false;
			});
			$("#check_all").prop("checked", all);
		});
		
	});
	
	function del_all(f) {
		
		if($("input:checkbox[name='check_box']:checked").length==0){
			alert("삭제할 항목을 선택하세요");
			return;
		}
		
		if(confirm('정말 삭제하시겠습니까?')==false) return;
		
		f.action="bmkplace_del_all.do";
		f.submit();

	}

</script>

</head>

<body>


<div>
<h1 id="bmkplace_title">캠핑용품 즐겨찾기</h1>

<form>
 	<table id="bmkgoods_data_align" class="table">
		<tr>
			<td colspan="6">
				<input type="checkbox" id="check_all">전체&nbsp;&nbsp;
				<input type="button" value="선택삭제" onclick="del_all(this.form);">
			</td>
		</tr>
		<tr class="success">
			<th>선택</th>
			<th>사진</th>
			<th>캠핑장명</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>삭제</th>
		</tr>
		
		<!-- 장바구니가 비어있으면 -->
		<c:if test="${ empty list }">
			<tr>
				<td colspan="6" align="center">
					<b>즐겨찾기가 비었습니다.</b>
				</td>
			</tr>
		</c:if>	
		
		<!-- 통화단위 : 지역지정  크롬브라우저는 안해도 적용되는데 웨일브라우저 등은 안됨 -->
		<fmt:setLocale value="ko_kr"/>
		
		
		<!-- for(CartVo vo : list) -->
		<c:forEach var="vo" items="${ list }">
		
			<tr id="bmkplace_data_align">
				<td><input type="checkbox" name="check_box" value="${ vo.bmk_p_idx }"></td>			
				<td>
   		   			<img src="${ pageContext.request.contextPath }/resources/image/${ vo.p_filename }" width="90" height="90">
   		   		</td>
				<td>${ vo.p_name }</td>
				<td>${ vo.p_addr }</td>
				<td>${ vo.p_tel }</td>
				 
				<td>
					<form>
						<input type="hidden" name="bmk_p_idx" value="${ vo.bmk_p_idx }">
						<input type="button" value="삭제" style="border:1 solid black;cursor:hand"
		                       onclick="bmkplace_delete(this.form)">
		            </form>
				</td>
				
			</tr>
			
		</c:forEach>

	</table>
</form>	
</div>
	
</body>
</html>






