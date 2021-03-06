<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
   #box{
       width: 800px;
       margin: auto;
       margin-top: 20px; 
   }
   
   #content_type{
       width: 100%;
       min-height: 100px; 
       height: auto;
       border: 1px solid #cccccc;
       padding: 5px;
   }
   
   /* 댓글입력창 */
   textarea#comment_content{
       
       width:  80%;
       height: 80px;
       resize: none;
       float:left;
       margin-bottom: 10px;
   }
   
   #comment_button{
       width:  20%;
       height: 80px;
       float:left;
   }
   
   #comment_display{
   
       clear:both;
       margin-top: 50px;
   }
   
   
   
</style>


<script type="text/javascript">
  
  var comment_page=1;
  
  
  function  reply_form(){
	  
	   //로그인 여부 체크
	   if("${ empty user }" == "true"){
		   
		   if(confirm("로그인 하신후 답글쓰기가 가능합니다\n로그인 하시겠습니까?")==false)return;
		   
		   //로그인 폼
		   location.href="${ pageContext.request.contextPath }/member/login_form.do?url=" + 
				                                        encodeURIComponent(location.href);
		   return;
	   }
	   
	   //답글쓰기 폼으로 이동:
	   location.href="reply_form.do?b_idx=${ vo.b_idx }&page=${ param.page }" ;
	   
  }//end:reply_form
  
  
  function board_delete(){
	  
	  if(confirm("정말 삭제 하시겠습니까?")==false) return;
	  
	  //삭제      
	  location.href="delete.do?b_idx=${ vo.b_idx }&page=${ param.page }&search=${ param.search}&search_text=${param.search_text}";
  }
  
  //댓글달기
  function  add_comment(){
	  
	   //로그인 여부 체크
	   if("${ empty user }" == "true"){
		   
		   if(confirm("로그인 하신후 댓글쓰기가 가능합니다\n로그인 하시겠습니까?")==false)return;
		   
		   //로그인 폼
		   location.href="${ pageContext.request.contextPath }/member/login_form.do?url=" + 
				                                        encodeURIComponent(location.href);
		   return;
	   }
	   
	   //입력값 체크..
	   var c_content = $("#comment_content").val().trim();
	   
	   if(c_content==''){
		   alert('댓글을 입력하세요');
		   $("#comment_content").val("");
		   $("#comment_content").focus();
		   return;
	   }
	   
	   
	   //ajax로 전송한다
	   $.ajax({
		   url	: "../comment/insert.do",
		   data : {
			       "c_content" : c_content, 
			       "m_idx":"${ user.m_idx }", 
			       "m_id":"${ user.m_id}",
			       "m_name":"${ user.m_name}",
			       "b_idx" : "${ vo.b_idx }"
		          },
		    dataType: "json",
		    success : function(result_data){
		    	// result_data = {"result":"success"}
		    	// result_data = {"result":"fail"}
		    	
		    	if(result_data.result =='fail'){
		    		alert("댓글쓰기 실패");
		    		return;
		    	}
		    	
		    	//성공했으면
		    	comment_list(1);
		    	
		    	
		    	//입력값 지우기
		    	$("#comment_content").val("");
		    },
		    error   : function(err){
		    	alert(err.responseText);
		    }
	   });

  }
  
  
  //댓글목록 가져오기
  function comment_list(page){
	  
	  comment_page = page;
	  
	  //ajax요청
	  $.ajax({
		  url	: "../comment/list.do",
		  data	: {"b_idx":"${ vo.b_idx }" , "page": page },
		  success: function(result_data){
			  
			  $("#comment_display").html(result_data);
			  
		  },
		  error  : function(err){
			  
			  alert(err.responseText);
			  
		  }
	  });
  }
  
  
  //jQuery초기화
  $(document).ready(function(){
	  
	  comment_list(1);
	  
  });
  
  

</script>

</head>
<body>
  <div id="box">
  	    
  	    <div class="panel panel-primary">
	      <div class="panel-heading"><h3>${ vo.m_name }님의 글</h3></div>
	      <div class="panel-body">
	          <table class="table">
	               
	               <!-- 제목 -->
	               <tr>
	                   <th width="20%">제목</th>
	                   <td>${ vo.b_subject }</td>
	               </tr>
	               
	               <!-- 내용 -->
	               <tr>
	                   <th>내용</th>
	                   <td>
	                      <div id="content_type">${ vo.b_content }</div>
	                   </td>  
	               </tr>
	               
	               <tr>
	                   <th>작성일자</th>
	                   <td>${ vo.b_regdate }</td>
	               </tr>
	               
	               <tr>
	                  <td colspan="2"  align="center">
	                        
	                        <input class="btn  btn-success" type="button"  value="목록보기" 
	                               onclick="location.href='list.do?page=${ param.page }&search=${ param.search}&search_text=${param.search_text}'">
	                        
	                        <!-- 답글의 깊이는 0 1 만 허용 => 메인글 + 하위1개 에만 답글허용 -->
	                        <c:if test="${ (param.search eq 'all')  and  (vo.b_depth le 1) }">
	                        	<input class="btn  btn-primary" type="button"  value="답글쓰기" 
	                        	       onclick="reply_form();">
	                        </c:if>
	                        
	                        <!-- 글쓴이 본인만 처리되도록 -->
	                        <c:if test="${ user.m_idx == vo.m_idx }">
		                        <input class="btn  btn-info"    type="button"  value="수정하기" 
		                               onclick="location.href='modify_form.do?b_idx=${ vo.b_idx }&page=${ param.page }&search=${ param.search}&search_text=${param.search_text}'">
		                        <input class="btn  btn-danger"  type="button"  value="삭제하기" 
		                               onclick="board_delete();">
	                        </c:if>
	                  
	                  </td>
	               </tr>
	               
	          </table>
	      </div>
	    </div><!--End Panel  -->
	    
	    <!-- 댓글입력창 -->
	    <div>
	        <textarea id="comment_content" placeholder="댓글은 로그인후에 작성가능합니다"></textarea>
	        <input    id="comment_button"  type="button" value="댓글달기" onclick="add_comment();">    
	    </div>
	    
	    <hr>
	    
	    <!-- 댓글출력창  -->
	    <div id="comment_display">
	        
	    </div>
	    
	    
  </div><!-- End Box -->
</body>
</html>