package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.VisitVo;

public class VisitDao {

		//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
		static VisitDao single = null;

		public static VisitDao getInstance() {

			if (single == null)
				single = new VisitDao();

			return single;
		}

		//�ܺο��� ������������ ���� : new VisitDao() (X)
		private VisitDao() {
			// TODO Auto-generated constructor stub
		}
		
		
		//������ ��ȸ
		public List<VisitVo> selectList() {

			Connection conn = null; //���������ü
			PreparedStatement pstmt = null; //SQL���ó����ü 
			ResultSet rs = null; //�����ó����ü

			//��ȸ����� ���� �÷���
			List<VisitVo> list = new ArrayList<VisitVo>();
			String sql = "select * from visit order by idx desc";

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
					VisitVo vo = new VisitVo();
					vo.setIdx(rs.getInt("idx"));
					vo.setName(rs.getString("name"));
					vo.setContent(rs.getString("content"));
					vo.setPwd(rs.getString("pwd"));
					vo.setIp(rs.getString("ip"));
					vo.setRegdate(rs.getString("regdate"));
					
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
		public int insert(VisitVo vo) {
			// TODO Auto-generated method stub

			Connection conn = null; //���������ü
			PreparedStatement pstmt = null; //���ó����ü

			int res = 0; //ó���� ���

			String sql = "insert into visit values(seq_visit_idx.nextVal, ? , ? , ? , ? , sysdate)";

			try {
				//1.Connection������
				conn = DBService.getInstance().getConnection();
				//2.PareparedStatement������(���ó����ü)
				pstmt = conn.prepareStatement(sql);

				//3.pstmt parameterIndex ä���ش�
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getPwd());
				pstmt.setString(4, vo.getIp());
				
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
		
		
		public int delete(int idx) {
			// TODO Auto-generated method stub

			Connection conn = null; //���������ü
			PreparedStatement pstmt = null; //���ó����ü

			int res = 0; //ó���� ���

			String sql = "delete from visit where idx=?";

			try {
				//1.Connection������
				conn = DBService.getInstance().getConnection();
				//2.PareparedStatement������(���ó����ü)
				pstmt = conn.prepareStatement(sql);

				//3.pstmt parameterIndex ä���ش�
				pstmt.setInt(1, idx);
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
		public VisitVo selectOne(int idx) {
			
			Connection        conn  = null; //���������ü
			PreparedStatement pstmt = null; //SQL���ó����ü 
			ResultSet         rs    = null; //�����ó����ü
			
			//��ȸ����� ���� ��ü
			VisitVo vo         = null;
			//                                                 1  <= parameter index
			String       sql  = "select * from visit where idx=?";
								 
			try {
				//1.Connection ������
				conn = DBService.getInstance().getConnection();
				
				//2.���ó����ü ������
				pstmt = conn.prepareStatement(sql);
				
				//2-1.pstmt parameter����
				pstmt.setInt(1, idx);
				
				//3.�����ó����ü ������
				rs = pstmt.executeQuery();
				
				if(rs.next()) 
				{
				    //���� rs�� ��ġ : �����Ϳ��� ���?	
				    //rs�� ����Ű�� ���ڵ峻�� �ʵ尪�� ���ͼ� VO����	
				    vo = new VisitVo(); 
	
					vo.setIdx(rs.getInt("idx"));
					vo.setName(rs.getString("name"));
					vo.setContent(rs.getString("content"));
					vo.setPwd(rs.getString("pwd"));
					vo.setIp(rs.getString("ip"));
					vo.setRegdate(rs.getString("regdate"));

				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				
				try {
					
					//�ݱ�:������������...
					//���������� �ݾƶ�(3)
					if(rs!=null) rs.close();
					
					//���������� �ݾƶ�(2)
					if(pstmt!=null) pstmt.close();
					
					//����Ǿ������� �ݾƶ�(1)
					if(conn!=null) conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//end finally
		
			return vo;
		}
		public int update(VisitVo vo) {
			// TODO Auto-generated method stub

			Connection conn = null; //���������ü
			PreparedStatement pstmt = null; //���ó����ü

			int res = 0; //ó���� ���

			String sql = "update visit set name=?, content=?, pwd=?, ip=?, regdate=sysdate where idx=?";
			
			try {
				//1.Connection������
				conn = DBService.getInstance().getConnection();
				//2.PareparedStatement������(���ó����ü)
				pstmt = conn.prepareStatement(sql);

				//3.pstmt parameterIndex ä���ش�
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getPwd());
				pstmt.setString(4, vo.getIp());
				pstmt.setInt(5, vo.getIdx());
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
