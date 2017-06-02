package com.icloud.service.vote.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.vote.VoteRecordMapper;
import com.icloud.model.vote.VoteRecord;
import com.icloud.service.vote.VoteRecordService;

@Service
public class VoteRecordServiceImpl implements VoteRecordService {
     
	@Autowired
	private VoteRecordMapper voteRecordMapper;

	@Override
	public void save(VoteRecord t) throws Exception {
		// TODO Auto-generated method stub
		voteRecordMapper.insert(t);
	}

	@Override
	public void update(VoteRecord t) throws Exception {
		// TODO Auto-generated method stub
		voteRecordMapper.updateByPrimaryKey(t);
	}

	@Override
	public PageInfo<VoteRecord> findByPage(int pageNo, int pageSize, VoteRecord t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoteRecord findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return voteRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		voteRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(VoteRecord t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<VoteRecord> getList(VoteRecord voteRecord) {
		
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(voteRecord.getVoteUser())){
			criteria.andFieldEqualTo("vote_user", voteRecord.getVoteUser());
		}
		if(StringUtil.checkObj(voteRecord.getModuleId())){
			criteria.andFieldEqualTo("module_id", voteRecord.getModuleId());
		}
		if(StringUtil.checkObj(voteRecord.getEventId())){
			criteria.andFieldEqualTo("event_id", voteRecord.getEventId());
		}
		return voteRecordMapper.selectByExample(example);
	}
	
}
