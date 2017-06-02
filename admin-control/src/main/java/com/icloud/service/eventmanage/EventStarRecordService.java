package com.icloud.service.eventmanage;

import com.github.pagehelper.PageInfo;
import com.icloud.model.eventmanage.EventStarRecord;

public interface EventStarRecordService {
	PageInfo<EventStarRecord> findList(int pageNo,int pageSize,EventStarRecord eventStarRecord);
}
