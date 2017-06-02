package com.icloud.dao.eventmanage;

import java.util.List;

import com.icloud.dao.DAO;
import com.icloud.model.bms.Tadmin;
import com.icloud.model.eventmanage.TeventType;


public interface TeventTypeMapper extends DAO<TeventType>{

    int insertSelective(TeventType record);
    
    List<TeventType> selectMenuByUser(Tadmin admin);
 
    int selectCountByName(String typeName);
    
    int selectCountByTypeMark(String typeMark);
    
    List<TeventType> selectAllList();
    
    int countByParent(String id);
  
    List<TeventType> selectByRole(String roleId);

}



