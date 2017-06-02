package com.icloud.dao.event;

import java.util.List;

import com.icloud.model.event.EventDetails;

public interface EventDetailsMapper {

	void save(EventDetails eventDetails);
	
	void deleteByKey(String id);
	
	void deleteByEvent(String eventId);
	
	EventDetails findForObject(String id);
	
	List<EventDetails> findForList(EventDetails eventDetails);
}
