package com.icloud.service.questions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.questions.QuestionnaireMapper;
import com.icloud.model.questions.Questionnaire;
import com.icloud.service.questions.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
	@Autowired
	private QuestionnaireMapper questionnaireMapper;

	@Override
	public List<Questionnaire> findForList(String eventId) {
		return questionnaireMapper.findForList(eventId);
	}

}
