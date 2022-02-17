package dao;

import java.util.List;

import vo.ProductVo;

public interface ProductDao {

	List<ProductVo> selectList();
	ProductVo       selectOne(int idx);
	
	
	int insert(ProductVo vo);
	int update(ProductVo vo);
	int delete(int idx);
	
	//Optional(����) : Product_Remain_DaoImpl(���)
	default ProductVo       selectOne(String name) { return null;}
	default int             update_name(ProductVo vo) { return 0; }
	
}
