package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.VisitVo;

public class VisitDaoImpl implements VisitDao{

	
	SqlSession sqlSession;

	//setter만 생성
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<VisitVo> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("visit.visit_list");
	}

	@Override
	public List<VisitVo> selectList(Map map) {
		// TODO Auto-generated method stub
		//SqlSessionTemplate객체내에서 1.openSession 2.작업수행 3.close()
		return sqlSession.selectList("visit.visit_condition_list",map);
	}

	@Override
	public VisitVo selectOne(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("visit.visit_list_one",idx);
	}

	@Override
	public int insert(VisitVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("visit.visit_insert",vo);
	}

	@Override
	public int update(VisitVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("visit.visit_update",vo);
	}

	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("visit.visit_delete",idx);
	}
	
	
}
