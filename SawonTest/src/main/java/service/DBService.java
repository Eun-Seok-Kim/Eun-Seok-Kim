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
			InitialContext ic = new InitialContext();
			Context ctx = (Context) ic.lookup("java:comp/env");
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
