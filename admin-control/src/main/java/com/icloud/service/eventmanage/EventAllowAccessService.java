package com.icloud.service.eventmanage;

import java.util.List;

import com.icloud.model.eventmanage.EventAllowAccess;

public interface EventAllowAccessService {
	List<EventAllowAccess> findListByEvent(String eventId);
}
