package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitCheckPwdActionSungListAction
 */
@WebServlet("/visit/check_pwd.do")
public class VisitCheckPwdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,						
						   HttpServletResponse response)
						   throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /visit/check_pwd.do?idx=13&c_pwd=1234
		//���� ���ڵ� ����
		request.setCharacterEncoding("utf-8"); 
		//parameter�ޱ�
		int idx			= Integer.parseInt(request.getParameter("idx"));
		String c_pwd	= request.getParameter("c_pwd");
		//idx�� �ش�Ǵ� �Խù� ���� ������
		
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		//��й�ȣ ��
		boolean bSame = vo.getPwd().equals(c_pwd);
		
		//����� ��û�� ������ Ÿ������ ����(json){"result" : true}
		//										  {"result" : false}
		String json_result = String.format("{\"result\":%b}", bSame);
		
		//�������
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json_result);
		
	

	}

}

