package com.icloud.service.vote.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.vote.VoteConfigMapper;
import com.icloud.model.vote.VoteConfig;
import com.icloud.service.vote.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private  VoteConfigMapper  voteConfigMapper;
	@Override
	public List<VoteConfig> selectByEvent(String eventId) {
		return voteConfigMapper.selectByEvent(eventId);
	}

}
