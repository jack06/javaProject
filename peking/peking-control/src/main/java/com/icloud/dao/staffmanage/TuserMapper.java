package com.icloud.dao.staffmanage;

import java.util.List;

import com.icloud.dao.DAO;
import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuser;

public interface TuserMapper extends DAO<Tuser>{


    int insertSelective(Tuser record);
    
    List<Tuser> selectMenuByUser(Tadmin admin);

    List<Tuser> selectParentMenu();
    
    int selectCountByName(String nick);
    
    List<Tuser> selectAllList();
    
    int countByParent(String id);
  
    List<Tuser> selectByRole(String roleId);
    
}