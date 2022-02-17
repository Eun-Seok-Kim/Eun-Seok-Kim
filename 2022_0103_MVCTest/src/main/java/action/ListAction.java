package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//POHO( Plain Old Java Object):���� �ڹٰ�ü

public class ListAction {
	
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		
		//��������
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("Oracle");
		list.add("Html");
		list.add("Servlet");
		list.add("JSP");
		
		//request���ؼ� binding(����)
		request.setAttribute("list", list);
		
		return "list.jsp";
	}
}
