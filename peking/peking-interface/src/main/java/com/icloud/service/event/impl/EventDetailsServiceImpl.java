package com.icloud.service.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.event.EventDetailsMapper;
import com.icloud.model.event.EventDetails;
import com.icloud.service.event.EventDetailsService;

@Service
public class EventDetailsServiceImpl implements EventDetailsService {

	@Autowired
	private EventDetailsMapper eventDetailsMapper;
	
	@Override
	public void save(EventDetails eventDetails) {
		eventDetailsMapper.save(eventDetails);
	}

	@Override
	public void deleteByKey(String id) {
		eventDetailsMapper.deleteByKey(id);

	}

	@Override
	public void deleteByEvent(String eventId) {
        eventDetailsMapper.deleteByEvent(eventId);
	}

	@Override
	public EventDetails findForObject(String id) {
		return eventDetailsMapper.findForObject(id);
	}

	@Override
	public List<EventDetails> findForList(EventDetails eventDetails) {
		return eventDetailsMapper.findForList(eventDetails);
	}

}
