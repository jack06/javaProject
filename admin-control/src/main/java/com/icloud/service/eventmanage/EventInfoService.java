package com.icloud.service.eventmanage;

import com.github.pagehelper.PageInfo;
import com.icloud.model.eventmanage.EventInfo;
import com.icloud.service.BaseService;

public interface EventInfoService extends BaseService<EventInfo> {
	public PageInfo<EventInfo> findByPage(int pageNo,int pageSize,EventInfo t) throws Exception ;
}
