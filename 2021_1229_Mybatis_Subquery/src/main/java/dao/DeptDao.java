package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.DeptVo;
import vo.GogekVo;
import vo.SawonVo;

public class DeptDao {

	//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static DeptDao single = null;

	//mybatis객체 생성
	SqlSessionFactory factory;
	
	public static DeptDao getInstance() {

		if (single == null)
			single = new DeptDao();

		return single;
	}

	//외부에서 직접생성하지 말것 : new DeptDao() (X)
	private DeptDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	//부서목록
	public List<DeptVo> selectList(){
		List<DeptVo> list =null;
		//1.SqlSession
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		list = sqlSession.selectList("dept.dept_list");
		
		
		//3.세션닫기
		sqlSession.close();
		return list;
	}
	

}
