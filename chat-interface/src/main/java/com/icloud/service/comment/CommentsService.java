package com.icloud.service.comment;

import com.github.pagehelper.PageInfo;
import com.icloud.model.comments.Comments;

public interface CommentsService {
	void save(Comments comment);
	PageInfo<Comments> findForList(int pageNo,int pageSize,Comments comments);
}
