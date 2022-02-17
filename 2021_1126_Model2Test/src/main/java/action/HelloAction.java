package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloAction
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
		request.setCharacterEncoding("utf-8");
		String ip = request.getRemoteAddr(); 
		String nation = request.getParameter("nation");
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

		request.setAttribute("ip", ip);
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		
		
		//응답처리
		
		//결과물에 대한 정보 전달
		RequestDispatcher disp = request.getRequestDispatcher("Hello_result.jsp");
		disp.forward(request, response);
		
		
		
		
		

	}

}
