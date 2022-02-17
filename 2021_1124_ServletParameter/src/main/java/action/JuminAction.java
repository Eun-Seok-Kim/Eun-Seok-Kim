package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myutil.Jumin;

/**
 * Servlet implementation class JuminAction
 */
@WebServlet("/jumin.do")
public class JuminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,
						  HttpServletResponse response)
						  throws ServletException, IOException {
		// TODO Auto-generated method stub
	request.setCharacterEncoding("utf-8");	
	String jumin_no = request.getParameter("jumin_no");
	
	
	//기존에 완성된 파일 myutil/Jumin.java 파일을 객체로 불러온다.
	Jumin jumin = new Jumin();
	jumin.setJumin_no(jumin_no);
	
	int year		= jumin.getYear();
	int age			= jumin.getAge();
	String tti		= jumin.getTti();
	String season	= jumin.getSeason();
	String gender	= jumin.getGender();
	String ganji	= jumin.getGanji();
	String local	= jumin.getLocal();
	
	//가공데이터 응답처리
	//아래 순서 중요 1.response 2.PrintWriter
	response.setContentType("text/html; charset=utf-8;");
	PrintWriter out = response.getWriter();
	
	out.println("<html><head>");
	out.println("<title>결과</title>");
	out.println("<script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>");
	out.println("</head>");
	out.println("<body>");
	out.println("<table border='1'>");
	out.println("<caption>회원정보입력결과</caption>");
	
	out.printf("<tr><th>주민번호</th><td>%s</td></tr>",jumin_no);
	out.printf("<tr><th>출생년도</th><td>%d(%s)</td></tr>",year,ganji);
	out.printf("<tr><th>나이</th><td>%d</td></tr>",age);
	out.printf("<tr><th>성별</th><td>%s</td></tr>",gender);
	out.printf("<tr><th>띠</th><td>%s</td></tr>",tti);
	out.printf("<tr><th>출생계절</th><td>%s</td></tr>",season);
	out.printf("<tr><th>출생지역</th><td>%s</td></tr>",local);
	out.println("<tr><td colspan='2' align='center'><a href='jumin.html'>다시하기</a></td></tr>");
	
	
	out.println("</table>");
	out.println("</body>");
	out.println("<html>");
	
	
	
	
	System.out.println(jumin_no);
	System.out.println(year);
	System.out.println(tti);
	System.out.println(gender);
	System.out.println(tti);
	System.out.println(season);
	System.out.println(local);
	System.out.println(ganji);
	
	
	
	
	
	}//end_service

}
