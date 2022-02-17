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

<!-- SweetAlert2사용설정  -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<style type="text/css">
   #box{
      width: 1000px;
      margin: auto;
      margin-top: 10px;
   }
   
   #title{
      text-align: center; 
      
      font-size: 30px;
      font-weight: bold;
      
      color: #3355ee;
      
      text-shadow: 1px 1px 3px  black;  
      
   }
   
   #div_login{
      text-align: right;
      padding-top: 10px;
      padding-bottom: 10px;
     /*  border: 1px solid blue; */
   }
   
   input[value='로그인']  ,input[value='회원가입'],
   input[value='로그아웃'],input[value='사진올리기']{
         width: 100px;
   }
   
   
   #photo_main_box{
       
       width: 100%;
       height: 600px;
       margin-top: 10px;
       
       border: 2px solid blue;
       
       overflow-y: scroll;
          
   }
   
   
   .photo_box{
       
       width: 120px;
       heigth: 150px;
       padding: 10px;
       
       margin-left: 35px;
       margin-right: 35px;
       
       margin-top: 50px;
       margin-bottom: 50px;
       
       border: 1px solid green;
       
       float: left;
       
   }
   
   .photo_box > img{
       width: 100px;
       height: 100px;
       border: 1px solid black;
       outline: 1px solid gray;
   }
   
   .subject_type{
   
      border: 1px solid #cccccc;
      box-shadow: 1px 1px 1px black;
      margin-top: 5px;
      margin-bottom: 5px;
      padding: 5px;
      
      font-size: 12px;
      
      /* ellipsis */
      overflow:hidden;
      text-overflow:ellipsis;
      white-space:nowrap;
      
      
   }
   
   .photo_box:hover{
      border: 1px solid red;
   }
   
   #empty_message{
      text-align: center;
      margin-top: 250px;
      color: red;
      
   }
</style>

<script type="text/javascript">

    function  upload_photo(){
    	
    	//로그인 상태 확인(안된경우)
    	if("${ empty user}" == "true"){
    		
    		if(confirm("사진올리기는 로그인 하신후 가능합니다\n로그인 하시겠습니까?")==false)return;
    		
    		//로그인폼으로 이동
    		location.href="../member/login_form.do";
    		return;
    	}
    	
    	
    	//로그인이 된경우
    	location.href="insert_form.do";// PhotoInsertFormAction    	
 
    }
    
    
    
    
    ///jQuery초기화
    //$(document).ready(function(){});
    
    $(function(){
    	setTimeout(show_message,100);
    });
    
    
    function show_message(){
    	
    	if("${ param.reason eq 'logout'}" == "true"){
    		
    		alert('로그인을 하신후 이용할 수 있습니다');
    	}
    }

    //팝업창 보여주기
    
    var g_p_idx;//전역변수
    
    function show_popup(p_idx){
    	
    	//팝업버튼을 클릭시 전달된 p_idx->g_p_idx
    	g_p_idx = p_idx;
    	
    	$("#pop_button").show();
    	
    	//ajax을 이용해서 p_idx에 해당되는 게시물정보 가져온다
    	//p_idx = 100;
    	
    	$.ajax({
    		//요청정보
    		url		: "photo_one.do",  //PhotoOneAction <= photo_one.do?p_idx=3
    		data	: {"p_idx": p_idx},
    		//응답정보
    		dataType: 'json',
    		success	: function(result_data){
    			
    			if(result_data.p_idx == 0){
    				alert('찾으시는 사진정보가 없습니다');
    				return;
    			}
    			//result_data = {"p_idx":3 , "p_subject": "제목...." , "p_content": "내용..." , "m_idx": 1 }
    			
    			//데이터 배치...
    			$("#pop_img").attr("src", "../upload/" + result_data.p_filename);
    	    	$("#pop_subject").html(result_data.p_subject);
    	    	$("#pop_content").html(result_data.p_content);
    	    	$("#pop_name_date").html("작성일자 :" + result_data.p_modifydate.substr(0,16));
    	    	
    	    	//사진을 올린 사용가 아니면 수정/삭제메뉴는 감춰라
    	    	if( (result_data.m_idx) != '${ user.m_idx}'){
    	    		//alert('diff');
    	    		$("#pop_button").hide();
    	    	}
    	    	
    	    	
    			popup_center_position();
    			
    			$("#popup").show();
    	    	
    		},
    		error	: function(err){
    			alert(err.responseText);
    		}
    		
    		
    	});
    	
    }
    
    
    
    
    
    function popup_center_position(){
    	
    	//위치조정
    	var pop_width =  $("#popup").css('width');
    	var pop_height = $("#popup").css('height');
    	
    	// px단위 제거
    	pop_width  = pop_width.replace('px','');
    	pop_height = pop_height.replace('px','');
    	
    	//console.log("p_w:" + pop_width + " p_h:" + pop_height);
    	
    	//브라우져 크기
    	var window_width  = $(window).width();
    	var window_height = $(window).height();
    	
    	//console.log("w_w:" + window_width + " w_h:" + window_height);
    	
    	//중앙위치 계산
    	var left = window_width/2  - pop_width/2;
    	var top  = window_height/2 - pop_height/2;
    	
    	$("#popup").css({'left':left, 'top': top });
    	
    	
    	
    }
    
    //팝업창 숨기기
    function hide_popup(){
    	
    	$("#popup").hide();
    	
    }
    
    
    //Browser크기변경이 발생하면 항상 중앙위치
   
    $(window).resize(function(){
    	
    	popup_center_position();
    	
    });
    
    
    
    //다운로드
    function download(filename){
    	
    	//로그인 상태 확인(안된경우)
    	if("${ empty user}" == "true"){
    		
    		if(confirm("사진받기는 로그인 하신후 가능합니다\n로그인 하시겠습니까?")==false)return;
    		
    		//로그인폼으로 이동
    		location.href="../member/login_form.do";
    		return;
    	}
    	
    	//alert(filename);
    	//로그인 되었을경우
    	//var msg="안녕";
    	//alert(msg + " : "+  encodeURIComponent(msg));
    	//주의) 자바스크립에서 url을 통해서 한글(특수문자)전송시에는 인코딩해서 전송해라
    	//      encodeURIComponent(값,charset)
    	//      encodeURIComponent(값) <= 현재페이지 인코딩 적용
    	location.href="../FileDownload.do?dir=/upload/&filename=" +  encodeURIComponent(filename,"utf-8");
    }
    
    
    //팝업메뉴상의 수정/삭제함수
    function delete_photo(){
    	
    	
    	if(confirm("정말 삭제하시겠습니까?")==false)return;
    	
    	//alert("삭제할 p_idx=" + g_p_idx);
    	location.href="delete.do?p_idx=" + g_p_idx;  // PhotoDeleteAction
    }
   
    
	function modify_photo(){
    	
		//주의사항: 첨부화일은 수정하지 말것
		//alert("수정할 p_idx=" + g_p_idx);
		location.href="modify_form.do?p_idx=" + g_p_idx; // PhotoModifyFormAction
    	
    }
//$("#id_img").attr('src','${vo.p_filename_new}'})
</script>


</head>
<body>

   <!-- popup윈도우 추가 -->
   <%@include file="photo_popup.jsp" %>



   <div id="box">
       <h1 id="title">::::PhotoGallery::::</h1>
       <div id="div_login">
            <!-- 현재경로 : /photo/list.do -->
            <!-- 로그인이 안된경우 -->
            <c:if test="${ empty sessionScope.user }">
                <input class="btn  btn-primary"  type="button"  value="로그인"    
                       onclick="location.href='../member/login_form.do'">
                <input class="btn  btn-success"  type="button"  value="회원가입"  
                       onclick="location.href='../member/insert_form.do'">
            </c:if>
            
            <!-- 로그인이 되어있는 상태 -->
            <c:if test="${ not empty sessionScope.user }">
                <b>${ user.m_name }</b>님 환영합니다
                <input class="btn  btn-danger" type="button"  value="로그아웃"    
                       onclick="location.href='../member/logout.do'">
            </c:if>
       </div>
       
       <div>
            <input  class="btn  btn-info" type="button"  value="사진올리기"     
                    onclick="upload_photo();">                   
       </div>
       
       <!-- PhotoBox -->
       <div id="photo_main_box">
       
            <!-- Data가 없는 경우 -->
            <c:if test="${ empty list }">
               <div id="empty_message">등록된 사진이 없습니다</div>
            </c:if> 
       
            
            <!-- Data가 있는 경우 -->
            <!-- for(PhotoVo vo : list)   -->
            <c:forEach var="vo"  items="${ list }">
            
	            <!-- photo 1장 정보 -->
	            <div class="photo_box">
	               <img id="id_img" src="../upload/${ vo.p_filename }"   onclick="show_popup('${ vo.p_idx }');">
	               <div class="subject_type">${ vo.p_subject }</div>
	               <div>
	                   <input  style="width:100%;"  class="btn  btn-success"  type="button"  value="다운로드"
	                           onclick="download('${ vo.p_filename }');" >
	               </div>
	            </div> 
            
            </c:forEach>
       
       
       </div>
   </div>
   
</body>
</html>