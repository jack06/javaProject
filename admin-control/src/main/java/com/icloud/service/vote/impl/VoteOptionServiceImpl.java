package com.icloud.service.vote.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.vote.VoteOptionMapper;
import com.icloud.model.vote.VoteOption;
import com.icloud.service.vote.VoteOptionService;

@Service
public class VoteOptionServiceImpl implements VoteOptionService {

	@Autowired
	private VoteOptionMapper voteOptionMapper;
	@Override
	public List<VoteOption> selectByModule(String moduleId) {
		return voteOptionMapper.selectByModule(moduleId);
	}

}
