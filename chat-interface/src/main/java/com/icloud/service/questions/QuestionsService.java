package com.icloud.service.questions;

import java.util.List;

import com.icloud.model.questions.Questions;
import com.icloud.service.BaseService;

public interface QuestionsService extends BaseService<Questions>{

	public List<Questions> getList(Questions questions);
	int selectMaxNo(String questionnaireId);
	void deleteByModule(String questionnaireId);
}
