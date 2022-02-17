package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.MemberVo;

public class MemberDao {

	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static MemberDao single = null;

	public static MemberDao getInstance() {

		if (single == null)
			single = new MemberDao();

		return single;
	}

	//�ܺο��� ������������ ���� : new MemberDao() (X)
	
	
	
	//��������ȸ-�����ڸ� ����
	private MemberDao() {
		// TODO Auto-generated constructor stub
	}

	public List<MemberVo> selectList() {

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü 
		ResultSet rs = null; //�����ó����ü

		//��ȸ����� ���� �÷���
		List<MemberVo> list = new ArrayList<MemberVo>();
		//idx������� ��ȸ
		String sql = "select * from member order by m_idx";

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
				MemberVo vo = new MemberVo();
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_id(rs.getString("m_id"));	
				vo.setM_pwd(rs.getString("m_pwd"));				
				vo.setM_zipcode(rs.getString("m_zipcode"));
				vo.setM_addr(rs.getString("m_addr"));
				vo.setM_ip(rs.getString("m_ip"));
				vo.setM_regdate(rs.getString("m_regdate"));
				vo.setM_grade(rs.getString("m_grade"));
				
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
	//m_idx�� �ش�Ǵ� ȸ������ 1��
	public MemberVo selectOne(int m_idx) {

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü 
		ResultSet rs = null; //�����ó����ü

		//��ȸ����� ���� ��ü
		MemberVo vo = null;
		String sql = "select * from member where m_idx=?";

		try {
			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//3. pstmt parameter����
			pstmt.setInt(1,m_idx);
			//4.�����ó����ü ������
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//���� rs�� ��ġ : �����Ϳ��� ���?	
				//rs�� ����Ű�� ���ڵ峻�� �ʵ尪�� ���ͼ� VO����	
				vo = new MemberVo();
				
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_id(rs.getString("m_id"));	
				vo.setM_pwd(rs.getString("m_pwd"));				
				vo.setM_zipcode(rs.getString("m_zipcode"));
				vo.setM_addr(rs.getString("m_addr"));
				vo.setM_ip(rs.getString("m_ip"));
				vo.setM_regdate(rs.getString("m_regdate"));
				
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

		return vo;
	}
	
	public MemberVo selectOne(String m_id) {

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü 
		ResultSet rs = null; //�����ó����ü

		//��ȸ����� ���� ��ü
		MemberVo vo = null;
		String sql = "select * from member where m_id=?";

		try {
			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//3. pstmt parameter����
			pstmt.setString(1, vo.getM_id());
			//4.�����ó����ü ������
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//���� rs�� ��ġ : �����Ϳ��� ���?	
				//rs�� ����Ű�� ���ڵ峻�� �ʵ尪�� ���ͼ� VO����	
				vo = new MemberVo();
				
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_id(rs.getString("m_id"));	
				vo.setM_pwd(rs.getString("m_pwd"));				
				vo.setM_zipcode(rs.getString("m_zipcode"));
				vo.setM_addr(rs.getString("m_addr"));
				vo.setM_ip(rs.getString("m_ip"));
				vo.setM_regdate(rs.getString("m_regdate"));
				vo.setM_grade(rs.getString("m_geade"));
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

		return vo;
	}
	
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���

		String sql = "insert into member values( (select nvl(max(m_idx),0)+1 from member),?, ?, ?, ?, ?, ?, sysdate, '�Ϲ�')";


		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex ä���ش�
			pstmt.setString(1, vo.getM_name());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_pwd());
			pstmt.setString(4, vo.getM_zipcode());
			pstmt.setString(5, vo.getM_addr());
			pstmt.setString(6, vo.getM_ip());
			
													
			//4.DBó��
			res = pstmt.executeUpdate();// select���� ��� ���

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
				if (pstmt != null)
					pstmt.close();//2
				if (conn != null)
					conn.close(); //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public int delete(int m_idx) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���

		String sql = "delete from member where m_idx=?";

		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex ä���ش�
			pstmt.setInt(1, m_idx);
			//4.DBó��
			res = pstmt.executeUpdate();// select���� ��� ���

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
				if (pstmt != null)
					pstmt.close();//2
				if (conn != null)
					conn.close(); //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���

		String sql = "update member set m_pwd=?, m_id=?, m_zipcode=?, m_addr=?, m_ip=?, m_regdate=sysdate where m_idx=?";
		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);
			//3.pstmt parameterIndex ä���ش�
			pstmt.setString(1, vo.getM_pwd());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_zipcode());
			pstmt.setString(4, vo.getM_addr());
			pstmt.setString(5, vo.getM_ip());
			pstmt.setInt(6, vo.getM_idx());
	
			//4.DBó��
			res = pstmt.executeUpdate();// select���� ��� ���

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
				if (pstmt != null)
					pstmt.close();//2
				if (conn != null)
					conn.close(); //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
}
