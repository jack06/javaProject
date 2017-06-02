package com.icloud.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类名称： LuckyDrawUtil
 * 类描述： 开奖工具类
 * 创建人： weiwy
 * 创建时间： 2017-4-19 下午2:09:59
 *
 */
public class LuckyDrawUtil {
	
	public final static Logger log = LoggerFactory.getLogger(LuckyDrawUtil.class);

	public static final SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
	
	public static Long getFirstLuckyNo(){
		String luckyNoStr = null;
		Long firstLuckyNo = null; 
		try{
			luckyNoStr = ConfigUtil.get("luckyNo");
			if(null == luckyNoStr || "".equals(luckyNoStr)){
				log.info("ConfigUtil.get(\"luckyNo\")获取不到值，返回null");
				return null;
			}
			firstLuckyNo = Long.valueOf(luckyNoStr);
		}catch(Exception ex){
			log.error("luckyNoStr convert to Long error, luckyNoStr=["+luckyNoStr+"]", ex);
			return null;
		}
		return firstLuckyNo;
	}
	
	public static long sumRaiseJoinTime(long totalTime, Date date){
		String timeStr = sdf.format(date);
		long time = Long.valueOf(timeStr);
		return totalTime + time;
	}
	
	public static long nextWinnerNumber(long winnerNumber, long num, boolean isAdd){
		if(isAdd)
			return winnerNumber + (new Random().nextInt((int)(num+1)));
		else
			return winnerNumber - (new Random().nextInt((int)(num+1)));
	}
}
