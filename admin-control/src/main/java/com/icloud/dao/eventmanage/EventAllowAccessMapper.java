package com.icloud.dao.eventmanage;

import java.util.List;

import com.icloud.model.eventmanage.EventAllowAccess;

public interface EventAllowAccessMapper {
	
	List<EventAllowAccess> findListByEvent(String eventId);

}
