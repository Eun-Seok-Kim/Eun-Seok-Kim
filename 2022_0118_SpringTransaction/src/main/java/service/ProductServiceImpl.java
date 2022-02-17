package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ProductDao;
import vo.ProductVo;

public class ProductServiceImpl implements ProductService {

	ProductDao product_in_dao; // 입고
	ProductDao product_out_dao; // 출고
	ProductDao product_remain_dao; // 재고

	public ProductServiceImpl(ProductDao product_in_dao, ProductDao product_out_dao, ProductDao product_remain_dao) {
		super();
		this.product_in_dao = product_in_dao;
		this.product_out_dao = product_out_dao;
		this.product_remain_dao = product_remain_dao;
	}

	@Override
	public Map selectList() {
		// TODO Auto-generated method stub
		List<ProductVo> in_list     = product_in_dao.selectList();       //입고목록
		List<ProductVo> out_list    = product_out_dao.selectList();      //출고목록
		List<ProductVo> remain_list = product_remain_dao.selectList();   //재고목록
		
		Map map = new HashMap();
		map.put("in_list", in_list);
		map.put("out_list", out_list);
		map.put("remain_list", remain_list);
		
		return map;
	}

	@Override
	public int insert_in(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		//1.입고테이블 등록
		int res = product_in_dao.insert(vo);
		System.out.println(res);
		//2.재고테이블에 현재 등록상품정보 얻어오기
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());
		
		if(remainVo==null) {
			//재고목록에 없을경우 새로등록
			res = product_remain_dao.insert(vo);
			
		}else {
			//재고목록에 있을경우 수정
			//수량 = 기존수량 + 새수량
			int cnt = remainVo.getCnt() + vo.getCnt();
			remainVo.setCnt(cnt);	
			res = product_remain_dao.update_name(remainVo);		
		}
		
		return res;
	}

	@Override
	public int insert_out(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		//1.출고테이블 등록
		int res = product_out_dao.insert(vo);
		System.out.println(res);
		//2.재고테이블에 현재 출고상품정보 얻어오기
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());
		
		if(remainVo==null) {//출고된 상품이 재고테이블에는 존재하지 않는다
			
			//예외를 발생
			throw new Exception("remain_not");
		}else {
			//출고수량이 큰 경우
			if(vo.getCnt()>remainVo.getCnt()) {
				//예외발생
				throw new Exception("remain_lack");
			}
		}
		int cnt = remainVo.getCnt() - vo.getCnt();
		remainVo.setCnt(cnt);	
		res = product_remain_dao.update_name(remainVo);	
		return res;
	}
	
	@Override
	public int delete_in(ProductVo vo) throws Exception{
		// TODO Auto-generated method stub
		//삭제할 상품정보 얻어오기
		ProductVo delVo = product_in_dao.selectOne(vo.getIdx());
		//입고 삭제
		int res = product_in_dao.delete(vo.getIdx());
		
		//입고 취소 상품정보 remain갱신
		ProductVo remainVo = product_remain_dao.selectOne(delVo.getName());
		
		if(delVo.getCnt()>remainVo.getCnt()) {
			//재고수량과 취소수량 비교 후 메시지
			throw new Exception("in_remain_lack");
		}
		//재고수량 수정
		int cnt = remainVo.getCnt()-delVo.getCnt();
		remainVo.setCnt(cnt);
		res = product_remain_dao.update(remainVo);
		
		return res;
	}
	
	
	@Override
	public int delete_out(ProductVo vo) throws Exception{
		//출고취소할 상품정보 얻어오기
		ProductVo insVo = product_out_dao.selectOne(vo.getIdx());
		//출고 삭제
		int res = product_out_dao.delete(vo.getIdx());
		
		//입고 취소 상품정보 remain갱신
		ProductVo remainVo = product_remain_dao.selectOne(insVo.getName());
		//재고수량 수정
		int cnt = remainVo.getCnt()+insVo.getCnt();
		remainVo.setCnt(cnt);
		res = product_remain_dao.update(remainVo);
		
		return res;
	}
	
}








