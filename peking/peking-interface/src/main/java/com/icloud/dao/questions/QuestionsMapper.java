package com.icloud.dao.questions;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.questions.Questions;

public interface QuestionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Questions record);

    int insertSelective(Questions record);

    Questions selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Questions record);

    int updateByPrimaryKey(Questions record);
    
    
    long countByExample(QueryBuilder example);

    List<Questions> selectByExample(QueryBuilder example);
    
    Integer selectMaxNo(String questionnaireId);
    
    void deleteByModule(String questionnaireId);
}