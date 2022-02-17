package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberRegisterAction
 */
@WebServlet("/member_register.do")
public class MemberRegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,
						   HttpServletResponse response) 
						   throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * member_register.do?name=홍길동 
		 * 					  &id=hong
		 * 					  &pwd=1234
		 * 					  &gender=남자
		 * 					  &hobby=영화
		 * 					  &friend=친구1
		 * 					  &friend=
		 * 					  &friend=
		 * 					  &blood=B
		 * 					  &profile=나는+홍길동
		 */
			//수신인코딩 설정(전송방식이 POST일때 무조건 처리되어야함)
			request.setCharacterEncoding("utf-8");
		
		String name		= request.getParameter("name");
		String id		= request.getParameter("id");
		String pwd		= request.getParameter("pwd");
		String gender	= request.getParameter("gender");
		String blood	= request.getParameter("blood");
		
		//\n -> <br>로 변경
		String profile	= request.getParameter("profile").replaceAll("\n","<br>");

		
		//클라이언트에서 같은이름으로 넘어왔을때 배열로데이터 받기
		// => check box는 체크된 항목만 전송된다.
		String [] hobby_array = request.getParameterValues("hobby");
		String 	  hobby_list="취미없음";
			if(hobby_array!=null) {
				StringBuffer sb = new StringBuffer();
				for(String hobby : hobby_array) {
					sb.append(hobby);//sb에 hobby값 넣기
					sb.append(" ");//띄어쓰기
				}//end_hobby_for
				hobby_list = sb.toString().trim(); // 공백제거된 hobby값이 저장
			}//end_hobby_if
			
		// => 전체 항목이 넘어온다
		String [] friend_array = request.getParameterValues("friend");
		String	  friend_list = "";
			StringBuffer sb = new StringBuffer();
			for(String friend : friend_array) {
				sb.append(friend);
				sb.append(" ");				
			}//end_for
			friend_list = sb.toString().trim();
				if(friend_list.isEmpty()) {
					friend_list="친구없음";
				}//end_if
	//가공데이터 응답처리
		//아래 순서 중요 1.response 2.PrintWriter
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>결과</title></head>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<caption>회원정보입력결과</caption>");
		
		out.printf("<tr><th>이름</th><td>%s</td></tr>",name);
		out.printf("<tr><th>아이디</th><td>%s</td></tr>",id);
		out.printf("<tr><th>비밀번호</th><td>%s</td></tr>",pwd);
		out.printf("<tr><th>성별</th><td>%s</td></tr>",gender);
		out.printf("<tr><th>취미</th><td>%s</td></tr>",hobby_list);
		out.printf("<tr><th>친구</th><td>%s</td></tr>",friend_list);
		out.printf("<tr><th>혈액형</th><td>%s</td></tr>",blood);
		out.printf("<tr><th>자기소개</th><td>%s</td></tr>",profile);	
		out.println("<tr><td colspan='2' align='center'><a href='member_input_form.html'>다시하기</a></td></tr>");
		
		
		out.println("</table>");
		out.println("</body>");
		out.println("<html>");

		
	}//end-service

}
