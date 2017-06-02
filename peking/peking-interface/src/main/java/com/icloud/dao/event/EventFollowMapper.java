package com.icloud.dao.event;

import com.icloud.dao.DAO;
import com.icloud.model.event.EventFollow;

public interface EventFollowMapper extends DAO<EventFollow> {

  void deleteByObject(EventFollow follow);
    
}