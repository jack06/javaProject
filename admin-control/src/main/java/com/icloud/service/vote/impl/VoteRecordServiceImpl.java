package com.icloud.service.vote.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.vote.VoteRecordMapper;
import com.icloud.model.vote.VoteRecord;
import com.icloud.service.vote.VoteRecordService;

@Service
public class VoteRecordServiceImpl implements VoteRecordService {

	@Autowired
	private VoteRecordMapper voteRecordMapper;
	@Override
	public Integer selectOptionCount(VoteRecord record) {
		Integer count = voteRecordMapper.selectOptionCount(record);
		return null==count?0:count;
	}
	@Override
	public PageInfo<VoteRecord> findPage(int pageNo, int pageSize,
			String moduleId) {
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<>(voteRecordMapper.findList(moduleId));
	}

}
