package com.icloud.dao.comment;

import java.util.List;

import com.icloud.model.comments.CommentRecord;

public interface CommentRecordMapper{
	List<CommentRecord> selectByCommentId(String commentId);
	void save(CommentRecord c);
}
