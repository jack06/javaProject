package com.icloud.service.comments;

import com.github.pagehelper.PageInfo;
import com.icloud.model.comments.Comments;

public interface CommentsService {
	PageInfo<Comments> findForList(int pageNo,int pageSize,Comments comments);
}
