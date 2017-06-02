package com.icloud.service.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.event.EventDetailsConfigMapper;
import com.icloud.model.event.EventDetailsConfig;
import com.icloud.service.event.EventDetailsConfigService;

@Service
public class EventDetailsConfigServiceImpl implements EventDetailsConfigService {
 
	@Autowired
	private EventDetailsConfigMapper mapper;
	
	@Override
	public void save(EventDetailsConfig config) {
		mapper.save(config);
	}

	@Override
	public void deleteByKey(String id) {
		mapper.deleteByKey(id);
	}

	@Override
	public void deleteByEvent(String eventId) {
		mapper.deleteByEvent(eventId);

	}

	@Override
	public EventDetailsConfig findForObject(String id) {
		return mapper.findForObject(id);
	}

	@Override
	public List<EventDetailsConfig> findForList(EventDetailsConfig config) {
		return mapper.findForList(config);
	}

	@Override
	public void deleteByObj(EventDetailsConfig config) {
		mapper.deleteByObj(config);
	}

}
