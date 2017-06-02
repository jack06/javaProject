package com.icloud.service.comment;

import java.util.List;

import com.icloud.model.comments.CommentRecord;

public interface CommentRecordService {
	List<CommentRecord> selectByCommentId(String commentId);
	void save(CommentRecord c);
}
