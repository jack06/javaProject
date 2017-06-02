package com.icloud.dao.vote;

import java.util.List;

import com.icloud.model.vote.VoteRecord;

public interface VoteRecordMapper {
	Integer selectOptionCount(VoteRecord record);
	List<VoteRecord> findList(String moduleId);
   
}