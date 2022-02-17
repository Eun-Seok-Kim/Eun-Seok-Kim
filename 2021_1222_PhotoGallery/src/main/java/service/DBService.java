package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {
	
	//BasicDataSource를 사용하기 위한 인터페이스
	DataSource ds;
	
	//single-ton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static DBService single = null;

	public static DBService getInstance() {

		if (single == null)
			single = new DBService();

		return single;
	}

	//외부에서 직접 생성하지 말 것 : new DBService() (X)   
	private DBService() {
		// TODO Auto-generated constructor stub
		
		try {
			
			//JNDI(Java Naming Directory(검색) Interface): InitialContext()
			//자바 이름 검색해서 인터페이스 획득하는 기술
			//1.InitialContext생성
			InitialContext ic = new InitialContext();
			
			//2.Resource의 저장위치정보 획득: Context
			//  자바 위치 구하는 상수로 생각하기(java:comp/env) env엔바이어런스?
			Context ctx = (Context) ic.lookup("java:comp/env");
			
			//3.DataSource획득
			ds = (DataSource) ctx.lookup("jdbc/oracle_test");
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//      java.sql import
	public Connection getConnection() throws SQLException {
		
		return ds.getConnection();
	}
	
	

}
