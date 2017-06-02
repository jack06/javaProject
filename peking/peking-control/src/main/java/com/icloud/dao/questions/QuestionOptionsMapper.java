package com.icloud.dao.questions;

import java.util.List;

import com.icloud.model.questions.QuestionOptions;

public interface QuestionOptionsMapper {
	 List<QuestionOptions> selectByQuestion(String questionId);
}