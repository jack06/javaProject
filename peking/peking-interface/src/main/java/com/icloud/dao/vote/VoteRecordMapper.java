package com.icloud.dao.vote;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.vote.VoteRecord;

public interface VoteRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(VoteRecord record);

    int insertSelective(VoteRecord record);

    VoteRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VoteRecord record);

    int updateByPrimaryKey(VoteRecord record);
    
    long countByExample(QueryBuilder example);

    List<VoteRecord> selectByExample(QueryBuilder example);
}