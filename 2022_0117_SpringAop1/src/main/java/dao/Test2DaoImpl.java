package dao;

import java.util.ArrayList;
import java.util.List;

public class Test2DaoImpl implements Test2Dao {

	@Override
	public List selectList() {
		// TODO Auto-generated method stub
		
		List list = new ArrayList();
		
		list.add("����");
		list.add("�뱸");
		list.add("�λ�");
		list.add("���");
		return list;
	}

}
