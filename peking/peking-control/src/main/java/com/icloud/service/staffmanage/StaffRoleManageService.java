package com.icloud.service.staffmanage;

import java.util.List;

import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuserrole;
import com.icloud.service.BaseService;

public interface StaffRoleManageService extends BaseService<Tuserrole>{
	
	List<Tuserrole> selectMenuByUser(Tadmin admin);
	
	int insertSelective(Tuserrole record);
	
	int selectCountByName(String roleName);
	
	List<Tuserrole> selectAllList();

	int countByParent(String id);

	List<Tuserrole> selectByRole(String roleId);

	 List<Tuserrole> selectRoleList(String[] list);
}
