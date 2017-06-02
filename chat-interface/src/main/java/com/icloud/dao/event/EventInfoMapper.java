package com.icloud.dao.event;

import java.util.List;

import com.icloud.dao.DAO;
import com.icloud.model.event.EventInfo;

public interface EventInfoMapper extends DAO<EventInfo>{
	List<EventInfo> findListByRole(EventInfo ev);
	List<EventInfo> findUserFollowList(EventInfo ev);
	List<EventInfo> findMyList(EventInfo ev);
}