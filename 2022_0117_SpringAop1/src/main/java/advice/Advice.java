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
		
		//request에 넣어놓은 srat시간을 꺼내온다
		long start = (Long) request.getAttribute("start");
		long end = System.currentTimeMillis();
		String ip = request.getRemoteAddr();
		System.out.printf("--수행시간(%s) : %d(ms)\n",ip,end-start);
		System.out.println("----after:" + s.toLongString());
	}
}
