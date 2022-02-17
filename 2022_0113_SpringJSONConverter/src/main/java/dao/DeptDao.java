package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.DeptVo;

public class DeptDao {

	
	SqlSession sqlSession; //Spring에서 설계해놓은 SqlSessionTemplate객체의 인터페이스

	//아래 경우는 기본생성자를 만들지 않는다
	
	public DeptDao(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public List<DeptVo> selectList() {
		// TODO Auto-generated method stub
		
		//SqlSessionTemplate내에서 selectList가 재정의 되어있다.
		//1. factoryBean을 이용해서 ipenSession()
		//2. mapper id를 이용해서 작성수행
		//3. 작업수행한 sqlSession을 닫기(close)
		
		return sqlSession.selectList("dept.dept_list");
	}
	
	
}
