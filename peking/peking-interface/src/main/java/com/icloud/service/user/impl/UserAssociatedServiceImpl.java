package com.icloud.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.user.UserAssociatedMapper;
import com.icloud.model.user.UserAssociated;
import com.icloud.service.user.UserAssociatedService;

@Service
public class UserAssociatedServiceImpl implements UserAssociatedService {

	@Autowired
	private UserAssociatedMapper userAssociatedMapper;
	@Override
	public void deleteByUser(UserAssociated userAssociated) {
		userAssociatedMapper.deleteByUser(userAssociated);
	}

	@Override
	public void save(UserAssociated userAssociated) {
		userAssociatedMapper.save(userAssociated);
	}

	@Override
	public Integer checkIsFriend(UserAssociated userAssociated) {
		Integer count = userAssociatedMapper.checkIsFriend(userAssociated);
		return null==count?0:count;
	}

	@Override
	public List<UserAssociated> selectFriendList(String userId) {
		return userAssociatedMapper.selectFriendList(userId);
	}

}
