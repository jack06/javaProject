package com.icloud.config.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.icloud.service.RaiseService;

/**
 * 类名称： LuckDrawJob
 * 类描述： 抽奖定时任务
 * 创建人： weiwy
 * 创建时间： 2017-4-14 下午3:48:56
 *
 */
@Component
@EnableScheduling
public class LuckDrawJob{
	
	public final static Logger log = LoggerFactory.getLogger(LuckDrawJob.class);

	@Autowired
	private RaiseService raiseService;
	
	//每天10点进行抽奖
	@Scheduled(cron = "0 0 10  * * ? ")
	public void luckDrawJobRun(){
		log.info("===============luckDrawJobRun running===============");
		raiseService.LuckDraw();
	}

}
