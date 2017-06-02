package com.icloud.service.eventmanage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.eventmanage.EventInfoMapper;
import com.icloud.model.eventmanage.EventInfo;
import com.icloud.service.eventmanage.EventInfoService;

@Service
public class EventInfoServiceImpl implements EventInfoService {

	@Autowired
	private EventInfoMapper eventInfoMapper;
	
	@Override
	public void save(EventInfo t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(EventInfo t) throws Exception {
          eventInfoMapper.update(t);
	}

	@Override
	public List<EventInfo> findList(EventInfo t) throws Exception {
		return null;
	}

	@Override
	public Integer findCount(EventInfo t) throws Exception {
		return null;
	}

	@Override
	public void delete(String id) throws Exception {

	}

	@Override
	public EventInfo findByKey(String id) throws Exception {
		return eventInfoMapper.findForObject(id);
	}

	@Override
	public PageInfo<EventInfo> findByPage(PageInfo<EventInfo> page, EventInfo t)
			throws Exception {
		return null;
	}
	

	public PageInfo<EventInfo> findByPage(int pageNo,int pageSize,EventInfo t)
			throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<EventInfo> list = eventInfoMapper.findForList(t);
		return new PageInfo<EventInfo>(list);
	}
	

}
