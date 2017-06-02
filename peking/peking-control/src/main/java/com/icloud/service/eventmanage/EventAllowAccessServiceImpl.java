package com.icloud.service.eventmanage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.eventmanage.EventAllowAccessMapper;
import com.icloud.model.eventmanage.EventAllowAccess;

@Service
public class EventAllowAccessServiceImpl implements EventAllowAccessService {
    @Autowired
	private EventAllowAccessMapper eventAllowAccessMapper;
	@Override
	public List<EventAllowAccess> findListByEvent(String eventId) {
		return eventAllowAccessMapper.findListByEvent(eventId);
	}

}
