package com.icloud.dao.eventmanage;

import java.util.List;

import com.icloud.model.eventmanage.EventDetails;

public interface EventDetailsMapper {

	void save(EventDetails eventDetails);
	
	void deleteByKey(String id);
	
	void deleteByEvent(String eventId);
	
	EventDetails findForObject(String id);
	
	List<EventDetails> findForList(EventDetails eventDetails);
}
