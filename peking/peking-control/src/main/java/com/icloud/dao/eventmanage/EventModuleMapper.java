package com.icloud.dao.eventmanage;

import java.util.List;

import com.icloud.model.eventmanage.EventModule;

public interface EventModuleMapper{
	void save(EventModule eventModule);
	void delete(EventModule eventModule);
	List<EventModule> findForList(String eventId);
	int findForCount(String eventId);

}
