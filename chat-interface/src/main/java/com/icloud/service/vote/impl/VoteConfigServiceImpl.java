package com.icloud.service.vote.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.vote.VoteConfigMapper;
import com.icloud.model.vote.VoteConfig;
import com.icloud.service.vote.VoteConfigService;

@Service
public class VoteConfigServiceImpl implements VoteConfigService {
     
	@Autowired
	private VoteConfigMapper voteConfigMapper;

	@Override
	public void save(VoteConfig t) throws Exception {
		voteConfigMapper.insert(t);
	}

	@Override
	public void update(VoteConfig t) throws Exception {
		// TODO Auto-generated method stub
		voteConfigMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public PageInfo<VoteConfig> findByPage(int pageNo, int pageSize, VoteConfig t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoteConfig findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return voteConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		voteConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(VoteConfig t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


}
