package com.icloud.service.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.event.EventInfoMapper;
import com.icloud.model.event.EventInfo;
import com.icloud.service.event.EventInfoService;

@Service
public class EventInfoServiceImpl implements EventInfoService {
	@Autowired
	private EventInfoMapper eventInfoMapper;
	@Override
	public void save(EventInfo t) throws Exception {
		eventInfoMapper.save(t);
	}

	@Override
	public void update(EventInfo t) throws Exception {
        eventInfoMapper.update(t);
	}

	@Override
	public PageInfo<EventInfo> findByPage(int pageNo, int pageSize, EventInfo t) throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		List<EventInfo> list = eventInfoMapper.findForList(t);
		PageInfo<EventInfo> page = new PageInfo<EventInfo>(list);
		return page;
	}

	@Override
	public EventInfo findByKey(String id) throws Exception {
		return eventInfoMapper.findForObject(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		eventInfoMapper.deleteByKey(id);

	}

	@Override
	public int findCount(EventInfo t) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<EventInfo> findListByRole(int pageNo,int pageSize,EventInfo ev) {
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<EventInfo>(eventInfoMapper.findListByRole(ev));
	}

	@Override
	public PageInfo<EventInfo> findUserFollowList(int pageNo, int pageSize, EventInfo ev) {
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<EventInfo>(eventInfoMapper.findUserFollowList(ev));
	}

	@Override
	public PageInfo<EventInfo> findMyList(int pageNo, int pageSize, EventInfo ev) {
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<EventInfo>(eventInfoMapper.findMyList(ev));
	}

}
