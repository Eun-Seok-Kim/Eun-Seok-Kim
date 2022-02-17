package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloAction
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
		request.setCharacterEncoding("utf-8");
		String ip = request.getRemoteAddr(); 
		String nation = request.getParameter("nation");
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

		request.setAttribute("ip", ip);
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		
		
		//����ó��
		
		//������� ���� ���� ����
		RequestDispatcher disp = request.getRequestDispatcher("Hello_result.jsp");
		disp.forward(request, response);
		
		
		
		
		

	}

}
