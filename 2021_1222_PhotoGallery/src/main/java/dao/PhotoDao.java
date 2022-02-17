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

	//single-ton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static PhotoDao single = null;

	public static PhotoDao getInstance() {

		if (single == null)
			single = new PhotoDao();

		return single;
	}

	//외부에서 직접 생성하지 말 것 : new PhotoDao (X)   
	private PhotoDao() {
		// TODO Auto-generated constructor stub
	}
	
	//전체조회
	public List<PhotoVo> selectList() {

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //SQL명령처리객체
		ResultSet rs = null; //결과행처리객체

		//조회결과를 담을 컬렉션
		List<PhotoVo> list = new ArrayList<PhotoVo>();
		String sql = "select * from photo order by p_idx desc";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.명령처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//현재 rs의 위치 : 데이터영역 어딘가?
				//rs가 가리키는 레코드 내의 필드값을 얻어와서 VO포장
				PhotoVo vo = new PhotoVo();
				
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_subject(rs.getString("p_subject"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setP_modifydate(rs.getString("p_modifydate"));
				vo.setM_idx(rs.getInt("m_idx"));

				//ArrayList추가
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {

				//닫기:열린역순으로
				//열려있으면 닫아라(3)
				if (rs != null)
					rs.close();
				//열려있으면 닫아라(2)
				if (pstmt != null)
					pstmt.close();
				//연결되어 있으면 닫아라(1)
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} //end finally

		return list;
	}
	
	//p_idx에 해당되는 데이터 1건 조회
	public PhotoVo selectOne(int p_idx) {

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //SQL명령처리객체
		ResultSet rs = null; //결과행처리객체

		//조회결과를 담을 객체
		PhotoVo vo = null;
		//                                            1 <- parameter index
		String sql = "select * from photo where p_idx=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.명령처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter설정
			pstmt.setInt(1, p_idx);

			//4.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//현재 rs의 위치 : 데이터영역 어딘가?
				//rs가 가리키는 레코드 내의 필드값을 얻어와서 VO포장
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

				//닫기:열린역순으로
				//열려있으면 닫아라(3)
				if (rs != null)
					rs.close();

				//열려있으면 닫아라(2)
				if (pstmt != null)
					pstmt.close();

				//연결되어 있으면 닫아라(1)      만약 못 닫으면 어떻게 할지 try~catch한거,,
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

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수

		//                                                            1  2  3  4                    5
		String sql = "insert into photo values(seq_photo_idx.nextVal, ?, ?, ?, ?, sysdate, sysdate, ?)";

		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex 채워준다   
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_filename());
			pstmt.setString(4, vo.getP_ip());
			pstmt.setInt(5, vo.getM_idx());

			//4.DB처리
			res = pstmt.executeUpdate(); //select 외의 모든 명령

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(열린역순)
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
	

	//이미지변경만 update
	public int update_img(PhotoVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수

		//                                       1           2      3                            4             5
		String sql = "update photo set p_filename=? where p_idx=?";

		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex 채워준다
			pstmt.setString(1, vo.getP_filename());
			pstmt.setInt(2, vo.getP_idx());

			//4.DB처리
			res = pstmt.executeUpdate(); //select 외의 모든 명령

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(열린역순)
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

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수

		//                                       1           2      3                            4             5
		String sql = "update photo set p_subject=?,p_content=?, p_ip=?, p_modifydate=sysdate where p_idx=?";

		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex 채워준다   
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_ip());
			pstmt.setInt(4, vo.getP_idx());

			//4.DB처리
			res = pstmt.executeUpdate(); //select 외의 모든 명령

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(열린역순)
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

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수

		String sql = "delete from photo where p_idx=?";

		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex 채워준다   
			pstmt.setInt(1, p_idx);

			//4.DB처리
			res = pstmt.executeUpdate(); //select 외의 모든 명령

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(열린역순)
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
