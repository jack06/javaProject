package com.icloud.service.eventmanage;

import com.github.pagehelper.PageInfo;
import com.icloud.model.eventmanage.EventFollow;

public interface EventFollowService {
	PageInfo<EventFollow> findForList(int pageNo,int pageSize,EventFollow follow);
}
