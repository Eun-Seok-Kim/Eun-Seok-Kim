package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {
	
	//BasicDataSource�� ����ϱ� ���� �������̽�
	DataSource ds;
	
	//single-ton : ��ü 1���� �����ؼ� ���������� ���� ����
	static DBService single = null;

	public static DBService getInstance() {

		if (single == null)
			single = new DBService();

		return single;
	}

	//�ܺο��� ���� �������� �� �� : new DBService() (X)   
	private DBService() {
		// TODO Auto-generated constructor stub
		
		try {
			
			//JNDI(Java Naming Directory(�˻�) Interface): InitialContext()
			//�ڹ� �̸� �˻��ؼ� �������̽� ȹ���ϴ� ���
			//1.InitialContext����
			InitialContext ic = new InitialContext();
			
			//2.Resource�� ������ġ���� ȹ��: Context
			//  �ڹ� ��ġ ���ϴ� ����� �����ϱ�(java:comp/env) env�����̾��?
			Context ctx = (Context) ic.lookup("java:comp/env");
			
			//3.DataSourceȹ��
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
