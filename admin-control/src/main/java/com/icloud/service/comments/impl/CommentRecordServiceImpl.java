package com.icloud.service.comments.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.comment.CommentRecordMapper;
import com.icloud.model.comments.CommentRecord;
import com.icloud.service.comments.CommentRecordService;

@Service
public class CommentRecordServiceImpl implements CommentRecordService {
	
	@Autowired
	private CommentRecordMapper commentRecordMapper;

	@Override
	public List<CommentRecord> selectByCommentId(String commentId) {
		// TODO Auto-generated method stub
		return commentRecordMapper.selectByCommentId(commentId);
	}


}
