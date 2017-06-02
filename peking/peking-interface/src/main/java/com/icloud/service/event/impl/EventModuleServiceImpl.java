package com.icloud.service.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.event.EventModuleMapper;
import com.icloud.model.event.EventModule;
import com.icloud.service.event.EventModuleService;

@Service
public class EventModuleServiceImpl implements EventModuleService {

	@Autowired
	private EventModuleMapper eventModuleMapper;
	
	@Override
	public void save(EventModule eventModule) {
		eventModuleMapper.save(eventModule);
	}

	@Override
	public void delete(EventModule eventModule) {
         eventModuleMapper.delete(eventModule);
	}

	@Override
	public List<EventModule> findForList(String eventId) {
		return eventModuleMapper.findForList(eventId);
	}

	@Override
	public int findForCount(String eventId) {
		return eventModuleMapper.findForCount(eventId);
	}

}
