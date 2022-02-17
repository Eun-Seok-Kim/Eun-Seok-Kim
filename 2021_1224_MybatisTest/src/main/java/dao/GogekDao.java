package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.GogekVo;

public class GogekDao {

	//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static GogekDao single = null;
	//SqlSession생성하는 객체
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

	//외부에서 직접생성하지 말것 : new GogekDao() (X)

	public List<GogekVo> selectList() {

		//조회결과을 담을 컬렉션
		List<GogekVo> list = null;
		
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("gogek.gogek_list");
		
		sqlSession.close();
		
		return list;
	}
}
