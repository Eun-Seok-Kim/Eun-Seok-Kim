package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {

	DataSource ds;
	
	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static DBService single = null;

	public static DBService getInstance() {

		if (single == null)
			single = new DBService();

		return single;
	}

	//�ܺο��� ������������ ���� : new DBService() (X)
	private DBService() {
		// TODO Auto-generated constructor stub
		
		try {
			//JMDI(Java Naming Directory(�˻�) Interface) : InitialContext()
			//1.InitialContext ����
			InitialContext ic = new InitialContext();
			//2.Resource�� ������ġ���� ȹ�� : Context
	
			Context ctx = (Context) ic.lookup("java:comp/env");
			
			//3.DateSourceȹ��	
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
