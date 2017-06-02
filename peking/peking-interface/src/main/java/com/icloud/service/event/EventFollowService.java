package com.icloud.service.event;

import com.icloud.model.event.EventFollow;
import com.icloud.service.BaseService;

public interface EventFollowService extends BaseService<EventFollow> {
	void deleteByObject(EventFollow follow);
}
