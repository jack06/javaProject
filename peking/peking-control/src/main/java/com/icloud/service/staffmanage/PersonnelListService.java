package com.icloud.service.staffmanage;

import java.util.List;

import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuser;
import com.icloud.service.BaseService;

public interface PersonnelListService extends BaseService<Tuser> {

	List<Tuser> selectMenuByUser(Tadmin admin);
	
	List<Tuser> selectParentMenu();
	
	int insertSelective(Tuser record);
	
	int selectCountByName(String nick);
	
	List<Tuser> selectAllList();

	int countByParent(String id);

	 List<Tuser> selectByRole(String roleId);
}
