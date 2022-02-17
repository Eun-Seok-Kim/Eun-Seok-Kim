package dao;

import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.SawonVo;

public class SawonDao {
	//single-ton : ��ü1���� ���� ����
	static SawonDao single = null;

	//SqlSession�����ϴ� ��ü
	SqlSessionFactory factory;
	
	public SawonDao() {
		super();
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	public static SawonDao getInstance() {

		if (single == null)
			single = new SawonDao();

		return single;
	}
	
	public List<SawonVo> selectList() {

		
		//��ȸ����� ���� �÷���
		List<SawonVo> list = null;
		//1.SqlSession*Mybatis���ఴü
		SqlSession sqlSession = factory.openSession();
		//2.�۾�����
		//sqlSession�� �־��� mapper id�� �ִ� ������ ���ͼ� select ����� List�� ���� �ؼ� �ش�
		list =sqlSession.selectList("sawon.sawon_list");
		
		
		//3.�ݱ�
		sqlSession.close();

		return list;
	}
public List<SawonVo> selectListDeptno(int deptno) {

		
		//��ȸ����� ���� �÷���
		List<SawonVo> list = null;
		//1.SqlSession*Mybatis���ఴü
		SqlSession sqlSession = factory.openSession();
		//2.�۾�����
		//sqlSession�� �־��� mapper id�� �ִ� ������ ���ͼ� select ����� List�� ���� �ؼ� �ش�
		//							namespace.mapper id		  parameter(��������)
		list =sqlSession.selectList("sawon.sawon_list_deptno",deptno);
		
		
		//3.�ݱ�
		sqlSession.close();

		return list;
	}

public List<SawonVo> selectListSajob(String sajob) {

	
	//��ȸ����� ���� �÷���
	List<SawonVo> list = null;
	//1.SqlSession*Mybatis���ఴü
	SqlSession sqlSession = factory.openSession();
	//2.�۾�����
	//sqlSession�� �־��� mapper id�� �ִ� ������ ���ͼ� select ����� List�� ���� �ؼ� �ش�
	//							namespace.mapper id		  parameter(��������)
	list =sqlSession.selectList("sawon.sawon_list_sajob",sajob);
	
	
	//3.�ݱ�
	sqlSession.close();

	return list;
}
public List<SawonVo> selectListDeptnoSajob(SawonVo vo) {

	//��ȸ����� ���� �÷���
	List<SawonVo> list = null;

	//1.SqlSession*Mybatis���ఴü
	SqlSession sqlSession = factory.openSession();
	//2.�۾�����
	//sqlSession�� �־��� mapper id�� �ִ� ������ ���ͼ� select ����� List�� ���� �ؼ� �ش�
	//							namespace.mapper id		  parameter(��������)
	list =sqlSession.selectList("sawon.sawon_list_deptno_sajob",vo);
	
	
	//3.�ݱ�
	sqlSession.close();

	return list;
}

public List<SawonVo> selectList(Map map) {
	// TODO Auto-generated method stub
	//��ȸ����� ���� �÷���
	List<SawonVo> list = null;
	//1.SqlSession*Mybatis���ఴü
	SqlSession sqlSession = factory.openSession();
	//2.�۾�����
	//sqlSession�� �־��� mapper id�� �ִ� ������ ���ͼ� select ����� List�� ���� �ؼ� �ش�
	list =sqlSession.selectList("sawon.sawon_list_map", map);
	
	
	//3.�ݱ�
	sqlSession.close();

	return list;
}


}
