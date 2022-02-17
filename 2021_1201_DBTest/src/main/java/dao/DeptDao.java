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

	//singleton : 객체 1개만 생성해서 지속적으로 서비스 수행
	static DeptDao single = null;

	public static DeptDao getInstance() {

		if (single == null)
			single = new DeptDao();

		return single;
	}

	//외부에서 직접생성하지 말것 : new DeptDao() (X)
	private DeptDao() {
		// TODO Auto-generated constructor stub
	}

	public List<DeptVo> selectList() {

		Connection conn = null; //연결관리객체
		PreparedStatement pstmt = null; //SQL명령처리객체 
		ResultSet rs = null; //결과행처리객체

		//조회결과을 담을 컬렉션
		List<DeptVo> list = new ArrayList<DeptVo>();
		String sql = "select * from dept";

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
				DeptVo vo = new DeptVo();
				
				vo.setDeptno(rs.getInt("deptno"));
				vo.setDname(rs.getString("dname"));
				vo.setLoc(rs.getString("loc"));
				
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
	
	
	
	
}
