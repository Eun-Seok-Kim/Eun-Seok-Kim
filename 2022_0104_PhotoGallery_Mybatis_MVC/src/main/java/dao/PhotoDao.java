package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.PhotoVo;

public class PhotoDao {

	//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static PhotoDao single = null;
	SqlSessionFactory factory;
	public static PhotoDao getInstance() {

		if (single == null)
			single = new PhotoDao();

		return single;
	}

	//외부에서 직접생성하지 말것 : new PhotoDao() (X)
	private PhotoDao() {
		// TODO Auto-generated constructor stub
	factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//전체조회
	public List<PhotoVo> selectList() {
	
		//조회결과을 담을 컬렉션

		List<PhotoVo> list = null;
		SqlSession sqlSession = factory.openSession();
		
		list = sqlSession.selectList("photo.photo_list");
		sqlSession.close();
		return list;
		
	}
	
	
	//p_idx에 해당되는 게시물 1건 얻어오기
	public PhotoVo selectOne(int p_idx) {

		//조회결과를 담을 객체
		PhotoVo vo = null;
		//세션열기
		SqlSession sqlSession = factory.openSession();
		//작업수행
		vo = sqlSession.selectOne("photo.photo_list_one",p_idx);
		//세션닫기
		sqlSession.close();

		return vo;
	}

	public int insert(PhotoVo vo) {
		// TODO Auto-generated method stub
		int res = 0; //처리된 행수
		//										 auto commit( 기본false)
		SqlSession sqlSession = factory.openSession(true);
		//작업수행
		res = sqlSession.insert("photo.photo_insert",vo);
		//수행된 내용에 대한 Transaction Commit(적용)
		//세션닫기
		sqlSession.close();		
		
		return res;
	}
	public int delete(int p_idx) {
		// TODO Auto-generated method stub
		int res = 0; //처리된 행수
		SqlSession sqlSession = factory.openSession(true);
		//작업수행
		res = sqlSession.delete("photo.photo_delete",p_idx);
		//수행된 내용에 대한 Transaction Commit(적용)
		//세션닫기
		sqlSession.close();
		
		return res;
	}	
	
	public int update(PhotoVo vo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		int res = 0; //처리된 행수
		SqlSession sqlSession = factory.openSession(true);
		//작업수행
		res = sqlSession.update("photo.photo_update",vo);
		
		/*
		  //수행된 내용에 대한 Transaction Commit(적용) 
		  sqlSession.commit();
		 */
		
		//세션닫기
		sqlSession.close();
	
		return res;

	}
	
	
	
	
	
	
	

}
