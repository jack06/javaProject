package com.icloud.dao.event;

import java.util.List;
import com.icloud.model.event.EventStarRecord;

public interface EventStarRecordMapper{

  void save(EventStarRecord eventStarRecord);
  
  int findCountByEvent(String eventId);
  
  EventStarRecord selectByUser(EventStarRecord eventStarRecord);
  
  List<EventStarRecord> findList(EventStarRecord eventStarRecord);
  
  
    
}