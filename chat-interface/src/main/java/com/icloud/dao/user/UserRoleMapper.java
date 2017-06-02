package com.icloud.dao.user;

import java.util.List;

import com.icloud.model.user.UserRole;

public interface UserRoleMapper {
	List<UserRole> findAll();
	UserRole findByKey(String id);
}
