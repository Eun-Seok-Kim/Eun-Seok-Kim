package dao;

import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.SawonVo;

public class SawonDao {
	//single-ton : 객체1개만 생성 서비스
	static SawonDao single = null;

	//SqlSession생성하는 객체
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

		
		//조회결과을 담을 컬렉션
		List<SawonVo> list = null;
		//1.SqlSession*Mybatis수행객체
		SqlSession sqlSession = factory.openSession();
		//2.작업수행
		//sqlSession이 주어진 mapper id에 있는 정보를 얻어와서 select 결과를 List로 포장 해서 준다
		list =sqlSession.selectList("sawon.sawon_list");
		
		
		//3.닫기
		sqlSession.close();

		return list;
	}
public List<SawonVo> selectListDeptno(int deptno) {

		
		//조회결과을 담을 컬렉션
		List<SawonVo> list = null;
		//1.SqlSession*Mybatis수행객체
		SqlSession sqlSession = factory.openSession();
		//2.작업수행
		//sqlSession이 주어진 mapper id에 있는 정보를 얻어와서 select 결과를 List로 포장 해서 준다
		//							namespace.mapper id		  parameter(전달인자)
		list =sqlSession.selectList("sawon.sawon_list_deptno",deptno);
		
		
		//3.닫기
		sqlSession.close();

		return list;
	}

public List<SawonVo> selectListSajob(String sajob) {

	
	//조회결과을 담을 컬렉션
	List<SawonVo> list = null;
	//1.SqlSession*Mybatis수행객체
	SqlSession sqlSession = factory.openSession();
	//2.작업수행
	//sqlSession이 주어진 mapper id에 있는 정보를 얻어와서 select 결과를 List로 포장 해서 준다
	//							namespace.mapper id		  parameter(전달인자)
	list =sqlSession.selectList("sawon.sawon_list_sajob",sajob);
	
	
	//3.닫기
	sqlSession.close();

	return list;
}
public List<SawonVo> selectListDeptnoSajob(SawonVo vo) {

	//조회결과을 담을 컬렉션
	List<SawonVo> list = null;

	//1.SqlSession*Mybatis수행객체
	SqlSession sqlSession = factory.openSession();
	//2.작업수행
	//sqlSession이 주어진 mapper id에 있는 정보를 얻어와서 select 결과를 List로 포장 해서 준다
	//							namespace.mapper id		  parameter(전달인자)
	list =sqlSession.selectList("sawon.sawon_list_deptno_sajob",vo);
	
	
	//3.닫기
	sqlSession.close();

	return list;
}

public List<SawonVo> selectList(Map map) {
	// TODO Auto-generated method stub
	//조회결과을 담을 컬렉션
	List<SawonVo> list = null;
	//1.SqlSession*Mybatis수행객체
	SqlSession sqlSession = factory.openSession();
	//2.작업수행
	//sqlSession이 주어진 mapper id에 있는 정보를 얻어와서 select 결과를 List로 포장 해서 준다
	list =sqlSession.selectList("sawon.sawon_list_map", map);
	
	
	//3.닫기
	sqlSession.close();

	return list;
}


}
