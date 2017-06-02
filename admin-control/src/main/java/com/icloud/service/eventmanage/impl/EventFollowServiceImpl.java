package com.icloud.service.eventmanage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.eventmanage.EventFollowMapper;
import com.icloud.model.eventmanage.EventFollow;
import com.icloud.service.eventmanage.EventFollowService;

@Service
public class EventFollowServiceImpl implements EventFollowService {

	@Autowired
	private EventFollowMapper mapper;
	@Override
	public PageInfo<EventFollow> findForList(int pageNo,int pageSize,EventFollow follow) {
		
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<EventFollow>(mapper.findForList(follow));
	}

}
