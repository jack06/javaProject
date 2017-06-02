package com.icloud.dao.event;

import java.util.List;
import com.icloud.model.event.EventType;
public interface EventTypeMapper{
	List<EventType> findAll();
}