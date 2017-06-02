package com.icloud.dao.questions;

import java.util.List;

import com.icloud.model.questions.Questionnaire;

public interface QuestionnaireMapper {
	List<Questionnaire> findForList(String eventId);
}