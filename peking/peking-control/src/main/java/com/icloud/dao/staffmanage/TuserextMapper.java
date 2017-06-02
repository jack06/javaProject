package com.icloud.dao.staffmanage;

import java.util.List;

import com.icloud.dao.DAO;
import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuserext;

public interface TuserextMapper extends DAO<Tuserext>{


    int insertSelective(Tuserext record);
    
    List<Tuserext> selectMenuByUser(Tadmin admin);

    List<Tuserext> selectParentMenu();
    
    int selectCountByName(String realName);
    
    List<Tuserext> selectAllList();
    
    int countByParent(String id);
  
    List<Tuserext> selectByRole(String roleId);
    
}