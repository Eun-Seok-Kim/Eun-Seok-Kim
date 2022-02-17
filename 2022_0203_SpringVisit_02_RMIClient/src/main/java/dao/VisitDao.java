package dao;

import java.util.List;
import java.util.Map;

import vo.VisitVo;

public interface VisitDao {

	List<VisitVo> selectList();
	List<VisitVo> selectList(Map map);
	VisitVo		  selectOne(int idx);
	
	int insert(VisitVo vo);
	int update(VisitVo vo);
	int delete(int idx);
	
}
