package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.SawonVo;

@Repository("sawon_dao")
public class SawonDaoImpl implements SawonDao {
	
	
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<SawonVo> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sawon.sawon_list");
	}

}
