package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.PhotoVo;

public class PhotoDao {

	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static PhotoDao single = null;
	SqlSessionFactory factory;
	public static PhotoDao getInstance() {

		if (single == null)
			single = new PhotoDao();

		return single;
	}

	//�ܺο��� ������������ ���� : new PhotoDao() (X)
	private PhotoDao() {
		// TODO Auto-generated constructor stub
	factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//��ü��ȸ
	public List<PhotoVo> selectList() {
	
		//��ȸ����� ���� �÷���

		List<PhotoVo> list = null;
		SqlSession sqlSession = factory.openSession();
		
		list = sqlSession.selectList("photo.photo_list");
		sqlSession.close();
		return list;
		
	}
	
	
	//p_idx�� �ش�Ǵ� �Խù� 1�� ������
	public PhotoVo selectOne(int p_idx) {

		//��ȸ����� ���� ��ü
		PhotoVo vo = null;
		//���ǿ���
		SqlSession sqlSession = factory.openSession();
		//�۾�����
		vo = sqlSession.selectOne("photo.photo_list_one",p_idx);
		//���Ǵݱ�
		sqlSession.close();

		return vo;
	}

	public int insert(PhotoVo vo) {
		// TODO Auto-generated method stub
		int res = 0; //ó���� ���
		//										 auto commit( �⺻false)
		SqlSession sqlSession = factory.openSession(true);
		//�۾�����
		res = sqlSession.insert("photo.photo_insert",vo);
		//����� ���뿡 ���� Transaction Commit(����)
		//���Ǵݱ�
		sqlSession.close();		
		
		return res;
	}
	public int delete(int p_idx) {
		// TODO Auto-generated method stub
		int res = 0; //ó���� ���
		SqlSession sqlSession = factory.openSession(true);
		//�۾�����
		res = sqlSession.delete("photo.photo_delete",p_idx);
		//����� ���뿡 ���� Transaction Commit(����)
		//���Ǵݱ�
		sqlSession.close();
		
		return res;
	}	
	
	public int update(PhotoVo vo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		int res = 0; //ó���� ���
		SqlSession sqlSession = factory.openSession(true);
		//�۾�����
		res = sqlSession.update("photo.photo_update",vo);
		
		/*
		  //����� ���뿡 ���� Transaction Commit(����) 
		  sqlSession.commit();
		 */
		
		//���Ǵݱ�
		sqlSession.close();
	
		return res;

	}
	
	
	
	
	
	
	

}
