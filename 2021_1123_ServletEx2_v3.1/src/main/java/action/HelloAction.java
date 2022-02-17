package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloAction1
 */
@WebServlet("/Hello.do")
public class HelloAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest request,		//��ûó����ü(Client����)
			HttpServletResponse response	//����ó����ü(����->Ŭ���̾�Ʈ�� �������)	
			) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String ip = request.getRemoteAddr(); //ȣ���� ���� �ּ�
		
		
		System.out.printf("--[%s] HelloAction: service() call--\n",ip);
		
		//Ŭ���̾�Ʈ�κ��� �Ѿ�� parameter������ ���´�.
		String nation = request.getParameter("nation");
		System.out.println("���޹��� �Ķ���� : "+ nation);
		//�������� �Ķ��Ÿ ���� ������ ������� �⺻ kor�� ����
		if(nation==null || nation.isEmpty()) {
			nation = "defalut";
		}
		String title   = "";
		String message = "";
		switch(nation)
		{
		case "kor": title="�ѱ��� �λ縻"; message="�ȳ��ϼ���";break;
		case "eng": title="���� �λ縻"; message="Hi Evertone";break;
		case "jpn": title="�Ϻ��� �λ縻"; message="���۳�������";break;
		case "chn": title="�߱��� �λ縻"; message="���Ͽ�";break;
		case "ger": title="���Ͼ� �λ縻"; message="����Ź";break;
		case "fra": title="�������� �λ縻"; message="���긣";break;
		default	  : title="�����";	message="����?";
		}
		
		//����ó��
		
		//������� ���� ���� ����
		response.setContentType("text/html; charset=utf-8;");
		
		//response�̿��ؼ� ��°�ü ������ ���´�.
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.printf("<head><title>%s</title></head>",title);
		out.println("<body>");
		out.printf("[%s]�Դϴ�<br>", title);
		out.printf("[%s]�� [%s]<br>",ip, message);
		out.printf("<a href='Hello.html'>�ٽ��ϱ� </a>");
		out.println("</body>");
		out.println("</html>");
	}

}
