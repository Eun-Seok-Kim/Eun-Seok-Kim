package service;

import java.util.Map;

import vo.ProductVo;

public interface ProductService {

	Map selectList();
	int insert_in(ProductVo vo) throws Exception;
	int insert_out(ProductVo vo) throws Exception;
	
	int delete_in(ProductVo vo) throws Exception;
	int delete_out(ProductVo vo) throws Exception;
}
