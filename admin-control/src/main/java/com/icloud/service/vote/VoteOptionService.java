package com.icloud.service.vote;

import java.util.List;

import com.icloud.model.vote.VoteOption;

public interface VoteOptionService {
	List<VoteOption> selectByModule(String moduleId);
}
