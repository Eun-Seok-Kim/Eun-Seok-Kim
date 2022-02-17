package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.GogekVo;

public class GogekDao {

	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static GogekDao single = null;
	//SqlSession�����ϴ� ��ü
	SqlSessionFactory factory;

	public GogekDao() {
		super();
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	
	public static GogekDao getInstance() {

		if (single == null)
			single = new GogekDao();
	
		return single;
	}

	//�ܺο��� ������������ ���� : new GogekDao() (X)

	public List<GogekVo> selectList() {

		//��ȸ����� ���� �÷���
		List<GogekVo> list = null;
		
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("gogek.gogek_list");
		
		sqlSession.close();
		
		return list;
	}
}
