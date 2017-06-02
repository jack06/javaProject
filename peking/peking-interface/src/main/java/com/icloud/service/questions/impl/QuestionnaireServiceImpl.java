package com.icloud.service.questions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.questions.QuestionnaireMapper;
import com.icloud.model.questions.Questionnaire;
import com.icloud.service.questions.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
     
	@Autowired
	private QuestionnaireMapper questionnaireMapper;

	@Override
	public void save(Questionnaire t) throws Exception {
		questionnaireMapper.insert(t);
	}

	@Override
	public void update(Questionnaire t) throws Exception {
		questionnaireMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public PageInfo<Questionnaire> findByPage(int pageNo, int pageSize, Questionnaire t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Questionnaire findByKey(String id) throws Exception {
		return questionnaireMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		questionnaireMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(Questionnaire t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Questionnaire> getList(Questionnaire questionnaire) {
		QueryBuilder example=new QueryBuilder();
		example.createCriteria();
		if(StringUtil.checkObj(questionnaire.getDescription())){
			
		}
		return questionnaireMapper.selectByExample(example);
	}

	
}
