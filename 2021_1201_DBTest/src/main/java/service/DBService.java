package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {

	DataSource ds;
	
	//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static DBService single = null;

	public static DBService getInstance() {

		if (single == null)
			single = new DBService();

		return single;
	}

	//외부에서 직접생성하지 말것 : new DBService() (X)
	private DBService() {
		// TODO Auto-generated constructor stub
		
		try {
			//JMDI(Java Naming Directory(검색) Interface) : InitialContext()
			//1.InitialContext 생성
			InitialContext ic = new InitialContext();
			//2.Resource의 저장위치정보 획득 : Context
	
			Context ctx = (Context) ic.lookup("java:comp/env");
			
			//3.DateSource획득	
			ds = (DataSource) ctx.lookup("jdbc/oracle_test");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	public Connection getConnection() throws SQLException {
		
		return ds.getConnection();
	}
	

}
