package com.icloud.dao.eventmanage;

import java.util.List;

import com.icloud.model.eventmanage.EventFollow;

public interface EventFollowMapper{

  List<EventFollow> findForList(EventFollow follow);
    
}