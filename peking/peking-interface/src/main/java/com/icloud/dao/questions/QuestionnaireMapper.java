package com.icloud.dao.questions;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.questions.Questionnaire;

public interface QuestionnaireMapper {
    int deleteByPrimaryKey(String id);

    int insert(Questionnaire record);

    int insertSelective(Questionnaire record);

    Questionnaire selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Questionnaire record);

    int updateByPrimaryKey(Questionnaire record);
    
    long countByExample(QueryBuilder example);

    List<Questionnaire> selectByExample(QueryBuilder example);
}