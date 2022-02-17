package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAction {

	public String execute(HttpServletRequest request,HttpServletResponse response) {
		
		// /view.do?book=Java , Oracle , Html , Servlet , JSP
		
		//1.parameter받기
		String book = request.getParameter("book");
		String content="";
		switch(book.toUpperCase())
		{
			case "JAVA"	: content = " 자바책"; break;
			case "ORACLE"	: content = " 오라클책"; break;
			case "HTML"	: content = " HTML책"; break;
			case "SERVLET"	: content = " 서블릿책"; break;
			case "JSP"	: content = " JSP책"; break;
			default : content = "무슨책?";
		}
		request.setAttribute("book", book);
		request.setAttribute("content", content);
/*		
		List<String> list = new ArrayList<String>();
		if(book==null || book.equals("Java")) {			
			list.add("Java");
			list.add("Java 책 입니다.");
		}else if(book==null || book.equals("Oracle")) {			
			list.add("Oracle");
			list.add("Oracle 책 입니다.");
		}else if(book==null || book.equals("Html")) {			
			list.add("Html");
			list.add("Html 책 입니다.");
		}else if(book==null || book.equals("Servlet")) {			
			list.add("Servlet");
			list.add("Servlet 책 입니다.");
		}else if(book==null || book.equals("JSP")) {			
			list.add("JSP");
			list.add("JSP 책 입니다.\n");
		request.setAttribute("list", list);
*
*/
		//2.상세내용 작성
	
		//3.제목과 내용을 request binding
		
		
		//request통해서 binding(연결)
		
		return "view.jsp";
	}
}
