package com.icloud.dao.event;

import java.util.List;

import com.icloud.model.event.EventAllowAccess;

public interface EventAllowAccessMapper {
	void bathSave(List<EventAllowAccess> list);
	void deleteByEvent(String eventId);
	List<EventAllowAccess> findListByEvent(String eventId);

}
