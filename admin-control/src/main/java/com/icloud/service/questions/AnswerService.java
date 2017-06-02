package com.icloud.service.questions;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.icloud.model.questions.Answer;
import com.icloud.model.questions.AnswerRecord;

public interface AnswerService {
	PageInfo<AnswerRecord> findAnswerRecord(int pageNo,int pageSzie,String questionnaireId);
	List<Answer> selectAllByQuestionnaire(Answer answer); 
}
