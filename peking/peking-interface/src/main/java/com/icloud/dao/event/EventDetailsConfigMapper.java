package com.icloud.dao.event;

import java.util.List;

import com.icloud.model.event.EventDetailsConfig;

public interface EventDetailsConfigMapper {
	void save(EventDetailsConfig config);
	void deleteByKey(String id);
	void deleteByEvent(String eventId);
	EventDetailsConfig findForObject(String id);
	List<EventDetailsConfig> findForList(EventDetailsConfig config);
	void deleteByObj(EventDetailsConfig config);
}
