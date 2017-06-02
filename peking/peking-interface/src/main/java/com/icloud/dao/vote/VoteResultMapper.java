package com.icloud.dao.vote;
import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.vote.VoteResult;

public interface VoteResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(VoteResult record);

    int insertSelective(VoteResult record);

    VoteResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VoteResult record);

    int updateByPrimaryKey(VoteResult record);
    
    long countByExample(QueryBuilder example);

    List<VoteResult> selectByExample(QueryBuilder example);
}