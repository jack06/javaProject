package com.icloud.service.questions;

import java.util.List;

import com.icloud.model.questions.Questionnaire;
import com.icloud.service.BaseService;

public interface QuestionnaireService extends BaseService<Questionnaire>{

	public List<Questionnaire> getList(Questionnaire questionnaire);
}
