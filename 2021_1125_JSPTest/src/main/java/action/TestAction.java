package action;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestAction
 */
@WebServlet("/TestAction")
public class TestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//������� ����
	String name = "TestServlet";
	
	//����޼ҵ�
	public void display() {
			System.out.println(name + "�� �ȳ��ϼ���");
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// �ش缭���� �ʱ�ȭ�� Ȱ��
		//JSP -> _jspInit()
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		//�ش缭���� Container���� ����(�Ҹ�)�ö�
		//JSP -> _jspDestory()
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//������� �䱸�� ���ؼ� ȣ��
		//JSP -> _jspService()
		
		//service()���� �ڵ念��
		String ip = request.getRemoteAddr();
	}


}
