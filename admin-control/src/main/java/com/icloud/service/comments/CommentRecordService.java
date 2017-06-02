package com.icloud.service.comments;

import java.util.List;

import com.icloud.model.comments.CommentRecord;

public interface CommentRecordService {
	List<CommentRecord> selectByCommentId(String commentId);
}
