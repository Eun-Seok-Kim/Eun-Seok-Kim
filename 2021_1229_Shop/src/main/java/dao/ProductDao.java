package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.ProductVo;

public class ProductDao {
//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
static ProductDao single = null;
SqlSessionFactory factory;
public static ProductDao getInstance() {

	if (single == null)
		single = new ProductDao();

	return single;
}

//�ܺο��� ������������ ���� : new ProductDao() (X)
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

	//��ȸ����� ���� ��ü
	ProductVo vo = null;
	//���ǿ���
	SqlSession sqlSession = factory.openSession();
	//�۾�����
	vo = sqlSession.selectOne("product.product_list_one",p_idx);
	//���Ǵݱ�
	sqlSession.close();

	return vo;
}		
public int insert(ProductVo vo) {
	// TODO Auto-generated method stub
	int res = 0; //ó���� ���
	//										 auto commit( �⺻false)
	SqlSession sqlSession = factory.openSession(true);
	//�۾�����
	res = sqlSession.insert("product.product_insert",vo);
	//����� ���뿡 ���� Transaction Commit(����)
	//���Ǵݱ�
	sqlSession.close();		
	
	return res;
}


}
