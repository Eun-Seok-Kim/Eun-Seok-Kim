<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap 설정 4-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- SweetAlert2사용설정  -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<style type="text/css">
   #box{
      width: 900px;
      margin: auto;
      margin-top: 50px;
      /* background: #cccccc; */
   }
   
   #box > h1{
       text-align: center;
       
       font-size: 35px;
       font-weight: bold;
       font-family: 굴림체,궁서체;
       
       color: #5588cc;
       text-shadow: 1px 1px 1px black;
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

  function del(idx){
	  
	  //삭제확인 : 확인(true)  취소(false)
	  //if(confirm("정말 삭제하시겠습니까?")==false) return;
	  
	  //alert('idx=' + idx + '  Data 삭제');
	  
	  //자바스크립트을 이용해서 서블릿 호출  : SungDeleteAction
	  //location.href = "delete.do?idx=" + idx;
	  
	  
	  Swal.fire({
		  title: '정말 삭제하시겠습니까?',
		  text: "선택한 성적을 삭제합니다",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '삭제',
		  cancelButtonText:  '취소'
		}).then((result) => {
		
		  //삭제(confirm)버튼 클릭시	
		  if (result.isConfirmed) {
			//자바스크립트을 이용해서 서블릿 호출  : SungDeleteAction
			location.href = "delete.do?idx=" + idx;
		  }
		});
	  
  }//end - del
  
  
  function modify_form(idx){
	  
	  //수정폼 띄우기
	  location.href="modify_form.do?idx=" + idx; // SungModifyFormAction
  }
  
  function del_all(f){
	  
	  //체크항목 체크
	  if( $("input:checkbox[name='idx']:checked").length ==0 ){
		  
		  //alert('삭제할 항목을 선택하세요');
		  Swal.fire({
			   icon: 'info',
			   title: '삭제할 항목을 선택하세요',
			   text: '...',
			   confirmButtonText:'확인',
			  
		   });
		  return;
	  }
	  
	  
	  Swal.fire({
		  title: '정말 삭제하시겠습니까?',
		  text: "선택한 성적을 삭제합니다",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '삭제',
		  cancelButtonText:  '취소'
		}).then((result) => {
		
		  //삭제(confirm)버튼 클릭시	
		  if (result.isConfirmed) {
			  
			  f.action="delete_all.do"; // SungDeleteAllAction
			  f.submit();
			  
		  }
		});
	  
	  
	  
	 
  }

  
  /* 전체선택시  */
  $(document).ready(function(){
	  
	  //전체선택 체크박스가 클릭되면
	  $("#check_all").click(function(){
		  //전체 체크박스 체크여부
		  var checked = $(this).is(":checked");
		 
		  if(checked){
			  //alert('아래있는 체크박스 모두 체크');
			  $("input:checkbox[name='idx']").prop("checked", true);
		  }else{
			  //alert('아래있는 체크박스 모두 체크해제');
			  $("input:checkbox[name='idx']").prop("checked", false);
		  }
	  });
	  
	  //아래쪽 체크박스 클릭되면
	  $("input:checkbox[name='idx']").click(function(){
		  //전체체크시 위의 전체체크박스 체크..
		  
		  var all = true;
		  //각각의 체크박스 체크상태 체크
		  $("input:checkbox[name='idx']").each(function(){
			  
			  if($(this).is(":checked")==false)
				  all = false;
		  });
		  
		  //전체 체크상태지정
		  $("#check_all").prop("checked", all);
		  
	  });
	  
	  
  });
  

</script>




</head>
<body>
<form>
   <div id="box">
       <h1>::::성적관리::::</h1>
       <div style="text-align: right; margin-bottom: 10px;">
          <input  class="btn  btn-primary"  type="button"  value="등록하기" 
                  onclick="location.href='insert_form.do'">
       </div> 
       
       <!-- 체크박스 작업 -->
       <div style="margin-bottom: 10px;">
            <input type="checkbox"  id="check_all" >전체 &nbsp;&nbsp;&nbsp;&nbsp;
            <input class="btn btn-danger"  type="button"  value="선택삭제"  onclick="del_all(this.form);">
       </div>
       
       <!-- Data출력  -->
       <div>
           <table class="table  table-hover table-striped">
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
                	<th>편집</th>
               </tr> 
               
               
               <!-- Data가 없을경우 -->
               <c:if test="${ empty requestScope.list }">
                  <tr>
                      <td colspan="9">
                         <div id="empty_msg">데이터가 없습니다</div>
                      </td>
                  </tr>
               
               </c:if>
               
               
               <!-- Data가 있는경우 -->
               <!-- for(SungVo vo : list) 동일함 -->
               <c:forEach var="vo"  items="${ requestScope.list }">
                  <tr>
                      <td>
                           <input type="checkbox"  name="idx" value="${ vo.idx }"> &nbsp;&nbsp;
                           ${ vo.idx }
                      </td>
                      <td>${ vo.name }</td>
                      <td>${ vo.kor }</td>
                      <td>${ vo.eng }</td>
                      <td>${ vo.mat }</td>
                      <td>${ vo.tot }</td>
                      <td>${ vo.avg }</td>
                      <td>${ vo.rank }</td>
                      <td align="center">
                          <input  class="btn btn-info"    type="button"  value="수정" onclick="modify_form('${ vo.idx}');">
                          <input  class="btn btn-danger"  type="button"  value="삭제" onclick="del('${ vo.idx }');">
                      </td>
                  </tr>
               </c:forEach>
           </table>
       </div>
   </div>
   
</form>  
</body>
</html>