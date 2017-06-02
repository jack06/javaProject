package com.icloud.service.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.event.EventStarRecordMapper;
import com.icloud.model.event.EventStarRecord;
import com.icloud.service.event.EventStarRecordService;

@Service
public class EventStarRecordServiceImpl implements EventStarRecordService {

	@Autowired
	private EventStarRecordMapper eventStarRecordMapper;
	@Override
	public void save(EventStarRecord eventStarRecord) {
		eventStarRecordMapper.save(eventStarRecord);

	}

	@Override
	public int findCountByEvent(String eventId) {
		return eventStarRecordMapper.findCountByEvent(eventId);
	}

	@Override
	public EventStarRecord selectByUser(EventStarRecord eventStarRecord) {
		return eventStarRecordMapper.selectByUser(eventStarRecord);
	}

	@Override
	public PageInfo<EventStarRecord> findList(int pageNo, int pageSize, EventStarRecord eventStarRecord) {
		PageHelper.startPage(pageNo, pageSize);
		List<EventStarRecord> list = eventStarRecordMapper.findList(eventStarRecord);
		return new PageInfo<EventStarRecord>(list);
	}

}
