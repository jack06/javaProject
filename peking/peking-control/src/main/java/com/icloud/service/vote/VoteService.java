package com.icloud.service.vote;

import java.util.List;

import com.icloud.model.vote.VoteConfig;

public interface VoteService {
	List<VoteConfig> selectByEvent(String eventId);
}
