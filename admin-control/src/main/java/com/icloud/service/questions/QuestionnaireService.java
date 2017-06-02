package com.icloud.service.questions;

import java.util.List;

import com.icloud.model.questions.Questionnaire;

public interface QuestionnaireService {
	List<Questionnaire> findForList(String eventId);
}
