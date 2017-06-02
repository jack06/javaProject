package com.icloud.service.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.event.EventFollowMapper;
import com.icloud.model.event.EventFollow;
import com.icloud.service.event.EventFollowService;
@Service
public class EventFollowServiceImpl implements EventFollowService {
    
    @Autowired
	private EventFollowMapper eventFollowMapper;
	@Override
	public void save(EventFollow t) throws Exception {
		eventFollowMapper.save(t);
	}

	@Override
	public void update(EventFollow t) throws Exception {
         eventFollowMapper.update(t);
	}

	@Override
	public PageInfo<EventFollow> findByPage(int pageNo, int pageSize, EventFollow t) throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		List<EventFollow> list = eventFollowMapper.findForList(t);
		return new PageInfo<>(list);
	}

	@Override
	public EventFollow findByKey(String id) throws Exception {
		return eventFollowMapper.findForObject(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		eventFollowMapper.deleteByKey(id);
	}

	@Override
	public int findCount(EventFollow t) throws Exception {
		return eventFollowMapper.findCount(t);
	}

	@Override
	public void deleteByObject(EventFollow follow) {
		eventFollowMapper.deleteByObject(follow);
	}

}
