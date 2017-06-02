package com.icloud.service.questions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.questions.AnswerMapper;
import com.icloud.model.questions.Answer;
import com.icloud.service.questions.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {
     
	@Autowired
	private AnswerMapper answerMapper;

	@Override
	public void save(Answer t) throws Exception {
		answerMapper.insert(t);
	}

	@Override
	public void update(Answer t) throws Exception {
		answerMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public PageInfo<Answer> findByPage(int pageNo, int pageSize, Answer t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return answerMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		answerMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public int findCount(Answer t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Answer> getList(Answer answer) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(answer.getQuestioonnaireId())){
			criteria.andFieldEqualTo("questioonnaire_id", answer.getQuestioonnaireId());
		}
		if(StringUtil.checkObj(answer.getQuestionId())){
			criteria.andFieldEqualTo("question_id", answer.getQuestionId());
		}
		if(StringUtil.checkObj(answer.getOptionsId())){
			criteria.andFieldEqualTo("options_id", answer.getOptionsId());
		}
		if(StringUtil.checkObj(answer.getUserId())){
			criteria.andFieldEqualTo("user_id", answer.getUserId());
		}
		return answerMapper.selectByExample(example);
	}
	

}
