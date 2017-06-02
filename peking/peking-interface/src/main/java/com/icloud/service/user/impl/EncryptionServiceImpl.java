package com.icloud.service.user.impl;

import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.icloud.service.BaseServiceImple;
import com.icloud.service.user.EncryptionService;

@Service
public class EncryptionServiceImpl extends BaseServiceImple implements EncryptionService{
	


	public final static Logger log = LoggerFactory
			.getLogger(EncryptionServiceImpl.class);
	/**
	 * 微信加密接口
	 * @param request
	 * @return
	 */
	public Object encryption(String jsonParams) { 

		System.out.print("1000000000= " +jsonParams);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("method", "json");
		map.put("status", "true");
		map.put("ontents", "云软微信小程序欢迎您，您是高人才！");
		return map;
	}	
 
}