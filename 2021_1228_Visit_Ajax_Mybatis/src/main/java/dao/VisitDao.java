package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.VisitVo;

public class VisitDao {

		//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
		static VisitDao single = null;
		//Mybatis ��ü ����
		SqlSessionFactory factory;
		
		public static VisitDao getInstance() {

			if (single == null)
				single = new VisitDao();

			return single;
		}

		//�ܺο��� ������������ ���� : new VisitDao() (X)
		private VisitDao() {
			// TODO Auto-generated constructor stub
			factory = MyBatisConnector.getInstance().getSqlSessionFactory();
		}
		
		
		//������ ��ȸ
		public List<VisitVo> selectList() {
			
			List<VisitVo> list = null;
			SqlSession sqlSession = factory.openSession();
			
			list = sqlSession.selectList("visit.visit_list");
			sqlSession.close();
			return list;
		}//����Ʈ ��
		
		public List<VisitVo> selectList(Map map) {
			
			List<VisitVo> list = null;
			SqlSession sqlSession = factory.openSession();
			
			list = sqlSession.selectList("visit.visit_condition_list",map);
			sqlSession.close();
			return list;
		}// map ����Ʈ ��
		
		public VisitVo selectOne(int idx) {
			VisitVo vo = null;
			//���ǿ���
			SqlSession sqlSession = factory.openSession();
			//�۾�����
			vo = sqlSession.selectOne("visit.visit_list_one",idx);
			//���Ǵݱ�
			sqlSession.close();

			return vo;
		}
		public int insert(VisitVo vo) {
			// TODO Auto-generated method stub

			int res = 0; //ó���� ���
			//���ǿ���
			//										 auto commit( �⺻false)
			SqlSession sqlSession = factory.openSession(true);
			//�۾�����
			res = sqlSession.insert("visit.visit_insert",vo);
			//����� ���뿡 ���� Transaction Commit(����)
			//���Ǵݱ�
			sqlSession.close();		
			
			return res;
		}
	
		public int delete(int idx) {
			// TODO Auto-generated method stub

			int res = 0; //ó���� ���
			SqlSession sqlSession = factory.openSession(true);
			//�۾�����
			res = sqlSession.delete("visit.visit_delete",idx);
			//����� ���뿡 ���� Transaction Commit(����)
			//���Ǵݱ�
			sqlSession.close();
			
			return res;
		}

		public int update(VisitVo vo) {
			// TODO Auto-generated method stub

			int res = 0; //ó���� ���
			SqlSession sqlSession = factory.openSession(true);
			//�۾�����
			res = sqlSession.update("visit.visit_update",vo);
			
			/*
			  //����� ���뿡 ���� Transaction Commit(����) 
			  sqlSession.commit();
			 */
			
			//���Ǵݱ�
			sqlSession.close();
		
			return res;
		}

		
		
}
