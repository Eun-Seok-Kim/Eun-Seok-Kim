package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberRegisterAction
 */
@WebServlet("/member_register.do")
public class MemberRegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,
						   HttpServletResponse response) 
						   throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * member_register.do?name=ȫ�浿 
		 * 					  &id=hong
		 * 					  &pwd=1234
		 * 					  &gender=����
		 * 					  &hobby=��ȭ
		 * 					  &friend=ģ��1
		 * 					  &friend=
		 * 					  &friend=
		 * 					  &blood=B
		 * 					  &profile=����+ȫ�浿
		 */
			//�������ڵ� ����(���۹���� POST�϶� ������ ó���Ǿ����)
			request.setCharacterEncoding("utf-8");
		
		String name		= request.getParameter("name");
		String id		= request.getParameter("id");
		String pwd		= request.getParameter("pwd");
		String gender	= request.getParameter("gender");
		String blood	= request.getParameter("blood");
		
		//\n -> <br>�� ����
		String profile	= request.getParameter("profile").replaceAll("\n","<br>");

		
		//Ŭ���̾�Ʈ���� �����̸����� �Ѿ������ �迭�ε����� �ޱ�
		// => check box�� üũ�� �׸� ���۵ȴ�.
		String [] hobby_array = request.getParameterValues("hobby");
		String 	  hobby_list="��̾���";
			if(hobby_array!=null) {
				StringBuffer sb = new StringBuffer();
				for(String hobby : hobby_array) {
					sb.append(hobby);//sb�� hobby�� �ֱ�
					sb.append(" ");//����
				}//end_hobby_for
				hobby_list = sb.toString().trim(); // �������ŵ� hobby���� ����
			}//end_hobby_if
			
		// => ��ü �׸��� �Ѿ�´�
		String [] friend_array = request.getParameterValues("friend");
		String	  friend_list = "";
			StringBuffer sb = new StringBuffer();
			for(String friend : friend_array) {
				sb.append(friend);
				sb.append(" ");				
			}//end_for
			friend_list = sb.toString().trim();
				if(friend_list.isEmpty()) {
					friend_list="ģ������";
				}//end_if
	//���������� ����ó��
		//�Ʒ� ���� �߿� 1.response 2.PrintWriter
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>���</title></head>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<caption>ȸ�������Է°��</caption>");
		
		out.printf("<tr><th>�̸�</th><td>%s</td></tr>",name);
		out.printf("<tr><th>���̵�</th><td>%s</td></tr>",id);
		out.printf("<tr><th>��й�ȣ</th><td>%s</td></tr>",pwd);
		out.printf("<tr><th>����</th><td>%s</td></tr>",gender);
		out.printf("<tr><th>���</th><td>%s</td></tr>",hobby_list);
		out.printf("<tr><th>ģ��</th><td>%s</td></tr>",friend_list);
		out.printf("<tr><th>������</th><td>%s</td></tr>",blood);
		out.printf("<tr><th>�ڱ�Ұ�</th><td>%s</td></tr>",profile);	
		out.println("<tr><td colspan='2' align='center'><a href='member_input_form.html'>�ٽ��ϱ�</a></td></tr>");
		
		
		out.println("</table>");
		out.println("</body>");
		out.println("<html>");

		
	}//end-service

}
