package com.icloud.service.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.event.EventAllowAccessMapper;
import com.icloud.model.event.EventAllowAccess;
import com.icloud.service.event.EventAllowAccessService;

@Service
public class EventAllowAccessServiceImpl implements EventAllowAccessService {

	@Autowired
	private EventAllowAccessMapper eventAllowAccessMapper;
	@Override
	public void bathSave(List<EventAllowAccess> list) {
		eventAllowAccessMapper.bathSave(list);
	}

	@Override
	public void deleteByEvent(String eventId) {
		eventAllowAccessMapper.deleteByEvent(eventId);
	}

	@Override
	public List<EventAllowAccess> findListByEvent(String eventId) {
		return eventAllowAccessMapper.findListByEvent(eventId);
	}

}
