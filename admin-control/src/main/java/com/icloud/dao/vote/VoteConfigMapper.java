package com.icloud.dao.vote;

import java.util.List;

import com.icloud.model.vote.VoteConfig;

public interface VoteConfigMapper {
	List<VoteConfig> selectByEvent(String eventId);
    
   
}