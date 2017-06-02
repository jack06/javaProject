package com.icloud.service.evaluate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.evaluate.EvaluationRecordMapper;
import com.icloud.model.evaluate.EvaluationRecord;
import com.icloud.service.evaluate.EvaluationRecordService;

/**
 * @filename      : EvaluationRecordServiceImpl.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年4月27日 上午10:15:40   
 * @copyright     : zhumeng.com@chat-interface
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
public class EvaluationRecordServiceImpl implements EvaluationRecordService{

	@Autowired
	private EvaluationRecordMapper evaluationRecordMapper;

	@Override
	public void save(EvaluationRecord t) throws Exception {
		// TODO Auto-generated method stub
		evaluationRecordMapper.insert(t);
	}

	@Override
	public void update(EvaluationRecord t) throws Exception {
		// TODO Auto-generated method stub
		evaluationRecordMapper.updateByPrimaryKey(t);
	}

	@Override
	public PageInfo<EvaluationRecord> findByPage(int pageNo, int pageSize, EvaluationRecord t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EvaluationRecord findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return evaluationRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		evaluationRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(EvaluationRecord t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EvaluationRecord> getList(EvaluationRecord evaluationRecord) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(evaluationRecord.getEventId())){
			criteria.andFieldEqualTo("event_id",evaluationRecord.getEventId());
		}
		if(StringUtil.checkObj(evaluationRecord.getModuleId())){
			criteria.andFieldEqualTo("module_id",evaluationRecord.getModuleId());
		}
		return evaluationRecordMapper.selectByExample(example);
	}
	
	
}
