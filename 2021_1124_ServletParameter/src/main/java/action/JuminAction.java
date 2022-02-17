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
	
	
	//������ �ϼ��� ���� myutil/Jumin.java ������ ��ü�� �ҷ��´�.
	Jumin jumin = new Jumin();
	jumin.setJumin_no(jumin_no);
	
	int year		= jumin.getYear();
	int age			= jumin.getAge();
	String tti		= jumin.getTti();
	String season	= jumin.getSeason();
	String gender	= jumin.getGender();
	String ganji	= jumin.getGanji();
	String local	= jumin.getLocal();
	
	//���������� ����ó��
	//�Ʒ� ���� �߿� 1.response 2.PrintWriter
	response.setContentType("text/html; charset=utf-8;");
	PrintWriter out = response.getWriter();
	
	out.println("<html><head>");
	out.println("<title>���</title>");
	out.println("<script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>");
	out.println("</head>");
	out.println("<body>");
	out.println("<table border='1'>");
	out.println("<caption>ȸ�������Է°��</caption>");
	
	out.printf("<tr><th>�ֹι�ȣ</th><td>%s</td></tr>",jumin_no);
	out.printf("<tr><th>����⵵</th><td>%d(%s)</td></tr>",year,ganji);
	out.printf("<tr><th>����</th><td>%d</td></tr>",age);
	out.printf("<tr><th>����</th><td>%s</td></tr>",gender);
	out.printf("<tr><th>��</th><td>%s</td></tr>",tti);
	out.printf("<tr><th>�������</th><td>%s</td></tr>",season);
	out.printf("<tr><th>�������</th><td>%s</td></tr>",local);
	out.println("<tr><td colspan='2' align='center'><a href='jumin.html'>�ٽ��ϱ�</a></td></tr>");
	
	
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
