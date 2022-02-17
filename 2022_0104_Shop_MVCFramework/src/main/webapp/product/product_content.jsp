<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function cart_insert() {
	//로그인상태 체크
	if("${empty user}"=="true"){
		if(confirm("장바구니담기는 로그인후에 이용가능합니다\n로그인  하시겠습니까?")==false)return;	
		//현재 위치 : alert(location.href);
		//로그인폼으로 이동 : 현재위치를 넘겨주고
		location.href="../member/login_form.do?url="+encodeURIComponent(location.href);
		return;
	}
	//로그인 된 경우
	$.ajax({
		url		:'cart_insert.do',
		data 	:{'p_idx':"${vo.p_idx}",'m_idx':"${user.m_idx}"},
		dataType: 'json',
		success : function(result_data){
			if(result_data.result == "success"){
				if(confirm("상품을 장바구니에 담았습니다.\n장바구니로 이동하시겠습니까?")==false)return;
				location.href="cart_list.do";
				return;
			}
			if(result_data.result == "exist"){
				alert("해당상품이 이미 담겨있습니다.");
			}
		},
		error	: function(err){
			alert(err.responseText);
		}
	});
	
}

</script>
</head>
<body>

<jsp:include page="index.jsp"/>

<table align="center" width="600" border="1"
 style="border-collapse:collapse;font-size:8pt"
 bordercolor="navy" cellpadding="4" cellspacing="0">
		<tr>
			<td width="40%">제품분류</td>
			<td width="60%">${vo.category}</td>
		</tr>
		<tr>
			<td width="40%">제품번호</td>
			<td width="60%">${vo.p_num }</td>
		</tr>
		<tr>
			<td width="40%">제품명</td>
			<td width="60%">${vo.p_name }</td>
		</tr>
		<tr>
			<td width="40%">제조사</td>
			<td width="60%">${vo.p_company }</td>
		</tr>
		<tr>
			<td width="40%">제품가격</td>
			<td width="60%">
				${vo.p_price }원
				(할인가:${vo.p_saleprice }원)
			</td>
		</tr>
		<tr>
			<td colspan="2">제품설명</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
		<img src="../images/${vo.p_image_l }"  width="400">
			</td>
		</tr>
		<tr>
			<td colspan="2">${vo.p_content }</td>
		</tr>
		
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="장바구니에 담기"
				onclick="cart_insert();"/>
				<c:if test="${not empty user }">
				<input type="button" 	value="장바구니 보기"
				onclick="location.href='cart_list.do'"/>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>