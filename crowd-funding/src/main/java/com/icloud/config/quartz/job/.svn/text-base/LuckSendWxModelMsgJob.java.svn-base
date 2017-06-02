package com.icloud.config.quartz.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.icloud.model.PrizeRecord;
import com.icloud.service.PrizeRecordService;
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
public class LuckSendWxModelMsgJob{
	
	public final static Logger log = LoggerFactory.getLogger(LuckSendWxModelMsgJob.class);

	@Autowired
	private PrizeRecordService prizeRecordService;
	
	//每天10点进行抽奖
	@Scheduled(cron = "0 1 10  * * ? ")
	public void sendWxModelMsgJob(){
		//查询中奖记录级联出众筹对象
		List<PrizeRecord> list =   prizeRecordService.selectRecordJoinRaise();
		log.info("查询中奖记录-级联众筹对象=="+list.size()+"/n");
		for (PrizeRecord pr : list) {			
			try {
				prizeRecordService.sendMsgToLucker(pr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//每天10点进行抽奖
	@Scheduled(cron = "0 5 10  * * ? ")
	public void sendWxModelMsgJob2(){
		//查询中奖记录级联出众筹对象
		List<PrizeRecord> list =   prizeRecordService.selectRecordJoinRaise();
		log.info("查询中奖记录-级联众筹对象=="+list.size()+"/n");
		for (PrizeRecord pr : list) {			
			try {
				prizeRecordService.sendMsgToLucker(pr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//每天10点进行抽奖
	@Scheduled(cron = "0 20 10  * * ? ")
	public void sendWxModelMsgJob3(){
		//查询中奖记录级联出众筹对象
		List<PrizeRecord> list =   prizeRecordService.selectRecordJoinRaise();
		log.info("查询中奖记录-@@@@-级联众筹对象=="+list.size()+"/n");
		for (PrizeRecord pr : list) {			
			try {
				prizeRecordService.sendMsgToLucker(pr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//每天10点进行抽奖
	@Scheduled(cron = "0 0 11  * * ? ")
	public void sendWxModelMsgJob4(){
		//查询中奖记录级联出众筹对象
		List<PrizeRecord> list =   prizeRecordService.selectRecordJoinRaise();
		log.info("查询中奖记录-@@@@-级联众筹对象=="+list.size()+"/n");
		for (PrizeRecord pr : list) {			
			try {
				prizeRecordService.sendMsgToLucker(pr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
}
