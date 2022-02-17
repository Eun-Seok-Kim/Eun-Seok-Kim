package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.GogekVo;

public class GogekDao {

	SqlSession sqlSession;

	public GogekDao(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public List<GogekVo> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("gogek.gogek_list");
	}

	
	
	
}
