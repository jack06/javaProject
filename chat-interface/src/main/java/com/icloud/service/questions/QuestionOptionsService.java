package com.icloud.service.questions;

import java.util.List;

import com.icloud.model.questions.QuestionOptions;
import com.icloud.service.BaseService;

public interface QuestionOptionsService extends BaseService<QuestionOptions>{

	public List<QuestionOptions> getList(QuestionOptions questionOptions);
	
	void deleteByModule(String questionnaireId);
}
