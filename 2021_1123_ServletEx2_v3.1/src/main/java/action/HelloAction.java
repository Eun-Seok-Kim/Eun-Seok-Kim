package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloAction1
 */
@WebServlet("/Hello.do")
public class HelloAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest request,		//요청처리객체(Client정보)
			HttpServletResponse response	//응답처리객체(서버->클라이언트로 결과전송)	
			) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String ip = request.getRemoteAddr(); //호출한 상대방 주소
		
		
		System.out.printf("--[%s] HelloAction: service() call--\n",ip);
		
		//클라이언트로부터 넘어온 parameter정보를 얻어온다.
		String nation = request.getParameter("nation");
		System.out.println("전달받은 파라메터 : "+ nation);
		//정상적인 파라메타 값이 들어오지 않을경우 기본 kor로 설정
		if(nation==null || nation.isEmpty()) {
			nation = "defalut";
		}
		String title   = "";
		String message = "";
		switch(nation)
		{
		case "kor": title="한국어 인사말"; message="안녕하세요";break;
		case "eng": title="영어 인사말"; message="Hi Evertone";break;
		case "jpn": title="일본어 인사말"; message="오겡끼데스까";break;
		case "chn": title="중국어 인사말"; message="니하오";break;
		case "ger": title="독일어 인사말"; message="쿠텐탁";break;
		case "fra": title="프랑스어 인사말"; message="봉쥬르";break;
		default	  : title="어떤나라";	message="뭘까?";
		}
		
		//응답처리
		
		//결과물에 대한 정보 전달
		response.setContentType("text/html; charset=utf-8;");
		
		//response이용해서 출력객체 정보를 얻어온다.
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.printf("<head><title>%s</title></head>",title);
		out.println("<body>");
		out.printf("[%s]입니다<br>", title);
		out.printf("[%s]님 [%s]<br>",ip, message);
		out.printf("<a href='Hello.html'>다시하기 </a>");
		out.println("</body>");
		out.println("</html>");
	}

}
