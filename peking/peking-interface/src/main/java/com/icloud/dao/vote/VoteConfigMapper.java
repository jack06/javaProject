package com.icloud.dao.vote;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.vote.VoteConfig;

public interface VoteConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(VoteConfig record);

    int insertSelective(VoteConfig record);

    VoteConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VoteConfig record);

    int updateByPrimaryKey(VoteConfig record);
    
    long countByExample(QueryBuilder example);

    List<VoteConfig> selectByExample(QueryBuilder example);
    
   
}