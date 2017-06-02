package com.icloud.dao.questions;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.questions.Answer;

public interface AnswerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
    
    long countByExample(QueryBuilder example);

    List<Answer> selectByExample(QueryBuilder example);
}