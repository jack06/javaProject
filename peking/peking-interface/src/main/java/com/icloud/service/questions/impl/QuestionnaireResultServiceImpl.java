package com.icloud.service.questions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.questions.QuestionnaireResultMapper;
import com.icloud.model.questions.QuestionnaireResult;
import com.icloud.service.questions.QuestionnaireResultService;

@Service
public class QuestionnaireResultServiceImpl implements QuestionnaireResultService {
     
	@Autowired
	private QuestionnaireResultMapper questionnaireResultMapper;

	@Override
	public void save(QuestionnaireResult t) throws Exception {
		questionnaireResultMapper.insert(t);
	}

	@Override
	public void update(QuestionnaireResult t) throws Exception {
		questionnaireResultMapper.updateByPrimaryKey(t);
	}

	@Override
	public PageInfo<QuestionnaireResult> findByPage(int pageNo, int pageSize, QuestionnaireResult t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionnaireResult findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return questionnaireResultMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		questionnaireResultMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(QuestionnaireResult t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<QuestionnaireResult> getList(QuestionnaireResult questionnaireResult) {
		QueryBuilder example=new QueryBuilder();
		example.createCriteria();
		if(StringUtil.checkObj(questionnaireResult.getId())){
			
		}
		return questionnaireResultMapper.selectByExample(example);
	}



}
