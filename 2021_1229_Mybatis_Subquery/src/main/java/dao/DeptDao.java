package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.DeptVo;
import vo.GogekVo;
import vo.SawonVo;

public class DeptDao {

	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static DeptDao single = null;

	//mybatis��ü ����
	SqlSessionFactory factory;
	
	public static DeptDao getInstance() {

		if (single == null)
			single = new DeptDao();

		return single;
	}

	//�ܺο��� ������������ ���� : new DeptDao() (X)
	private DeptDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	//�μ����
	public List<DeptVo> selectList(){
		List<DeptVo> list =null;
		//1.SqlSession
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		list = sqlSession.selectList("dept.dept_list");
		
		
		//3.���Ǵݱ�
		sqlSession.close();
		return list;
	}
	

}
