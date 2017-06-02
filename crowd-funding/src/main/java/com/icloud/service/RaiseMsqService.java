package com.icloud.service;

import com.icloud.model.RaiseMsq;

public interface RaiseMsqService {

	int notifyDelivery(RaiseMsq record);
}
