package com.icloud.dao.evaluate;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.evaluate.EvaluationConfig;

public interface EvaluationConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(EvaluationConfig record);

    int insertSelective(EvaluationConfig record);

    EvaluationConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EvaluationConfig record);

    int updateByPrimaryKey(EvaluationConfig record);
    
    long countByExample(QueryBuilder example);

    List<EvaluationConfig> selectByExample(QueryBuilder example);
    
}