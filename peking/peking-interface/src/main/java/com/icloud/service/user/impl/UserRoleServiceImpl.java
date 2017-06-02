package com.icloud.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.user.UserRoleMapper;
import com.icloud.model.user.UserRole;
import com.icloud.service.user.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	@Override
	public List<UserRole> findAll() {
		return userRoleMapper.findAll();
	}
	@Override
	public UserRole findByKey(String id) {
		return userRoleMapper.findByKey(id);
	}

}
