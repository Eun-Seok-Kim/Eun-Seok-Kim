package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.MemberVo;

public class MemberDao {

	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static MemberDao single = null;
	SqlSessionFactory factory;
	public static MemberDao getInstance() {

		if (single == null)
			single = new MemberDao();

		return single;
	}

	//�ܺο��� ������������ ���� : new MemberDao() (X)
	private MemberDao() {
		// TODO Auto-generated constructor stub
	factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//��ü��ȸ
	public List<MemberVo> selectList() {

		List<MemberVo> list = null;
		SqlSession sqlSession = factory.openSession();	
		list = sqlSession.selectList("member.member_list");
		sqlSession.close();
		return list;
	}
	
	//m_idx�� �ش�Ǵ� ȸ������ 1��
	public MemberVo selectOne(int m_idx) {

		//��ȸ����� ���� ��ü
		MemberVo vo = null;
		SqlSession sqlSession = factory.openSession();	
		vo = sqlSession.selectOne("member.member_list_one_idx",m_idx);
		sqlSession.close();

		return vo;
	}
	
	//m_id�� �ش�Ǵ� ������ 1�� ��ȸ
		public MemberVo selectOne(String m_id) {
	
		MemberVo vo = null;
		SqlSession sqlSession = factory.openSession();	
		vo = sqlSession.selectOne("member.member_list_one_id",m_id);
		sqlSession.close();

		return vo;
	}
	
	
	
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub

		int res = 0; //ó���� ���
		//										 auto commit( �⺻false)
		SqlSession sqlSession = factory.openSession(true);
		//�۾�����
		res = sqlSession.insert("member.member_insert",vo);
		//����� ���뿡 ���� Transaction Commit(����)
		//���Ǵݱ�
		sqlSession.close();		
		
		return res;
	}

	//����
	public int delete(int m_idx) {
		// TODO Auto-generated method stub
		int res = 0; //ó���� ���
		SqlSession sqlSession = factory.openSession(true);
		//�۾�����
		res = sqlSession.delete("member.member_delete",m_idx);
		//����� ���뿡 ���� Transaction Commit(����)
		//���Ǵݱ�
		sqlSession.close();
		
		return res;
	}

	//����
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub

		int res = 0; //ó���� ���
		SqlSession sqlSession = factory.openSession(true);
		//�۾�����
		res = sqlSession.update("member.member_update",vo);
		
		/*
		  //����� ���뿡 ���� Transaction Commit(����) 
		  sqlSession.commit();
		 */			
		//���Ǵݱ�
		sqlSession.close();
	
		return res;

	}
	
	
	
	
	
	
	

}
