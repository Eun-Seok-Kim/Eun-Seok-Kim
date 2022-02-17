package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.BmkPlaceVo;

public class BmkPlaceDaoImpl implements BmkPlaceDao {
	
	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BmkPlaceVo> selectList(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("bmkplace.bmkplace_list", m_idx);
	}

	@Override
	public BmkPlaceVo selectOne(BmkPlaceVo paramVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("bmkplace.bmkplace_one", paramVo);
	}

	@Override
	public int insert(BmkPlaceVo paramVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("bmkplace.bmkplace_insert", paramVo);
	}


	@Override
	public int delete(int bmk_p_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("bmkplace.bmkplace_delete", bmk_p_idx);
	}

	
}
