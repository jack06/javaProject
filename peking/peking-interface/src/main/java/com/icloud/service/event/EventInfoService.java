package com.icloud.service.event;

import com.github.pagehelper.PageInfo;
import com.icloud.model.event.EventInfo;
import com.icloud.service.BaseService;

public interface EventInfoService extends BaseService<EventInfo> {
	
	
	PageInfo<EventInfo> findListByRole(int pageNo,int pageSize,EventInfo ev);
	PageInfo<EventInfo> findUserFollowList(int pageNo,int pageSize,EventInfo ev);
	PageInfo<EventInfo> findMyList(int pageNo,int pageSize,EventInfo ev);
	PageInfo<EventInfo> findFriendEvents(int pageNo,int pageSize,EventInfo ev);
    PageInfo<EventInfo> findForAnnouncementList(int pageNo,int pageSize,EventInfo ev);
}
