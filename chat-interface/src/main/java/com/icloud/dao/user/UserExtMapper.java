package com.icloud.dao.user;

import com.icloud.model.user.UserExt;

public interface UserExtMapper {
	
	void deleteByKey(String id);
	void save(UserExt userExt);
	void update(UserExt userExt);
	UserExt findForObject(String id);
	UserExt findByUser(String userId);
	UserExt findByphone(String phone);
	UserExt findByEmail(String email);
}
