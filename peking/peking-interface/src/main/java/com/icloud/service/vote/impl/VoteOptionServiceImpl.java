package com.icloud.service.vote.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.vote.VoteOptionMapper;
import com.icloud.model.vote.VoteOption;
import com.icloud.service.vote.VoteOptionService;

@Service
public class VoteOptionServiceImpl implements VoteOptionService {
     
	@Autowired
	private VoteOptionMapper voteOptionMapper;

	@Override
	public void save(VoteOption t) throws Exception {
		voteOptionMapper.insert(t);
	}

	@Override
	public void update(VoteOption t) throws Exception {
		voteOptionMapper.updateByPrimaryKey(t);
	}

	@Override
	public PageInfo<VoteOption> findByPage(int pageNo, int pageSize, VoteOption t) throws Exception {
		return null;
	}

	@Override
	public VoteOption findByKey(String id) throws Exception {
		return voteOptionMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		voteOptionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(VoteOption t) throws Exception {
		return 0;
	}

	@Override
	public List<VoteOption> getList(VoteOption voteOption) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(voteOption.getConfigId())){
			criteria.andFieldEqualTo("config_id", voteOption.getConfigId());
		}
		if(StringUtil.checkObj(voteOption.getEventId())){
			criteria.andFieldEqualTo("event_id", voteOption.getEventId());
		}
		return voteOptionMapper.selectByExample(example);
	}

	@Override
	public void deleteByModule(String moduleId) {
        voteOptionMapper.deleteByModule(moduleId);
	}

	
}
