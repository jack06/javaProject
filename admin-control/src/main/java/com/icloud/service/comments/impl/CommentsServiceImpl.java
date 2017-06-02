package com.icloud.service.comments.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.comment.CommentsMapper;
import com.icloud.model.comments.Comments;
import com.icloud.service.comments.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsMapper mapper;

	@Override
	public PageInfo<Comments> findForList(int pageNo, int pageSize, Comments comments) {
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<Comments>(mapper.findForList(comments));
	}

}
