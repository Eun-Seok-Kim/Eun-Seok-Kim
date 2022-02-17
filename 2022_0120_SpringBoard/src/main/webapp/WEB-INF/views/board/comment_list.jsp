<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
    
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
   .comment_box{
      margin-top: 10px;
      border: 1px solid #cccccc;
     box-shadow: 1px 1px 5px #aaaaaa;
   }
   
   .comment_content_style{
      
      min-height: 50px;
      border: 1px solid #dddddd;
      /* box-shadow: -1px -1px 1px black; */
   }
   
   .page_box{
     display: inline-block;
     width: 20px;
     height: 22px;
     border: 1px solid gray;
     text-align: center;
    
  }
    
  a:hover, /* OPTIONAL*/
  a:visited,
  a:focus
  {text-decoration: none;}  
</style>

<script type="text/javascript">
    
    function comment_delete(c_idx,b_idx){
    	
    	if(confirm("정말 댓글을 삭제하시겠습니까?")==false)return;
    	
    	//ajax를 이용해서 삭제
    	$.ajax({
    		url	    : "../comment/delete.do",
    		data    : {"c_idx": c_idx ,"b_idx": b_idx},
    		dataType: "json",
    		success : function(result_data){
    			// result_data = { "result" : "success","totalPage":5}
    			// result_data = { "result" : "fail"}
    			if(result_data.result == 'fail'){
    				alert('삭제실패!!');
    			}
    			
    			//댓글목록을 다시 가져오기
    			if(comment_page > result_data.totalPage)
    				comment_page = result_data.totalPage;
    			
    			comment_list(comment_page);

    			
    		},
    		error   : function(err){
    			
    			alert(err.responseText);
    		}
    	});
    	
    	
    }

</script>


</head>
<body>

  <!-- 페이징메뉴 -->
  <c:if test="${ not empty list }">
	  <div>
	      ${ pageMenu }
	  </div>
  </c:if>



  <!--  for(CommentVo c_vo : list)  -->
  <c:forEach var ="c_vo"  items="${ list }">
	  <div class="comment_box">
	      
	      <!-- 로그인한 회원  == 게시물작성자 -->
	      <c:if test="${ user.m_idx eq c_vo.m_idx }">
	      	<div style="text-align: right;"><input type="button"  value="x" onclick="comment_delete('${ c_vo.c_idx }','${ c_vo.b_idx }');"></div>
	      </c:if>
	      
	      <div>${ c_vo.m_name} (${ c_vo.m_id })</div>
	      <div>${ c_vo.c_regdate }</div>
	      <div class="comment_content_style">${ c_vo.c_content }</div>
	  </div>
  </c:forEach>
</body>
</html>