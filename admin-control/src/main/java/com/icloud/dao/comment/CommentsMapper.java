package com.icloud.dao.comment;

import java.util.List;

import com.icloud.model.comments.Comments;

public interface CommentsMapper {
	List<Comments> findForList(Comments comments);
}
