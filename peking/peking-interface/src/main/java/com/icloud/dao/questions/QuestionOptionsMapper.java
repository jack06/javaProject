package com.icloud.dao.questions;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.questions.QuestionOptions;

public interface QuestionOptionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(QuestionOptions record);

    int insertSelective(QuestionOptions record);

    QuestionOptions selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QuestionOptions record);

    int updateByPrimaryKey(QuestionOptions record);
    
    long countByExample(QueryBuilder example);

    List<QuestionOptions> selectByExample(QueryBuilder example);
    void deleteByModule(String questionnaireId);
    
    List<QuestionOptions> selectByQuestion(String questionId);
}