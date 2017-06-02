package com.icloud.config.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.icloud.service.RaiseService;

/**
 * 
 * 类名称: RaiseStatusJob
 * 类描述: 活动状态监控任务
 * 创建人: zdh
 * 创建时间:2017年4月19日 上午11:31:36
 */
@Component
@EnableScheduling
public class RaiseStatusJob{
	
	public final static Logger log = LoggerFactory.getLogger(RaiseStatusJob.class);

	@Autowired
	private RaiseService raiseService;
	
	//每妙运行一次
	@Scheduled(cron = "0/3 * * * * ?")
	public void raiseStatusJoRun(){
		raiseService.raiseStatusJo();
	}

}
