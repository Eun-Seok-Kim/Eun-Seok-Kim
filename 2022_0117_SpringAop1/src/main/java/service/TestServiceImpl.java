package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Test2Dao;
import dao.TestDao;

public class TestServiceImpl implements TestService {

	TestDao dao1;
	Test2Dao dao2;
	
	
	// Constructor Injection
	public TestServiceImpl(TestDao dao1, Test2Dao dao2) {
		super();
		this.dao1 = dao1;
		this.dao2 = dao2;
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
		// business logic (수행코드)
		try {
			Thread.sleep(1234);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Map selectlist() {
		// TODO Auto-generated method stub
		
		List list1 = dao1.selectList();
		List list2 = dao2.selectList();
		
		Map map = new HashMap();
		map.put("list1", list1);
		map.put("list2", list2);
		return map;
	}
	
}
