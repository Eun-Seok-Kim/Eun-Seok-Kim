package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.SawonVo;

public class SawonDao {

	SqlSession sqlSession;

	public SawonDao(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public List<SawonVo> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sawon.sawon_list");
	}
	
	
}
