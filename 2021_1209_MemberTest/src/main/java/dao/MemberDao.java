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

	//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static MemberDao single = null;

	public static MemberDao getInstance() {

		if (single == null)
			single = new MemberDao();

		return single;
	}

	//외부에서 직접생성하지 말것 : new MemberDao() (X)
	
	
	
	//데이터조회-관리자만 가능
	private MemberDao() {
		// TODO Auto-generated constructor stub
	}

	public List<MemberVo> selectList() {

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //SQL명령처리객체 
		ResultSet rs = null; //결과행처리객체

		//조회결과을 담을 컬렉션
		List<MemberVo> list = new ArrayList<MemberVo>();
		//idx순서대로 조회
		String sql = "select * from member order by m_idx";

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
	//m_idx에 해당되는 회원정보 1건
	public MemberVo selectOne(int m_idx) {

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //SQL명령처리객체 
		ResultSet rs = null; //결과행처리객체

		//조회결과를 담을 객체
		MemberVo vo = null;
		String sql = "select * from member where m_idx=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3. pstmt parameter설정
			pstmt.setInt(1,m_idx);
			//4.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//현재 rs의 위치 : 데이터영역 어딘가?	
				//rs가 가리키는 레코드내의 필드값을 얻어와서 VO포장	
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

		return vo;
	}
	
	public MemberVo selectOne(String m_id) {

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //SQL명령처리객체 
		ResultSet rs = null; //결과행처리객체

		//조회결과를 담을 객체
		MemberVo vo = null;
		String sql = "select * from member where m_id=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3. pstmt parameter설정
			pstmt.setString(1, vo.getM_id());
			//4.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//현재 rs의 위치 : 데이터영역 어딘가?	
				//rs가 가리키는 레코드내의 필드값을 얻어와서 VO포장	
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

		return vo;
	}
	
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수

		String sql = "insert into member values( (select nvl(max(m_idx),0)+1 from member),?, ?, ?, ?, ?, ?, sysdate, '일반')";


		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex 채워준다
			pstmt.setString(1, vo.getM_name());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_pwd());
			pstmt.setString(4, vo.getM_zipcode());
			pstmt.setString(5, vo.getM_addr());
			pstmt.setString(6, vo.getM_ip());
			
													
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
	
	public int delete(int m_idx) {
		// TODO Auto-generated method stub

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수

		String sql = "delete from member where m_idx=?";

		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameterIndex 채워준다
			pstmt.setInt(1, m_idx);
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
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //명령처리객체

		int res = 0; //처리된 행수

		String sql = "update member set m_pwd=?, m_id=?, m_zipcode=?, m_addr=?, m_ip=?, m_regdate=sysdate where m_idx=?";
		try {
			//1.Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PareparedStatement얻어오기(명령처리객체)
			pstmt = conn.prepareStatement(sql);
			//3.pstmt parameterIndex 채워준다
			pstmt.setString(1, vo.getM_pwd());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_zipcode());
			pstmt.setString(4, vo.getM_addr());
			pstmt.setString(5, vo.getM_ip());
			pstmt.setInt(6, vo.getM_idx());
	
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
