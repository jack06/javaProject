package com.icloud.dao.eventmanage;

import java.util.List;

import com.icloud.model.eventmanage.EventStarRecord;

public interface EventStarRecordMapper{

   
  List<EventStarRecord> findList(EventStarRecord eventStarRecord);
  
  
    
}