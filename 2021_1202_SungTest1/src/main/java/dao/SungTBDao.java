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
   1.CRUD기능을 담당하는 객체
     Create : insert
     Read   : select
     Update : update
     Delete : delete
*/


public class SungTBDao {

	//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static SungTBDao single = null;

	public static SungTBDao getInstance() {

		if (single == null)
			single = new SungTBDao();

		return single;
	}

	//외부에서 직접생성하지 말것 : new SungTBDao() (X)
	private SungTBDao() {
		// TODO Auto-generated constructor stub
	}
	
	//idx에 해당되는 데이터 1건 조회
	public SungVo  selectOne(int idx) {
	
		Connection        conn  = null; //연결관리객체
		PreparedStatement pstmt = null; //SQL명령처리객체 
		ResultSet         rs    = null; //결과행처리객체
		
		//조회결과를 담을 객체
		SungVo vo         = null;
		//                                                       1  <= parameter index
		String       sql  = "select * from sungtb_view where idx=?";
		
		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();
			
			//2.명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			
			//2-1.pstmt parameter설정
			pstmt.setInt(1, idx);
			
			//3.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();
			
			if(rs.next()) 
			{
			    //현재 rs의 위치 : 데이터영역 어딘가?	
			    //rs가 가리키는 레코드내의 필드값을 얻어와서 VO포장	
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
				
				//닫기:열린역순으로...
				//열려있으면 닫아라(3)
				if(rs!=null) rs.close();
				
				//열려있으면 닫아라(2)
				if(pstmt!=null) pstmt.close();
				
				//연결되어있으면 닫아라(1)
				if(conn!=null) conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end finally
	
		return vo;
	}
	
	
	//전체데이터 조회
	public List<SungVo> selectList(){

		Connection        conn  = null; //연결관리객체
		PreparedStatement pstmt = null; //SQL명령처리객체 
		ResultSet         rs    = null; //결과행처리객체
		
		//조회결과을 담을 컬렉션
		List<SungVo> list = new ArrayList<SungVo>();
		String       sql  = "select * from sungtb_view";
		
		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();
			
			//2.명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			
			//3.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();
			
			while(rs.next()) 
			{
			    //현재 rs의 위치 : 데이터영역 어딘가?	
			    //rs가 가리키는 레코드내의 필드값을 얻어와서 VO포장	
				SungVo vo = new SungVo(); 
				
				vo.setIdx( rs.getInt("idx") );
				vo.setName( rs.getString("name") );
				vo.setKor( rs.getInt("kor") );
				vo.setEng( rs.getInt("eng") );
				vo.setMat( rs.getInt("mat") );
				vo.setTot( rs.getInt("tot") );
				vo.setAvg( rs.getString("avg") );
				vo.setRank( rs.getInt("rank") );
				
				//ArrayList추가
				list.add(vo);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			try {
				
				//닫기:열린역순으로...
				//열려있으면 닫아라(3)
				if(rs!=null) rs.close();
				
				//열려있으면 닫아라(2)
				if(pstmt!=null) pstmt.close();
				
				//연결되어있으면 닫아라(1)
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
		
		Connection        conn  = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체
		
		int res = 0; //처리된 행수
		
		//                                                               1   2   3   4  <= parameter index
		String sql = "insert into sungtb  values(seq_sungtb_idx.nextVal, ? , ? , ? , ? )";
		
		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);
			
			//3.pstmt parameter index 채워준다
			pstmt.setString(1, vo.getName());
			pstmt.setInt(   2, vo.getKor());
			pstmt.setInt(   3, vo.getEng());
			pstmt.setInt(   4, vo.getMat());
			
			//4.DB Insert
			res = pstmt.executeUpdate();// select외의 모든 명령
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			try {
				//닫기(열린역순)
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

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수
        //                                   1       2       3       4              5 
		String sql = "update sungtb set name=? , kor=? , eng=? , mat=?  where idx = ?";

		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex 채워준다
			pstmt.setString(1, vo.getName());
			
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			pstmt.setInt(5, vo.getIdx());
			

			//4.DB처리
			res = pstmt.executeUpdate();// select외의 모든 명령

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(열린역순)
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

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수
        //                                         1 <= parameter index
		String sql = "delete from sungtb where idx=?";

		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex 채워준다
            pstmt.setInt(1, idx);
			//4.DB처리
			res = pstmt.executeUpdate();// select외의 모든 명령

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(열린역순)
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
