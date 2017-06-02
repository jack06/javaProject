package com.icloud.service.comments.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.comment.CommentConfigMapper;
import com.icloud.model.comments.CommentConfig;
import com.icloud.service.comments.CommentConfigService;

@Service
public class CommentConfigServiceImpl implements CommentConfigService {

	@Autowired
	private CommentConfigMapper commentConfigMapper;

	@Override
	public List<CommentConfig> selectByEvent(String eventId) {
		return commentConfigMapper.selectByEvent(eventId);
	}

	
	

}
