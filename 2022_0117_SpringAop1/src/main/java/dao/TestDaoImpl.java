package dao;

import java.util.ArrayList;
import java.util.List;

public class TestDaoImpl implements TestDao {

	@Override
	public List selectList() {
		// TODO Auto-generated method stub
		
		List list = new ArrayList();
		
		list.add("���");
		list.add("����");
		list.add("����");
		list.add("����");
		return list;
	}

}
