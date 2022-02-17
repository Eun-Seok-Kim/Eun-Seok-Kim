package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.DeptVo;

public class DeptDao {

	
	SqlSession sqlSession; //Spring���� �����س��� SqlSessionTemplate��ü�� �������̽�

	//�Ʒ� ���� �⺻�����ڸ� ������ �ʴ´�
	
	public DeptDao(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public List<DeptVo> selectList() {
		// TODO Auto-generated method stub
		
		//SqlSessionTemplate������ selectList�� ������ �Ǿ��ִ�.
		//1. factoryBean�� �̿��ؼ� ipenSession()
		//2. mapper id�� �̿��ؼ� �ۼ�����
		//3. �۾������� sqlSession�� �ݱ�(close)
		
		return sqlSession.selectList("dept.dept_list");
	}
	
	
}
