package com.icloud.dao.vote;

import java.util.List;

import com.icloud.model.vote.VoteOption;

public interface VoteOptionMapper {
	List<VoteOption> selectByModule(String moduleId);
	VoteOption findByKey(String id);
}