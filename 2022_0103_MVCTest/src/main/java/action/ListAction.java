package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//POHO( Plain Old Java Object):순수 자바객체

public class ListAction {
	
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		
		//가상데이터
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("Oracle");
		list.add("Html");
		list.add("Servlet");
		list.add("JSP");
		
		//request통해서 binding(연결)
		request.setAttribute("list", list);
		
		return "list.jsp";
	}
}
