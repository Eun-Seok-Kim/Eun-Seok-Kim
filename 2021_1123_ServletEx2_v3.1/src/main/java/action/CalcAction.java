package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcAction
 */
@WebServlet("/calc.do")
public class CalcAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s1 = request.getParameter("su1");
		String s2 = request.getParameter("su2");
		
		int su1 = 	Integer.parseInt(s1);
		int su2 = 	Integer.parseInt(s2);
		
		
		//mime-type and encoding
		//�Ʒ� ���ڵ� ���� ���ϸ� �ѱ��� ����
		response.setContentType("text/html; charset=utf-8;");
		//response�̿��ؼ� ��°�ü ������ ���´�.
		PrintWriter out = response.getWriter();
		
		
		
		out.println("<html>");
		out.printf("<head><title>�����</title></head>");
		out.println("<body>");
		out.printf("[%4d]  +  [%4d] = [%4d] �Դϴ�<br>",su1,su2,su1+su2);
		out.printf("[%4d]&nbsp;  -  &nbsp;[%4d] = [%4d] �Դϴ�<br>",su1,su2,su1-su2);
		out.printf("[%4d]&nbsp; *  &nbsp;[%4d] = [%4d] �Դϴ�<br>",su1,su2,su1*su2);
	
		if(su2==0) {
			out.println("0���� ������ �����ϴ�<br>");
		}else {
			out.printf("[%4d]&nbsp;  /  &nbsp;[%4d] = [%4d] �Դϴ�<br>",su1,su2,(su1/su2));
			out.printf("[%4d]&nbsp; %%  &nbsp;[%4d] = [%4d] �Դϴ�<br>",su1,su2,(su1%su2));
		}	
		out.printf("<br>");
		out.printf("<a href='calc.html'>�ٽ��ϱ� </a>");
		out.println("</body>");
		out.println("</html>");	
		
	}

}
