package com.icloud.service.questions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.questions.QuestionsMapper;
import com.icloud.model.questions.Questions;
import com.icloud.service.questions.QuestionsService;

@Service
public class QuestionsServiceImpl implements QuestionsService {
     
	@Autowired
	private QuestionsMapper questionsMapper;

	@Override
	public void save(Questions t) throws Exception {
		questionsMapper.insert(t);
	}

	@Override
	public void update(Questions t) throws Exception {
		questionsMapper.updateByPrimaryKey(t);
	}

	@Override
	public PageInfo<Questions> findByPage(int pageNo, int pageSize, Questions t) throws Exception {
		return null;
	}

	@Override
	public Questions findByKey(String id) throws Exception {
		return questionsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		questionsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(Questions t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Questions> getList(Questions questions) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(questions.getQuestionnaireId())){
			criteria.andFieldEqualTo("questionnaire_id", questions.getQuestionnaireId());
		}
		example.setOrderByClause("question_no asc");
		return questionsMapper.selectByExample(example);
	}

	@Override
	public int selectMaxNo(String questionnaireId) {
		Integer maxNo = questionsMapper.selectMaxNo(questionnaireId);
		return null==maxNo?0:maxNo;
	}

	@Override
	public void deleteByModule(String questionnaireId) {
       questionsMapper.deleteByModule(questionnaireId);		
	}

	
}
