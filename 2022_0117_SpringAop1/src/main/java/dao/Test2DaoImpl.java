package dao;

import java.util.ArrayList;
import java.util.List;

public class Test2DaoImpl implements Test2Dao {

	@Override
	public List selectList() {
		// TODO Auto-generated method stub
		
		List list = new ArrayList();
		
		list.add("서울");
		list.add("대구");
		list.add("부산");
		list.add("경기");
		return list;
	}

}
