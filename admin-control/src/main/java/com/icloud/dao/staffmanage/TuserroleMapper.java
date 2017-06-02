package com.icloud.dao.staffmanage;

import java.util.List;

import com.icloud.dao.DAO;
import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuserrole;


public interface TuserroleMapper extends DAO<Tuserrole>{

    int insertSelective(Tuserrole record);
    
    List<Tuserrole> selectMenuByUser(Tadmin admin);
 
    int selectCountByName(String roleName);
    
    List<Tuserrole> selectAllList();
    
    int countByParent(String id);
  
    List<Tuserrole> selectByRole(String roleId);
    
    List<Tuserrole> selectRoleList(String[] array);

}



