package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.MemberVo;

public class MemberDao {

	//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static MemberDao single = null;
	SqlSessionFactory factory;
	public static MemberDao getInstance() {

		if (single == null)
			single = new MemberDao();

		return single;
	}

	//외부에서 직접생성하지 말것 : new MemberDao() (X)
	private MemberDao() {
		// TODO Auto-generated constructor stub
	factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//전체조회
	public List<MemberVo> selectList() {

		List<MemberVo> list = null;
		SqlSession sqlSession = factory.openSession();	
		list = sqlSession.selectList("member.member_list");
		sqlSession.close();
		return list;
	}
	
	//m_idx에 해당되는 회원정보 1건
	public MemberVo selectOne(int m_idx) {

		//조회결과를 담을 객체
		MemberVo vo = null;
		SqlSession sqlSession = factory.openSession();	
		vo = sqlSession.selectOne("member.member_list_one_idx",m_idx);
		sqlSession.close();

		return vo;
	}
	
	//m_id에 해당되는 데이터 1건 조회
		public MemberVo selectOne(String m_id) {
	
		MemberVo vo = null;
		SqlSession sqlSession = factory.openSession();	
		vo = sqlSession.selectOne("member.member_list_one_id",m_id);
		sqlSession.close();

		return vo;
	}
	
	
	
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub

		int res = 0; //처리된 행수
		//										 auto commit( 기본false)
		SqlSession sqlSession = factory.openSession(true);
		//작업수행
		res = sqlSession.insert("member.member_insert",vo);
		//수행된 내용에 대한 Transaction Commit(적용)
		//세션닫기
		sqlSession.close();		
		
		return res;
	}

	//삭제
	public int delete(int m_idx) {
		// TODO Auto-generated method stub
		int res = 0; //처리된 행수
		SqlSession sqlSession = factory.openSession(true);
		//작업수행
		res = sqlSession.delete("member.member_delete",m_idx);
		//수행된 내용에 대한 Transaction Commit(적용)
		//세션닫기
		sqlSession.close();
		
		return res;
	}

	//수정
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub

		int res = 0; //처리된 행수
		SqlSession sqlSession = factory.openSession(true);
		//작업수행
		res = sqlSession.update("member.member_update",vo);
		
		/*
		  //수행된 내용에 대한 Transaction Commit(적용) 
		  sqlSession.commit();
		 */			
		//세션닫기
		sqlSession.close();
	
		return res;

	}
	
	
	
	
	
	
	

}
