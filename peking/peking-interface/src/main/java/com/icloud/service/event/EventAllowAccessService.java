package com.icloud.service.event;

import java.util.List;

import com.icloud.model.event.EventAllowAccess;

public interface EventAllowAccessService {
	void bathSave(List<EventAllowAccess> list);
	void deleteByEvent(String eventId);
	List<EventAllowAccess> findListByEvent(String eventId);
}
