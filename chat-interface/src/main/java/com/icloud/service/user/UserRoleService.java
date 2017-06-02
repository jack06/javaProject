package com.icloud.service.user;

import java.util.List;

import com.icloud.model.user.UserRole;

public interface UserRoleService {
	List<UserRole> findAll();
	UserRole findByKey(String id);
}
