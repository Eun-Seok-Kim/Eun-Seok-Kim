package dao;

import java.util.ArrayList;
import java.util.List;

public class TestDaoImpl implements TestDao {

	@Override
	public List selectList() {
		// TODO Auto-generated method stub
		
		List list = new ArrayList();
		
		list.add("사과");
		list.add("참외");
		list.add("수박");
		list.add("딸기");
		return list;
	}

}
