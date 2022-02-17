package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ListAction;
import action.ViewAction;

/**
 * Servlet implementation class FrontControllerSungListAction
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//String url = request.getRequestURL().toString();	//전체주소
		String uri = request.getRequestURI();				//서버주소뺀 나머지 주소
		//System.out.printf("URL : [%s]\n",url);
		//System.out.printf("URi : [%s]\n",uri);
		
		//1.요청분류
		
		//		 01234567890123456789012345	<-index
		//uri = "/2022_0103_MVCTest/list.do"
		//uri = "/2022_0103_MVCTest/view.do"
		//uri = "/2022_0103_MVCTest/insert.do"
	
		int index = uri.lastIndexOf("/");
		//System.out.println(index);
		String cmd = uri.substring(index+1).replaceAll(".do", "");
		
		System.out.println(cmd);
		
		if(cmd.equals("list")) {
			ListAction action = new ListAction();
			String forward_page = action.execute(request, response);			
			RequestDispatcher disp = request.getRequestDispatcher(forward_page);
			disp.forward(request, response);
		}
		else if (cmd.equals("view"));{
			ViewAction action = new ViewAction();
			String forward_page = action.execute(request, response);			
			RequestDispatcher disp = request.getRequestDispatcher(forward_page);
			disp.forward(request, response);
			
		}
	}

}

