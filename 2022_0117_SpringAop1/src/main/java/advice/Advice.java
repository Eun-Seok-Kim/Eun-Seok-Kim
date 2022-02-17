package advice;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

public class Advice {
	
	@Autowired
	HttpServletRequest request;
	
	
	

	public void before(JoinPoint jp){
		Signature s =  jp.getSignature();
		long start = System.currentTimeMillis();
		request.setAttribute("start", start);
		System.out.println("----before:" + s.toString());
	}
	
	public void after(JoinPoint jp){
		Signature s =  jp.getSignature();
		
		//request�� �־���� srat�ð��� �����´�
		long start = (Long) request.getAttribute("start");
		long end = System.currentTimeMillis();
		String ip = request.getRemoteAddr();
		System.out.printf("--����ð�(%s) : %d(ms)\n",ip,end-start);
		System.out.println("----after:" + s.toLongString());
	}
}
