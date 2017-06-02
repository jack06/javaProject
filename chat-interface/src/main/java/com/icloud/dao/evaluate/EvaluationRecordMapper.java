package com.icloud.dao.evaluate;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.evaluate.EvaluationRecord;

public interface EvaluationRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(EvaluationRecord record);

    int insertSelective(EvaluationRecord record);

    EvaluationRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EvaluationRecord record);

    int updateByPrimaryKey(EvaluationRecord record);
    
    long countByExample(QueryBuilder example);

    List<EvaluationRecord> selectByExample(QueryBuilder example);
}