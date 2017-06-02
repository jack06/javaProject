package com.icloud.service.eventmanage;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.icloud.model.eventmanage.EventInfo;
import com.icloud.service.BaseService;

public interface EventInfoService extends BaseService<EventInfo> {
	public PageInfo<EventInfo> findByPage(int pageNo,int pageSize,EventInfo t) throws Exception ;
	
	public int importData(MultipartFile file) throws Exception;
}
