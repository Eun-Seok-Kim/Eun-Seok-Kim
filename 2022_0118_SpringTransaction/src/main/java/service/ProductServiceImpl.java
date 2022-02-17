package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ProductDao;
import vo.ProductVo;

public class ProductServiceImpl implements ProductService {

	ProductDao product_in_dao; // �԰�
	ProductDao product_out_dao; // ���
	ProductDao product_remain_dao; // ���

	public ProductServiceImpl(ProductDao product_in_dao, ProductDao product_out_dao, ProductDao product_remain_dao) {
		super();
		this.product_in_dao = product_in_dao;
		this.product_out_dao = product_out_dao;
		this.product_remain_dao = product_remain_dao;
	}

	@Override
	public Map selectList() {
		// TODO Auto-generated method stub
		List<ProductVo> in_list     = product_in_dao.selectList();       //�԰���
		List<ProductVo> out_list    = product_out_dao.selectList();      //�����
		List<ProductVo> remain_list = product_remain_dao.selectList();   //�����
		
		Map map = new HashMap();
		map.put("in_list", in_list);
		map.put("out_list", out_list);
		map.put("remain_list", remain_list);
		
		return map;
	}

	@Override
	public int insert_in(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		//1.�԰����̺� ���
		int res = product_in_dao.insert(vo);
		System.out.println(res);
		//2.������̺� ���� ��ϻ�ǰ���� ������
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());
		
		if(remainVo==null) {
			//����Ͽ� ������� ���ε��
			res = product_remain_dao.insert(vo);
			
		}else {
			//����Ͽ� ������� ����
			//���� = �������� + ������
			int cnt = remainVo.getCnt() + vo.getCnt();
			remainVo.setCnt(cnt);	
			res = product_remain_dao.update_name(remainVo);		
		}
		
		return res;
	}

	@Override
	public int insert_out(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		//1.������̺� ���
		int res = product_out_dao.insert(vo);
		System.out.println(res);
		//2.������̺� ���� ����ǰ���� ������
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());
		
		if(remainVo==null) {//���� ��ǰ�� ������̺��� �������� �ʴ´�
			
			//���ܸ� �߻�
			throw new Exception("remain_not");
		}else {
			//�������� ū ���
			if(vo.getCnt()>remainVo.getCnt()) {
				//���ܹ߻�
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
		//������ ��ǰ���� ������
		ProductVo delVo = product_in_dao.selectOne(vo.getIdx());
		//�԰� ����
		int res = product_in_dao.delete(vo.getIdx());
		
		//�԰� ��� ��ǰ���� remain����
		ProductVo remainVo = product_remain_dao.selectOne(delVo.getName());
		
		if(delVo.getCnt()>remainVo.getCnt()) {
			//�������� ��Ҽ��� �� �� �޽���
			throw new Exception("in_remain_lack");
		}
		//������ ����
		int cnt = remainVo.getCnt()-delVo.getCnt();
		remainVo.setCnt(cnt);
		res = product_remain_dao.update(remainVo);
		
		return res;
	}
	
	
	@Override
	public int delete_out(ProductVo vo) throws Exception{
		//�������� ��ǰ���� ������
		ProductVo insVo = product_out_dao.selectOne(vo.getIdx());
		//��� ����
		int res = product_out_dao.delete(vo.getIdx());
		
		//�԰� ��� ��ǰ���� remain����
		ProductVo remainVo = product_remain_dao.selectOne(insVo.getName());
		//������ ����
		int cnt = remainVo.getCnt()+insVo.getCnt();
		remainVo.setCnt(cnt);
		res = product_remain_dao.update(remainVo);
		
		return res;
	}
	
}








