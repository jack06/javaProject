package com.icloud.service.questions;

import java.util.List;

import com.icloud.model.questions.QuestionnaireResult;
import com.icloud.service.BaseService;

public interface QuestionnaireResultService extends BaseService<QuestionnaireResult>{

	public List<QuestionnaireResult> getList(QuestionnaireResult questionnaireResult);
}
