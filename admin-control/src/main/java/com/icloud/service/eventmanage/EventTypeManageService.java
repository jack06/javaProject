package com.icloud.service.eventmanage;

import java.util.List;

import com.icloud.model.bms.Tadmin;
import com.icloud.model.eventmanage.TeventType;
import com.icloud.service.BaseService;

public interface EventTypeManageService extends BaseService<TeventType>{
	
	List<TeventType> selectMenuByUser(Tadmin admin);
	
	int insertSelective(TeventType record);
	
	int selectCountByName(String typeName);
	
	int selectCountByTypeMark(String typeMark);
	
	List<TeventType> selectAllList();

	int countByParent(String id);

	List<TeventType> selectByRole(String roleId);


}
