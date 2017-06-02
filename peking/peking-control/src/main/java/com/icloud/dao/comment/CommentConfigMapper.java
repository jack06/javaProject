package com.icloud.dao.comment;

import java.util.List;

import com.icloud.model.comments.CommentConfig;

public interface CommentConfigMapper{
	List<CommentConfig> selectByEvent(String eventId);

}
