package com.icloud.service.comment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.comment.CommentConfigMapper;
import com.icloud.model.comments.CommentConfig;
import com.icloud.service.comment.CommentConfigService;

@Service
public class CommentConfigServiceImpl implements CommentConfigService {

	@Autowired
	private CommentConfigMapper commentConfigMapper;
	
	@Override
	public void save(CommentConfig t) throws Exception {
		commentConfigMapper.save(t);
	}

	@Override
	public void update(CommentConfig t) throws Exception {
		if(null==findByKey(t.getId())){
			commentConfigMapper.save(t);
		}else{
  		   commentConfigMapper.update(t);
		}  
  		  
	}

	@Override
	public PageInfo<CommentConfig> findByPage(int pageNo, int pageSize, CommentConfig t) throws Exception {
		return null;
	}

	@Override
	public CommentConfig findByKey(String id) throws Exception {
		return commentConfigMapper.findForObject(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		commentConfigMapper.deleteByKey(id);
	}

	@Override
	public int findCount(CommentConfig t) throws Exception {
		return commentConfigMapper.findCount(t);
	}

}
