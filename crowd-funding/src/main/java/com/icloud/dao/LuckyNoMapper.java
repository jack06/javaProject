package com.icloud.dao;


import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.LuckyNo;

public interface LuckyNoMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(LuckyNo record);

    int insertSelective(LuckyNo record);

    LuckyNo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LuckyNo record);

    int updateByPrimaryKey(LuckyNo record);
    
    List<LuckyNo> selectByExample(QueryBuilder example);
    
    Long selectMaxLuckyNo(Long raiseId);
    
    List<LuckyNo> selectByRaiseId(Long id);
}