package com.icloud.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.user.UserExtMapper;
import com.icloud.model.user.UserExt;
import com.icloud.service.user.UserExtService;

@Service
public class UserExtServiceImpl implements UserExtService {

	@Autowired
	private UserExtMapper userExtMapper;
	@Override
	public void deleteByKey(String id) {
		userExtMapper.deleteByKey(id);
	}

	@Override
	public void save(UserExt userExt) {
		userExtMapper.save(userExt);
	}

	@Override
	public void update(UserExt userExt) {
		userExtMapper.update(userExt);

	}

	@Override
	public UserExt findForObject(String id) {
		return userExtMapper.findForObject(id);
	}

	@Override
	public UserExt findByUser(String userId) {
		return userExtMapper.findByUser(userId);
	}

	@Override
	public UserExt findByphone(String phone) {
		return userExtMapper.findByphone(phone);
	}

	@Override
	public UserExt findByEmail(String email) {
		return userExtMapper.findByEmail(email);
	}

}
