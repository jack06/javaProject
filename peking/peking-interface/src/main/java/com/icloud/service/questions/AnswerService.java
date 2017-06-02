package com.icloud.service.questions;

import java.util.List;

import com.icloud.model.questions.Answer;
import com.icloud.service.BaseService;

public interface AnswerService extends BaseService<Answer>{

	public List<Answer> getList(Answer answer);
}
