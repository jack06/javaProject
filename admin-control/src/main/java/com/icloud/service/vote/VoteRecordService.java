package com.icloud.service.vote;

import com.github.pagehelper.PageInfo;
import com.icloud.model.vote.VoteRecord;

public interface VoteRecordService {
	Integer selectOptionCount(VoteRecord record);
	PageInfo<VoteRecord> findPage(int pageNo,int pageSize,String moduleId);
}
