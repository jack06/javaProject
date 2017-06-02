package com.icloud.service.vote.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.vote.VoteResultMapper;
import com.icloud.model.vote.VoteResult;
import com.icloud.service.vote.VoteResultService;

@Service
public class VoteResultServiceImpl implements VoteResultService {
     
	@Autowired
	private VoteResultMapper voteResultMapper;

	@Override
	public void save(VoteResult t) throws Exception {
		// TODO Auto-generated method stub
		voteResultMapper.insert(t);
	}

	@Override
	public void update(VoteResult t) throws Exception {
		// TODO Auto-generated method stub
		voteResultMapper.updateByPrimaryKey(t);
	}

	@Override
	public PageInfo<VoteResult> findByPage(int pageNo, int pageSize, VoteResult t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoteResult findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return voteResultMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		voteResultMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int findCount(VoteResult t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
