package com.icloud.service.event;

import java.util.List;

import com.icloud.model.event.EventModule;

public interface EventModuleService {
	void save(EventModule eventModule);
	void delete(EventModule eventModule);
	List<EventModule> findForList(String eventId);
	int findForCount(String eventId);
}
