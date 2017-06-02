package com.icloud.dao.questions;

import java.util.List;

import com.icloud.model.questions.Answer;
import com.icloud.model.questions.AnswerRecord;

public interface AnswerMapper {
	List<AnswerRecord> findAnswerRecord(String questionnaireId);
	List<Answer> selectAllByQuestionnaire(Answer answer);
}