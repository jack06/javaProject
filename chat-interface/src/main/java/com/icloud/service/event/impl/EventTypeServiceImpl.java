package com.icloud.service.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.event.EventTypeMapper;
import com.icloud.model.event.EventType;
import com.icloud.service.event.EventTypeService;

@Service
public class EventTypeServiceImpl implements EventTypeService {

	@Autowired
	private EventTypeMapper eventTypeMapper;
	@Override
	public List<EventType> findAll() {
		// TODO Auto-generated method stub
		return eventTypeMapper.findAll();
	}

}
