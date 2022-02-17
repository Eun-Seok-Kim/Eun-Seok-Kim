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

<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> -->


<style type="text/css">
  #box{
      width: 1000px;
      margin: auto;
      margin-top: 50px;
  }
  
  #title{
      text-align: center;
      font-size: 35px;
      font-family: 굴림체;
      font-weight: bold;
      
      color: #6699cc;
      
      text-shadow: 1px 1px 1px black;
  }
  
  th,td{
     text-align: center;
  }
  
  
  #page_menu{
      font-size: 20px;
      font-weight: bold;
  }
  
  #search_menu{
      text-align: center;
      margin-top: 2px;
      margin-bottom: 5px;
  }
  
  .page_box{
     display: inline-block;
     width: 30px;
     height: 30px;
     border: 1px solid gray;
    
  }
    
  a:hover, /* OPTIONAL*/
  a:visited,
  a:focus
  {text-decoration: none;}  
    
  
</style>


<script type="text/javascript">
   
   function insert_form(){
	   
	   //로그인 여부 체크
	   if("${ empty user }" == "true"){
		   
		   if(confirm("로그인 하신후 글쓰기가 가능합니다\n로그인 하시겠습니까?")==false)return;
		   
		   //로그인 폼
		   location.href="${ pageContext.request.contextPath }/member/login_form.do";
		   return;
	   }
	   
	   //글쓰기 폼으로 이동:
	   location.href="insert_form.do"
	   
   }//end-insert_form()
   
   
   //검색
   function search(){
	   
	   var search      = $("#search").val();
	   var search_text = $("#search_text").val().trim();
	   
	   //전체검색이 아닌데 검색어가 비어있으면
	   if(search != 'all' && search_text==''){
		  alert('검색어를 입력하세요');
		  $("#search_text").val("");
		  $("#search_text").focus();
		  return;
	   }
	   
	   //검색요청
	   location.href="list.do?search=" + search + "&search_text=" + encodeURIComponent(search_text);

   }
   

</script>


<script type="text/javascript">

   $(document).ready(function(){

	   setTimeout(show_message,200);
	   
	   //검색메뉴 
	   if("${ not empty param.search }"=="true"){
		   
		   $("#search").val("${ param.search}");
		   
		   //전체보기면 ->검색어 지움
		   if("${ param.search eq 'all' }" =="true"){
			   
			   $("#search_text").val("");
		   }
	   }
	   	   
	   
	   
   });
   
   function show_message(){
	   
	   if("${ param.reason eq 'session_timeout'}" == "true"){
		   
		   alert("로그아웃되었습니다\n로그인 하신후 이용하세요");
		   
	   }
   }


</script>



</head>
<body>
  <div id="box">
       <h1 id="title">::::게시판::::</h1>
       
       <!-- 로그인/ 로그아웃 -->
       <div  style="text-align: right;">
           <!-- 로그인 안된경우 -->
           <c:if test="${ empty user }">
              <input type="button"  value="로그인"   class="btn  btn-primary"
                     onclick="location.href='${ pageContext.request.contextPath }/member/login_form.do'">
           </c:if>
           
           <!-- 로그인이 된경우 -->
           <c:if test="${ not empty user }">
                <b>${ user.m_name }</b>님 환영합니다
                <input type="button"  value="로그아웃"  class="btn  btn-primary"
                     onclick="location.href='${ pageContext.request.contextPath }/member/logout.do'">
           </c:if>
       </div>
       
       <div style="margin-bottom: 10px;">
           <input  class="btn  btn-primary" type="button"  value="글쓰기"  
                   onclick="insert_form();">
       </div>
       
       <!-- 검색메뉴 -->
       <div id="search_menu">
        	<select id="search">
        	     <option value="all">전체보기</option>
        	     <option value="name">이름</option>
        	     <option value="subject">제목</option>
        	     <option value="content">내용</option>
        	     <option value="name_subject_content">이름+제목+내용</option>  
        	</select>
        	<input id="search_text"  value="${ param.search_text }">
        	<input type="button"  value="검색"  onclick="search();">
        </div>                    
             
       <!-- 게시판내용 -->
       <table class="table  table-striped  table-hover">
             
             <!-- 제목 -->
             <tr class="success">
                 <th width="10%">번호</th>
                 <th width="50%">제목</th>
                 <th width="15%">작성자</th>
                 <th width="15%">작성일자</th>
                 <th width="10%">조회수</th>
             </tr>
             
             <!-- 데이터가 없는 경우 -->
             <c:if test="${ empty list }">
                 <tr>
                    <td colspan="5" align="center">
                       <font color="red">작성된 게시물이 없습니다</font>
                    </td>
                 </tr>
             </c:if>
        
             <!-- 데이터가 있는 경우 -->
             <!-- for(BoardVo vo : list)  -->
             <c:forEach var="vo"  items="${ list }">
                <tr>
                    <td>${ vo.no }(${ vo.b_idx })</td>
                    <td style="text-align: left; text-indent: 10px;">
                    
                         <!-- 답글의정도(b_depth)에 따라서 들여쓰기 -->
                         <c:forEach begin="1"  end="${ vo.b_depth }">
                            &nbsp;&nbsp;
                         </c:forEach>
                    
                         <!--답글인경우에만 붙인다  -->
                         <c:if test="${ vo.b_depth != 0 }">
                         ㄴ
                         </c:if>
                         
                         <!-- 삭제되지 않은 게시물이면 -->
                         <c:if test="${ vo.b_use_yn eq 'y' }">
                         	 <a href="view.do?b_idx=${ vo.b_idx }&page=${ empty param.page ? 1 : param.page }&search=${ empty param.search ? 'all' : param.search  }&search_text=${param.search_text}">
                         	 ${ vo.b_subject }
                         	 </a>
                         	 
                         	 <c:if test="${ vo.comment_count != 0 }">
                                 <font color=red>(${ vo.comment_count })</font>     	 
                                 <%-- <span class="badge">${vo.comment_count}</span> --%>
                         	 </c:if>
                         </c:if>
                         
                         <!-- 삭제된 게시물이면 -->
                         <c:if test="${ vo.b_use_yn eq 'n' }">
                             <font color=red>(삭제된 게시물) ${ vo.b_subject }</font>
                         </c:if>
                         
                         
                    </td>
                    <td>${ vo.m_name }</td>
                    <td>${ fn:substring(vo.b_regdate,0,10) }</td>
                    <td>${ vo.b_readhit }</td>
                </tr>
             </c:forEach>
        
             
        
             <!-- 페이지 메뉴 -->
             <tr>
                <td colspan="5" align="center">
                    
                    <div id="page_menu"> 
                     ${ pageMenu }
                    </div>
                                        
                     
                </td>
             </tr>
       
       </table>
       
  </div>  
</body>
</html>











