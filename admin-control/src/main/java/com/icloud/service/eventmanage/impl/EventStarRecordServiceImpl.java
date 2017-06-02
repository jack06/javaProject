package com.icloud.service.eventmanage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.eventmanage.EventStarRecordMapper;
import com.icloud.model.eventmanage.EventStarRecord;
import com.icloud.service.eventmanage.EventStarRecordService;


@Service
public class EventStarRecordServiceImpl implements EventStarRecordService {

	@Autowired
	private EventStarRecordMapper eventStartRecordMapper;
	@Override
	public PageInfo<EventStarRecord> findList(int pageNo, int pageSize,
			EventStarRecord eventStarRecord) {
        PageHelper.startPage(pageNo, pageSize);
        return  new PageInfo<EventStarRecord>(eventStartRecordMapper.findList(eventStarRecord));
	}

}
