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


<style type="text/css">
  #rplace_insert_box{
      width: 700px;
      margin: auto;
      margin-top: 50px;
  }
  
  textarea {
	  width: 100%;
	  height: 150px;
	  resize: none;
  }

</style>

<script type="text/javascript">

  

  function send(f){
	  
	  var photo   = f.photo.value;
	  var subject = f.subject.value.trim();
	 
	  //공백처리
	  var content = f.content.value.replaceAll("\r\n","").trim();
	                          
	  if(subject==''){
		  alert("제목을 입력하세요");
		  f.subject.value='';
		  f.subject.focus();
		  return;
	  }
	  
	  if(content==''){
		  alert("내용을 입력하세요");
		  f.content.value='';
		  f.content.focus();
	
		  return ;
	  }
	  
	  f.action = "insert.do?page= ${ empty param.page ? 1 : param.page }"; //전송대상
	  f.submit();//전송
	  
	  
  }


</script>


</head>
<body>

<form name="f" method="POST" action="insert.do" enctype="multipart/form-data">

  <input type="hidden"  name="page"    value="${ param.page }">
  <input type="hidden"  name="search"    value="${ param.search }">
  <input type="hidden"  name="search_text"    value="${ param.search_text }">
  
  <div id="rplace_insert_box">
        <div class="panel panel-success">
	      <div class="panel-heading"><h3>글쓰기</h3></div>
	      
	      
	      <div class="panel-body">
	          <table class="table">
	              <tr>
	                 <th width="10%">제목</th>
	                 <td><input name="subject"  style="width: 100%;"></td>
	              </tr>
	              
	              <tr>
	                 <th>내용</th>
	                 <td>
	                     <textarea id="content" name="content" rows="" cols=""></textarea>
	                 </td>
	              </tr>
	              <tr>
				 <th>이미지첨부</th>
					 <td><input type="file" name="photo"></td>
				 </tr>
	              
	              
	              <tr>
	                 <td colspan="2" align="center">
	                      <input class="btn  btn-primary" type="button"  value="글올리기" onclick="send(this.form);">
	                      <input class="btn  btn-success" type="button"  value="목록보기" onclick="location.href='list.do?page=${ param.page }&search=${ param.search }&search_text=${ param.search_text }'">
	                 </td>
	              </tr>
	          </table>
	      
	      </div>
	    </div>
  </div>
</form>
  
</body>
</html>