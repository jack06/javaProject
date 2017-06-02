package com.icloud.service.vote;

import java.util.List;

import com.icloud.model.vote.VoteRecord;
import com.icloud.service.BaseService;

public interface VoteRecordService extends BaseService<VoteRecord>{
	
	public List<VoteRecord> getList(VoteRecord voteRecord);

}
