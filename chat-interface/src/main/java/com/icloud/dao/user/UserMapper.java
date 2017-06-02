package com.icloud.dao.user;

import com.icloud.dao.DAO;
import com.icloud.model.user.User;

public interface UserMapper extends DAO<User>{
	
	User findByOpenId(String openId);
    
}