package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.ProductVo;

public class ProductDao {
//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
static ProductDao single = null;
SqlSessionFactory factory;
public static ProductDao getInstance() {

	if (single == null)
		single = new ProductDao();

	return single;
}

//외부에서 직접생성하지 말것 : new ProductDao() (X)
private ProductDao() {
	// TODO Auto-generated constructor stub
	factory = MyBatisConnector.getInstance().getSqlSessionFactory();
}

public List<ProductVo> selectList(String category) {

	List<ProductVo> list = null;
	SqlSession sqlSession = factory.openSession();	
	list = sqlSession.selectList("product.product_list",category);
	sqlSession.close();
	return list;
}
public ProductVo selectOne(int p_idx) {

	//조회결과를 담을 객체
	ProductVo vo = null;
	//세션열기
	SqlSession sqlSession = factory.openSession();
	//작업수행
	vo = sqlSession.selectOne("product.product_list_one",p_idx);
	//세션닫기
	sqlSession.close();

	return vo;
}		
public int insert(ProductVo vo) {
	// TODO Auto-generated method stub
	int res = 0; //처리된 행수
	//										 auto commit( 기본false)
	SqlSession sqlSession = factory.openSession(true);
	//작업수행
	res = sqlSession.insert("product.product_insert",vo);
	//수행된 내용에 대한 Transaction Commit(적용)
	//세션닫기
	sqlSession.close();		
	
	return res;
}


}
