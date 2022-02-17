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

		//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
		static VisitDao single = null;

		public static VisitDao getInstance() {

			if (single == null)
				single = new VisitDao();

			return single;
		}

		//외부에서 직접생성하지 말것 : new VisitDao() (X)
		private VisitDao() {
			// TODO Auto-generated constructor stub
		}
		
		
		//데이터 조회
		public List<VisitVo> selectList() {

			Connection conn = null; //연결관리객체
			PreparedStatement pstmt = null; //SQL명령처리객체 
			ResultSet rs = null; //결과행처리객체

			//조회결과을 담을 컬렉션
			List<VisitVo> list = new ArrayList<VisitVo>();
			String sql = "select * from visit order by idx desc";

			try {
				//1.Connection 얻어오기
				conn = DBService.getInstance().getConnection();

				//2.명령처리객체 얻어오기
				pstmt = conn.prepareStatement(sql);

				//3.결과행처리객체 얻어오기
				rs = pstmt.executeQuery();

				while (rs.next()) {
					//현재 rs의 위치 : 데이터영역 어딘가?	
					//rs가 가리키는 레코드내의 필드값을 얻어와서 VO포장	
					VisitVo vo = new VisitVo();
					vo.setIdx(rs.getInt("idx"));
					vo.setName(rs.getString("name"));
					vo.setContent(rs.getString("content"));
					vo.setPwd(rs.getString("pwd"));
					vo.setIp(rs.getString("ip"));
					vo.setRegdate(rs.getString("regdate"));
					
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
					if (rs != null)
						rs.close();
					//열려있으면 닫아라(2)
					if (pstmt != null)
						pstmt.close();
					//연결되어있으면 닫아라(1)
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

			Connection conn = null; //연결관리객체
			PreparedStatement pstmt = null; //명령처리객체

			int res = 0; //처리된 행수

			String sql = "insert into visit values(seq_visit_idx.nextVal, ? , ? , ? , ? , sysdate)";

			try {
				//1.Connection얻어오기
				conn = DBService.getInstance().getConnection();
				//2.PareparedStatement얻어오기(명령처리객체)
				pstmt = conn.prepareStatement(sql);

				//3.pstmt parameterIndex 채워준다
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getPwd());
				pstmt.setString(4, vo.getIp());
				
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

			String sql = "delete from visit where idx=?";

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
		public VisitVo selectOne(int idx) {
			
			Connection        conn  = null; //연결관리객체
			PreparedStatement pstmt = null; //SQL명령처리객체 
			ResultSet         rs    = null; //결과행처리객체
			
			//조회결과를 담을 객체
			VisitVo vo         = null;
			//                                                 1  <= parameter index
			String       sql  = "select * from visit where idx=?";
								 
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
		public int update(VisitVo vo) {
			// TODO Auto-generated method stub

			Connection conn = null; //연결관리객체
			PreparedStatement pstmt = null; //명령처리객체

			int res = 0; //처리된 행수

			String sql = "update visit set name=?, content=?, pwd=?, ip=?, regdate=sysdate where idx=?";
			
			try {
				//1.Connection얻어오기
				conn = DBService.getInstance().getConnection();
				//2.PareparedStatement얻어오기(명령처리객체)
				pstmt = conn.prepareStatement(sql);

				//3.pstmt parameterIndex 채워준다
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getPwd());
				pstmt.setString(4, vo.getIp());
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
		
		
}
