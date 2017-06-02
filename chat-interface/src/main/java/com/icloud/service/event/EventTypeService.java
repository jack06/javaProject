package com.icloud.service.event;

import java.util.List;

import com.icloud.model.event.EventType;

public interface EventTypeService {
	List<EventType> findAll();
}
