package com.icloud.dao.questions;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.questions.QuestionnaireResult;

public interface QuestionnaireResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(QuestionnaireResult record);

    int insertSelective(QuestionnaireResult record);

    QuestionnaireResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QuestionnaireResult record);

    int updateByPrimaryKey(QuestionnaireResult record);
    
    long countByExample(QueryBuilder example);

    List<QuestionnaireResult> selectByExample(QueryBuilder example);
}