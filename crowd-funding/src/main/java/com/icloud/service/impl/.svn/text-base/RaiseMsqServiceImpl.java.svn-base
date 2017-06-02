package com.icloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.RaiseMsqMapper;
import com.icloud.model.RaiseMsq;
import com.icloud.service.RaiseMsqService;

@Service
public class RaiseMsqServiceImpl implements RaiseMsqService {

	@Autowired
	private RaiseMsqMapper raiseMsqMapper;
	
	@Override
	public int notifyDelivery(RaiseMsq record) {	
		return raiseMsqMapper.insert(record);
	}

}
