package com.icloud.dao.event;

import java.util.List;

import com.icloud.model.event.EventModule;

public interface EventModuleMapper{
	void save(EventModule eventModule);
	void delete(EventModule eventModule);
	List<EventModule> findForList(String eventId);
	int findForCount(String eventId);

}
