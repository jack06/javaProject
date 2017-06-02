package com.icloud.config.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.icloud.service.AwardRecordService;

/**
 * 获取邮件编号定时任务
 * @author chencl
 *
 */
@Component
@EnableScheduling
public class GetEmsNoJob {
	
	public final static Logger log = LoggerFactory.getLogger(GetEmsNoJob.class);
	
	@Autowired
	private AwardRecordService awardRecordService;
	
	//每天凌晨3点获取邮件编号
//	@Scheduled(cron = "0 0 3  * * ? ")
//	public void getEmsNoJobRun(){
//		log.info("===============getEmsNoJobRun running===============");
//		log.info("===============获取已发货订单邮件编号===============");
//		awardRecordService.getEmsNo();
//	}

}
