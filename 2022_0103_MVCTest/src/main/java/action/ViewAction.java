package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAction {

	public String execute(HttpServletRequest request,HttpServletResponse response) {
		
		// /view.do?book=Java , Oracle , Html , Servlet , JSP
		
		//1.parameter�ޱ�
		String book = request.getParameter("book");
		String content="";
		switch(book.toUpperCase())
		{
			case "JAVA"	: content = " �ڹ�å"; break;
			case "ORACLE"	: content = " ����Ŭå"; break;
			case "HTML"	: content = " HTMLå"; break;
			case "SERVLET"	: content = " ����å"; break;
			case "JSP"	: content = " JSPå"; break;
			default : content = "����å?";
		}
		request.setAttribute("book", book);
		request.setAttribute("content", content);
/*		
		List<String> list = new ArrayList<String>();
		if(book==null || book.equals("Java")) {			
			list.add("Java");
			list.add("Java å �Դϴ�.");
		}else if(book==null || book.equals("Oracle")) {			
			list.add("Oracle");
			list.add("Oracle å �Դϴ�.");
		}else if(book==null || book.equals("Html")) {			
			list.add("Html");
			list.add("Html å �Դϴ�.");
		}else if(book==null || book.equals("Servlet")) {			
			list.add("Servlet");
			list.add("Servlet å �Դϴ�.");
		}else if(book==null || book.equals("JSP")) {			
			list.add("JSP");
			list.add("JSP å �Դϴ�.\n");
		request.setAttribute("list", list);
*
*/
		//2.�󼼳��� �ۼ�
	
		//3.����� ������ request binding
		
		
		//request���ؼ� binding(����)
		
		return "view.jsp";
	}
}
