package com.icloud.service.questions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.questions.AnswerMapper;
import com.icloud.model.questions.Answer;
import com.icloud.model.questions.AnswerRecord;
import com.icloud.service.questions.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerMapper answerMapper;
	@Override
	public PageInfo<AnswerRecord> findAnswerRecord(int pageNo,int pageSize,String questionnaireId) {
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<AnswerRecord>(answerMapper.findAnswerRecord(questionnaireId));
	}
	@Override
	public List<Answer> selectAllByQuestionnaire(Answer answer) {
		return answerMapper.selectAllByQuestionnaire(answer);
	}

}
