package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnswerActionSungListAction
 */
@WebServlet("/answer.do")
public class AnswerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String question = request.getParameter("question");
		
		if(question==null || question.isEmpty())
			question = "Nothing";
		
		String answer="뭐지";
		switch(question.toUpperCase()) {
		case "HTML" : answer = "HyperText Markup Lanage";break;
		
		}
		//결과응답
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		out.print(answer);
		
	}

}

