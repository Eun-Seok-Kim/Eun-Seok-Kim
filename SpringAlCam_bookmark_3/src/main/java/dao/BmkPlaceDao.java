package dao;

import java.util.List;

import vo.BmkPlaceVo;

public interface BmkPlaceDao {
	
	
	List<BmkPlaceVo> selectList(int m_idx);
	BmkPlaceVo       selectOne(BmkPlaceVo vo);
	
	int          insert(BmkPlaceVo vo);
	int          delete(int bmk_p_idx);
	
	
}
