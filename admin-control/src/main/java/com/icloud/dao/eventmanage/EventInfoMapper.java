package com.icloud.dao.eventmanage;

import java.util.List;

import com.icloud.dao.DAO;
import com.icloud.model.eventmanage.EventInfo;

public interface EventInfoMapper extends DAO<EventInfo>{
	List<EventInfo> findListByRole(EventInfo ev);
	List<EventInfo> findUserFollowList(EventInfo ev);
}