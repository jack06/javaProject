package com.icloud.service.questions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.questions.QuestionOptionsMapper;
import com.icloud.model.questions.QuestionOptions;
import com.icloud.service.questions.QuestionOptionsService;

@Service
public class QuestionOptionsServiceImpl implements QuestionOptionsService {
     
	@Autowired
	private QuestionOptionsMapper questionOptionsMapper;

	@Override
	public void save(QuestionOptions t) throws Exception {
		questionOptionsMapper.insert(t);
	}

	@Override
	public void update(QuestionOptions t) throws Exception {
		questionOptionsMapper.updateByPrimaryKey(t);
	}

	@Override
	public PageInfo<QuestionOptions> findByPage(int pageNo, int pageSize, QuestionOptions t) throws Exception {
		return null;
	}

	@Override
	public QuestionOptions findByKey(String id) throws Exception {
		return questionOptionsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		questionOptionsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(QuestionOptions t) throws Exception {
		return 0;
	}

	@Override
	public List<QuestionOptions> getList(QuestionOptions questionOptions) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(questionOptions.getQuestionnaireId())){
			criteria.andFieldEqualTo("questionnaire_id", questionOptions.getQuestionnaireId());
		}
		if(StringUtil.checkObj(questionOptions.getQuestionId())){
			criteria.andFieldEqualTo("question_id", questionOptions.getQuestionId());
		}
		return questionOptionsMapper.selectByExample(example);
	}

	@Override
	public void deleteByModule(String questionnaireId) {
		questionOptionsMapper.deleteByModule(questionnaireId);
	}

	

}
