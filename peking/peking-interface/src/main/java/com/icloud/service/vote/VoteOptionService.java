package com.icloud.service.vote;

import java.util.List;

import com.icloud.model.vote.VoteOption;
import com.icloud.service.BaseService;

public interface VoteOptionService extends BaseService<VoteOption>{

	public List<VoteOption> getList(VoteOption voteOption);
	void deleteByModule(String moduleId);
}
