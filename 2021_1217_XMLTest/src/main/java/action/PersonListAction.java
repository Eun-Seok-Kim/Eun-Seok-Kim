package action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import vo.PersonVo;

/**
 * Servlet implementation class PersonListActionSungListAction
 */
@WebServlet("/person/list.do")
public class PersonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<PersonVo> list = new ArrayList<PersonVo>();

		try {
			//XML Parsing		
			SAXBuilder builder = new SAXBuilder();
			
			ServletContext application = request.getServletContext();
			
			String web_path="/";
			//웹경로 -> 절대경로
			String abs_path=application.getRealPath(web_path);
			System.out.println(abs_path);
			File f = new File(abs_path, "person.xml");
			
			//1.xml문서를 열어서 doc구한다
			Document doc = builder.build(f);
			
			//2.root Element를 구한다
			Element root = doc.getRootElement();
			
			//3.root 하위의 자식 Element구한다
			List<Element> person_list = root.getChildren("person");
			
			//4.각가의 Element의 구성값(name,age,tel)을 구한다.
			for(Element person : person_list) {
				PersonVo vo = new PersonVo();
				//person의 자식요소 값 얻어오기
				String name = person.getChildText("name");
				String nickname = person.getChild("name")
										.getAttributeValue("nickname");
				int age = 1;
				try {
					age = Integer.parseInt(person.getChildText("age"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				String tel = person.getChildText("tel");
				String hometel = person.getChild("tel")
									   .getAttributeValue("hometel");
				vo.setName(name);
				vo.setNickname(nickname);
				vo.setAge(age);
				vo.setTel(tel);
				vo.setHometel(hometel);
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//request binding
		request.setAttribute("list", list);
		//forward
		String forward_page = "person_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}
