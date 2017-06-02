package com.icloud.service.event;

import java.util.List;

import com.icloud.model.event.EventDetails;

public interface EventDetailsService {
    void save(EventDetails eventDetails);
	
	void deleteByKey(String id);
	
	void deleteByEvent(String eventId);
	
	EventDetails findForObject(String id);
	
	List<EventDetails> findForList(EventDetails eventDetails);
}
