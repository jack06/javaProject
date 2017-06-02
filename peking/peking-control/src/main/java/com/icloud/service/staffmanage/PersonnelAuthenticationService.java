package com.icloud.service.staffmanage;

import java.util.List;

import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuserext;
import com.icloud.service.BaseService;

public interface PersonnelAuthenticationService extends BaseService<Tuserext> {

	List<Tuserext> selectMenuByUser(Tadmin admin);
	
	List<Tuserext> selectParentMenu();
	
	int insertSelective(Tuserext record);
	
	int selectCountByName(String realName);
	
	List<Tuserext> selectAllList();

	int countByParent(String id);

	 List<Tuserext> selectByRole(String roleId);
}
