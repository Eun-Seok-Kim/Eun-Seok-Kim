package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.DeptVo;

/*
  	DAO ( Data Access Object)
  Create	: insert
  Read		: select
  Update	: update
  Delete	: delete
 
 */
public class DeptDao {

	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static DeptDao single = null;

	public static DeptDao getInstance() {

		if (single == null)
			single = new DeptDao();

		return single;
	}

	//�ܺο��� ������������ ���� : new DeptDao() (X)
	private DeptDao() {
		// TODO Auto-generated constructor stub
	}

	public List<DeptVo> selectList() {

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü 
		ResultSet rs = null; //�����ó����ü

		//��ȸ����� ���� �÷���
		List<DeptVo> list = new ArrayList<DeptVo>();
		String sql = "select * from dept";

		try {
			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//3.�����ó����ü ������
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//���� rs�� ��ġ : �����Ϳ��� ���?	
				//rs�� ����Ű�� ���ڵ峻�� �ʵ尪�� ���ͼ� VO����	
				DeptVo vo = new DeptVo();
				
				vo.setDeptno(rs.getInt("deptno"));
				vo.setDname(rs.getString("dname"));
				vo.setLoc(rs.getString("loc"));
				
				//ArrayList�߰�
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�:������������...
				//���������� �ݾƶ�(3)
				if (rs != null)
					rs.close();
				//���������� �ݾƶ�(2)
				if (pstmt != null)
					pstmt.close();
				//����Ǿ������� �ݾƶ�(1)
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} //end finally
		return list;
	}
	
	
	
	
}
