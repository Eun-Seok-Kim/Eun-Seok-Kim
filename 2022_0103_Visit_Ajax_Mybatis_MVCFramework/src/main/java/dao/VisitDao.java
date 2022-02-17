package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.VisitVo;

public class VisitDao {

		//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
		static VisitDao single = null;
		//Mybatis 객체 선언
		SqlSessionFactory factory;
		
		public static VisitDao getInstance() {

			if (single == null)
				single = new VisitDao();

			return single;
		}

		//외부에서 직접생성하지 말것 : new VisitDao() (X)
		private VisitDao() {
			// TODO Auto-generated constructor stub
			factory = MyBatisConnector.getInstance().getSqlSessionFactory();
		}
		
		
		//데이터 조회
		public List<VisitVo> selectList() {
			
			List<VisitVo> list = null;
			SqlSession sqlSession = factory.openSession();
			
			list = sqlSession.selectList("visit.visit_list");
			sqlSession.close();
			return list;
		}//리스트 끝
		
		public List<VisitVo> selectList(Map map) {
			
			List<VisitVo> list = null;
			SqlSession sqlSession = factory.openSession();
			
			list = sqlSession.selectList("visit.visit_condition_list",map);
			sqlSession.close();
			return list;
		}// map 리스트 끝
		
		public VisitVo selectOne(int idx) {
			VisitVo vo = null;
			//세션열기
			SqlSession sqlSession = factory.openSession();
			//작업수행
			vo = sqlSession.selectOne("visit.visit_list_one",idx);
			//세션닫기
			sqlSession.close();

			return vo;
		}
		public int insert(VisitVo vo) {
			// TODO Auto-generated method stub

			int res = 0; //처리된 행수
			//세션열기
			//										 auto commit( 기본false)
			SqlSession sqlSession = factory.openSession(true);
			//작업수행
			res = sqlSession.insert("visit.visit_insert",vo);
			//수행된 내용에 대한 Transaction Commit(적용)
			//세션닫기
			sqlSession.close();		
			
			return res;
		}
	
		public int delete(int idx) {
			// TODO Auto-generated method stub

			int res = 0; //처리된 행수
			SqlSession sqlSession = factory.openSession(true);
			//작업수행
			res = sqlSession.delete("visit.visit_delete",idx);
			//수행된 내용에 대한 Transaction Commit(적용)
			//세션닫기
			sqlSession.close();
			
			return res;
		}

		public int update(VisitVo vo) {
			// TODO Auto-generated method stub

			int res = 0; //처리된 행수
			SqlSession sqlSession = factory.openSession(true);
			//작업수행
			res = sqlSession.update("visit.visit_update",vo);
			
			/*
			  //수행된 내용에 대한 Transaction Commit(적용) 
			  sqlSession.commit();
			 */
			
			//세션닫기
			sqlSession.close();
		
			return res;
		}

		
		
}
