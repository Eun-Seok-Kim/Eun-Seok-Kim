package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.SawonVo;

public class SawonDao {

	
	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static SawonDao single = null;

	public static SawonDao getInstance() {

		if (single == null)
			single = new SawonDao();

		return single;
	}

	//�ܺο��� ������������ ���� : new SawonDao() (X)
	private SawonDao() {
		// TODO Auto-generated constructor stub
	}
	
	public List<SawonVo> selectList() {

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü 
		ResultSet rs = null; //�����ó����ü

		//��ȸ����� ���� �÷���
		List<SawonVo> list = new ArrayList<SawonVo>();
		String sql = "select * from sawon";

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
				SawonVo vo = new SawonVo();
				
				vo.setSabun(rs.getInt("sabun"));
				vo.setSaname(rs.getString("saname"));
				vo.setSasex(rs.getString("sasex"));
				vo.setDeptno(rs.getInt("deptno"));
				vo.setSajob(rs.getString("sajob"));
				vo.setSahire(rs.getDate("sahire"));
				//vo.setSahire(rs.getString("sahir"));
				//String�� ������� ��½� substring���� �ڿ� ©�����
				vo.setSamgr(rs.getInt("samgr"));
				vo.setSapay(rs.getInt("sapay"));
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
