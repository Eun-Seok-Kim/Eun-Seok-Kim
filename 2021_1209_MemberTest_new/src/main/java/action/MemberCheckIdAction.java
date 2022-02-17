package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberCheckIdActionSungListAction
 */
@WebServlet("/member/check_id.do")
public class MemberCheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /member/check_id.do?m_id=hong
		request.setCharacterEncoding("utf-8");
		
		String m_id = request.getParameter("m_id");
		
		//vo�� ��ε� selectOne�� ������ ���翩�� Ȯ��
		MemberVo vo = MemberDao.getInstance().selectOne(m_id);
		
		boolean bResult = true; //��밡�� true,
		//��ȸ�� id�� ������̸� null �� �ƴϱ⿡ false�� ����
		if(vo!=null)
			bResult = false;
		//�������
		//json������ ����
		String resultJson = String.format("{\"result\":%b}", bResult);
		//���� ���ڵ� ����
		response.setContentType("text/json; charset=utf-8;");
		
		response.getWriter().print(resultJson);
	}

}

