package com.icloud.dao.event;

import java.util.List;

import com.icloud.dao.DAO;
import com.icloud.model.event.EventInfo;

public interface EventInfoMapper extends DAO<EventInfo>{
	/**根据角色权限查找可查看事件 **/
	List<EventInfo> findListByRole(EventInfo ev);
	/**我的关注 **/
	List<EventInfo> findUserFollowList(EventInfo ev);
	/**我的发布**/
	List<EventInfo> findMyList(EventInfo ev);
	/** 查找朋友事件**/
	List<EventInfo> findFriendEvents(EventInfo ev);
	/**查找公告 **/
	List<EventInfo> findForAnnouncementList(EventInfo ev);
}