package com.icloud.service.user;


import com.icloud.model.user.User;
import com.icloud.service.BaseService;

public interface UserService extends BaseService<User>{
	User findByOpenId(String openId);
	
	User getUserFromSession(String sessionKey);
	

}
