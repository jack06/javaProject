package com.icloud.service.comments;

import java.util.List;

import com.icloud.model.comments.CommentConfig;

public interface CommentConfigService {
	List<CommentConfig> selectByEvent(String eventId);
}
