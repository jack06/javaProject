package com.icloud.service.user;

import java.util.List;

import com.icloud.model.user.UserAssociated;

public interface UserAssociatedService {
	void deleteByUser(UserAssociated userAssociated);
	void save(UserAssociated userAssociated);
	Integer checkIsFriend(UserAssociated userAssociated);
	List<UserAssociated> selectFriendList(String userId);
}
