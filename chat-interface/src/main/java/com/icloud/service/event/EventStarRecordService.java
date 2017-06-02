package com.icloud.service.event;

import com.github.pagehelper.PageInfo;
import com.icloud.model.event.EventStarRecord;

public interface EventStarRecordService {
	void save(EventStarRecord eventStarRecord);
	  
	  int findCountByEvent(String eventId);
	  
	  EventStarRecord selectByUser(EventStarRecord eventStarRecord);
	  
	  PageInfo<EventStarRecord> findList(int pageNo,int pageSize,EventStarRecord eventStarRecord);
	  
}
