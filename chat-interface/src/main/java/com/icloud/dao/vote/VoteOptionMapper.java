package com.icloud.dao.vote;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.vote.VoteOption;

public interface VoteOptionMapper {
    int deleteByPrimaryKey(String id);

    int insert(VoteOption record);

    int insertSelective(VoteOption record);

    VoteOption selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VoteOption record);

    int updateByPrimaryKey(VoteOption record);
    
    long countByExample(QueryBuilder example);

    List<VoteOption> selectByExample(QueryBuilder example);
    void deleteByModule(String moduleId);
}