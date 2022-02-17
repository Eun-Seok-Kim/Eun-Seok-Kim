package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.SungVo;

/*
   DAO(Data Access Object)
   1.CRUD����� ����ϴ� ��ü
     Create : insert
     Read   : select
     Update : update
     Delete : delete
*/


public class SungTBDao {

	//singleton : ��ü 1���� �����ؼ� ���������� ���� ����
	static SungTBDao single = null;

	public static SungTBDao getInstance() {

		if (single == null)
			single = new SungTBDao();

		return single;
	}

	//�ܺο��� ������������ ���� : new SungTBDao() (X)
	private SungTBDao() {
		// TODO Auto-generated constructor stub
	}
	
	//idx�� �ش�Ǵ� ������ 1�� ��ȸ
	public SungVo  selectOne(int idx) {
	
		Connection        conn  = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü 
		ResultSet         rs    = null; //�����ó����ü
		
		//��ȸ����� ���� ��ü
		SungVo vo         = null;
		//                                                       1  <= parameter index
		String       sql  = "select * from sungtb_view where idx=?";
		
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
			    vo = new SungVo(); 
				
				vo.setIdx( rs.getInt("idx") );
				vo.setName( rs.getString("name") );
				vo.setKor( rs.getInt("kor") );
				vo.setEng( rs.getInt("eng") );
				vo.setMat( rs.getInt("mat") );
				vo.setTot( rs.getInt("tot") );
				vo.setAvg( rs.getString("avg") );
				vo.setRank( rs.getInt("rank") );
				
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
	
	
	//��ü������ ��ȸ
	public List<SungVo> selectList(){

		Connection        conn  = null; //���������ü
		PreparedStatement pstmt = null; //SQL���ó����ü 
		ResultSet         rs    = null; //�����ó����ü
		
		//��ȸ����� ���� �÷���
		List<SungVo> list = new ArrayList<SungVo>();
		String       sql  = "select * from sungtb_view";
		
		try {
			//1.Connection ������
			conn = DBService.getInstance().getConnection();
			
			//2.���ó����ü ������
			pstmt = conn.prepareStatement(sql);
			
			//3.�����ó����ü ������
			rs = pstmt.executeQuery();
			
			while(rs.next()) 
			{
			    //���� rs�� ��ġ : �����Ϳ��� ���?	
			    //rs�� ����Ű�� ���ڵ峻�� �ʵ尪�� ���ͼ� VO����	
				SungVo vo = new SungVo(); 
				
				vo.setIdx( rs.getInt("idx") );
				vo.setName( rs.getString("name") );
				vo.setKor( rs.getInt("kor") );
				vo.setEng( rs.getInt("eng") );
				vo.setMat( rs.getInt("mat") );
				vo.setTot( rs.getInt("tot") );
				vo.setAvg( rs.getString("avg") );
				vo.setRank( rs.getInt("rank") );
				
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
		
		return list;
	}

	public int insert(SungVo vo) {
		// TODO Auto-generated method stub
		
		Connection        conn  = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü
		
		int res = 0; //ó���� ���
		
		//                                                               1   2   3   4  <= parameter index
		String sql = "insert into sungtb  values(seq_sungtb_idx.nextVal, ? , ? , ? , ? )";
		
		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);
			
			//3.pstmt parameter index ä���ش�
			pstmt.setString(1, vo.getName());
			pstmt.setInt(   2, vo.getKor());
			pstmt.setInt(   3, vo.getEng());
			pstmt.setInt(   4, vo.getMat());
			
			//4.DB Insert
			res = pstmt.executeUpdate();// select���� ��� ���
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			try {
				//�ݱ�(��������)
				if(pstmt != null) pstmt.close();//2
				if(conn != null)  conn.close(); //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	public int update(SungVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���
        //                                   1       2       3       4              5 
		String sql = "update sungtb set name=? , kor=? , eng=? , mat=?  where idx = ?";

		try {
			//1.Connection������
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement������(���ó����ü)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex ä���ش�
			pstmt.setString(1, vo.getName());
			
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
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

	public int delete(int idx) {
		// TODO Auto-generated method stub

		Connection conn = null; //���������ü
		PreparedStatement pstmt = null; //���ó����ü

		int res = 0; //ó���� ���
        //                                         1 <= parameter index
		String sql = "delete from sungtb where idx=?";

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
	
	

}
