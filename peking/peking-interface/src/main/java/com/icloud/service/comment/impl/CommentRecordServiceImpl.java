package com.icloud.service.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.comment.CommentRecordMapper;
import com.icloud.model.comments.CommentRecord;
import com.icloud.service.comment.CommentRecordService;

@Service
public class CommentRecordServiceImpl implements CommentRecordService {
	
	@Autowired
	private CommentRecordMapper commentRecordMapper;

	@Override
	public List<CommentRecord> selectByCommentId(String commentId) {
		// TODO Auto-generated method stub
		return commentRecordMapper.selectByCommentId(commentId);
	}

	@Override
	public void save(CommentRecord c) {
		commentRecordMapper.save(c);
		
	}

}
