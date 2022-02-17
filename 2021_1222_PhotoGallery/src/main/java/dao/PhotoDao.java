package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.PhotoVo;

public class PhotoDao {

	//single-ton : ��ü 1���� �����ؼ� ���������� ���� ����
	static PhotoDao single = null;

	public static PhotoDao getInstance() {

		if (single == null)
			single = new PhotoDao();

		return single;
	}

	//�ܺο��� ���� �������� �� �� : new PhotoDao (X)   
	private PhotoDao() {
		// TODO Auto-generated constructor stub
	}
	
	//��ü��ȸ
	public List<PhotoVo> selectList() {

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü
		ResultSet rs = null; //�����ó����ü

		//��ȸ����� ���� �÷���
		List<PhotoVo> list = new ArrayList<PhotoVo>();
		String sql = "select * from photo order by p_idx desc";

		try {
			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.���ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);

			//3.�����ó����ü ������
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//���� rs�� ��ġ : �����Ϳ��� ���?
				//rs�� ����Ű�� ���ڵ� ���� �ʵ尪�� ���ͼ� VO����
				PhotoVo vo = new PhotoVo();
				
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_subject(rs.getString("p_subject"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setP_modifydate(rs.getString("p_modifydate"));
				vo.setM_idx(rs.getInt("m_idx"));

				//ArrayList�߰�
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {

				//�ݱ�:������������
				//���������� �ݾƶ�(3)
				if (rs != null)
					rs.close();
				//���������� �ݾƶ�(2)
				if (pstmt != null)
					pstmt.close();
				//����Ǿ� ������ �ݾƶ�(1)
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} //end finally

		return list;
	}
	
	//p_idx�� �ش�Ǵ� ������ 1�� ��ȸ
	public PhotoVo selectOne(int p_idx) {

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü
		ResultSet rs = null; //�����ó����ü

		//��ȸ����� ���� ��ü
		PhotoVo vo = null;
		//                                            1 <- parameter index
		String sql = "select * from photo where p_idx=?";

		try {
			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.���ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter����
			pstmt.setInt(1, p_idx);

			//4.�����ó����ü ������
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//���� rs�� ��ġ : �����Ϳ��� ���?
				//rs�� ����Ű�� ���ڵ� ���� �ʵ尪�� ���ͼ� VO����
				vo = new PhotoVo();
				
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_subject(rs.getString("p_subject"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setP_modifydate(rs.getString("p_modifydate"));
				vo.setM_idx(rs.getInt("m_idx"));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {

				//�ݱ�:������������
				//���������� �ݾƶ�(3)
				if (rs != null)
					rs.close();

				//���������� �ݾƶ�(2)
				if (pstmt != null)
					pstmt.close();

				//����Ǿ� ������ �ݾƶ�(1)      ���� �� ������ ��� ���� try~catch�Ѱ�,,
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} //end finally

		return vo;
	}
	
	//insert
	public int insert(PhotoVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���

		//                                                            1  2  3  4                    5
		String sql = "insert into photo values(seq_photo_idx.nextVal, ?, ?, ?, ?, sysdate, sysdate, ?)";

		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex ä���ش�   
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_filename());
			pstmt.setString(4, vo.getP_ip());
			pstmt.setInt(5, vo.getM_idx());

			//4.DBó��
			res = pstmt.executeUpdate(); //select ���� ��� ���

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
				if (pstmt != null)
					pstmt.close(); //2
				if (conn != null)
					conn.close(); //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return res;
	}
	

	//�̹������游 update
	public int update_img(PhotoVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���

		//                                       1           2      3                            4             5
		String sql = "update photo set p_filename=? where p_idx=?";

		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex ä���ش�
			pstmt.setString(1, vo.getP_filename());
			pstmt.setInt(2, vo.getP_idx());

			//4.DBó��
			res = pstmt.executeUpdate(); //select ���� ��� ���

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
				if (pstmt != null)
					pstmt.close(); //2
				if (conn != null)
					conn.close(); //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return res;
	}
	
	
	
	
	//update
	public int update(PhotoVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���

		//                                       1           2      3                            4             5
		String sql = "update photo set p_subject=?,p_content=?, p_ip=?, p_modifydate=sysdate where p_idx=?";

		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex ä���ش�   
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_ip());
			pstmt.setInt(4, vo.getP_idx());

			//4.DBó��
			res = pstmt.executeUpdate(); //select ���� ��� ���

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
				if (pstmt != null)
					pstmt.close(); //2
				if (conn != null)
					conn.close(); //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return res;
	}
	
	
	public int delete(int p_idx) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���

		String sql = "delete from photo where p_idx=?";

		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex ä���ش�   
			pstmt.setInt(1, p_idx);

			//4.DBó��
			res = pstmt.executeUpdate(); //select ���� ��� ���

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
				if (pstmt != null)
					pstmt.close(); //2
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
