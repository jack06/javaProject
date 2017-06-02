package com.icloud.dao.questions;

import com.icloud.model.questions.Questions;

public interface QuestionsMapper {
    Questions findByKey(String id);
}