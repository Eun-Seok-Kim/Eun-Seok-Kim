package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import vo.DeptVo;

public class DeptDaoImpl implements DeptDao {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<DeptVo> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("dept.dept_list");
	}

}
