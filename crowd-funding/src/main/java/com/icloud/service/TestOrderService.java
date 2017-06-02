package com.icloud.service;

import com.icloud.model.TestOrder;


public interface TestOrderService {

	/**
	 * 返回事物的id
	 * @return
	 */

	TestOrder findByTxId(String txId);


}
