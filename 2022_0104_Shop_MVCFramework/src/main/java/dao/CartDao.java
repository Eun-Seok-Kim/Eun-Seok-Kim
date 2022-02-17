package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.CartVo;
import vo.ProductVo;

public class CartDao {
//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
static CartDao single = null;
SqlSessionFactory factory;
public static CartDao getInstance() {

	if (single == null)
		single = new CartDao();

	return single;
}

//외부에서 직접생성하지 말것 : new CartDao() (X)
private CartDao() {
	// TODO Auto-generated constructor stub
	factory = MyBatisConnector.getInstance().getSqlSessionFactory();
}
public List<CartVo> selectList(int m_idx) {

	List<CartVo> list = null;
	SqlSession sqlSession = factory.openSession();	
	list = sqlSession.selectList("cart.cart_list",m_idx);
	sqlSession.close();
	return list;
}

public int selectTotalAmount(int m_idx) {
	// TODO Auto-generated method stub
	int total_amount = 0;
	SqlSession sqlSession = factory.openSession();
	
	total_amount = sqlSession.selectOne("cart.cart_total_amount",m_idx);
	
	
	sqlSession.close();
	return total_amount;
}
public int delete(int c_idx) {
	// TODO Auto-generated method stub
	int res = 0; //처리된 행수
	SqlSession sqlSession = factory.openSession(true);
	//작업수행
	res = sqlSession.delete("cart.cart_delete",c_idx);
	//수행된 내용에 대한 Transaction Commit(적용)
	//세션닫기
	sqlSession.close();
	
	return res;
}

public int update(CartVo vo) {
	int res = 0; //처리된 행수
	SqlSession sqlSession = factory.openSession(true);
	//작업수행
	res = sqlSession.delete("cart.cart_update",vo);
	//수행된 내용에 대한 Transaction Commit(적용)
	//세션닫기
	sqlSession.close();

	return res;
}
public CartVo selectOne(CartVo paramVo) {

	CartVo list = null;
	SqlSession sqlSession = factory.openSession();	
	list = sqlSession.selectOne("cart.cart_select_one",paramVo);
	sqlSession.close();
	return list;
}

public int insert(CartVo paramVo) {
	// TODO Auto-generated method stub
	int res = 0; //처리된 행수
	SqlSession sqlSession = factory.openSession(true);
	//작업수행
	res = sqlSession.insert("cart.cart_insert",paramVo);
	//수행된 내용에 대한 Transaction Commit(적용)
	//세션닫기
	sqlSession.close();
	return res;
}






}
