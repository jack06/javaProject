package com.icloud.service.user;

import com.icloud.model.user.UserExt;

public interface UserExtService {
	
	void deleteByKey(String id);
	void save(UserExt userExt);
	void update(UserExt userExt);
	UserExt findForObject(String id);
	UserExt findByUser(String userId);
	UserExt findByphone(String phone);
	UserExt findByEmail(String email);
}
