package com.icloud.service.evaluate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.evaluate.EvaluationConfigMapper;
import com.icloud.model.evaluate.EvaluationConfig;
import com.icloud.service.evaluate.EvaluationConfigService;

/**
 * @filename      : EvaluationConfigServiceImpl.java
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
public class EvaluationConfigServiceImpl implements EvaluationConfigService{

	@Autowired
	private EvaluationConfigMapper evaluationConfigMapper;
	@Override
	public void save(EvaluationConfig t) throws Exception {
		evaluationConfigMapper.insert(t);
	}

	@Override
	public void update(EvaluationConfig t) throws Exception {
		 if(null==evaluationConfigMapper.selectByPrimaryKey(t.getId())){
			 evaluationConfigMapper.insert(t);
		 }else{
		   evaluationConfigMapper.updateByPrimaryKeySelective(t);
		 }
	}

	@Override
	public PageInfo<EvaluationConfig> findByPage(int pageNo, int pageSize, EvaluationConfig t) throws Exception {
		return null;
	}

	@Override
	public EvaluationConfig findByKey(String id) throws Exception {
		return evaluationConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		evaluationConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(EvaluationConfig t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EvaluationConfig> getList(EvaluationConfig evaluationConfig) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(evaluationConfig.getEventId())){
			criteria.andFieldEqualTo("event_id",evaluationConfig.getEventId());
		}
		return evaluationConfigMapper.selectByExample(example);
	}

	
}
