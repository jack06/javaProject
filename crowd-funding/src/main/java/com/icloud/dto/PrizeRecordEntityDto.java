package com.icloud.dto;

import java.util.Date;

import com.icloud.util.DateUtil;


public class PrizeRecordEntityDto {

	private Long currentPeriod;		//众筹期数
	private Long currentNum;		//众筹期号
	private Date outTime;			//开奖时间
	private Long luckyNo;			//幸运号码
	private String userNick;		//用户昵称
	private String outTimeStr;		//开奖时间字符串
	private Long arId;				//发货表id，用于检查用户是否已经领奖，如果为null表示未领奖，否则表示已领奖
	
	public Long getCurrentPeriod() {
		return currentPeriod;
	}
	public void setCurrentPeriod(Long currentPeriod) {
		this.currentPeriod = currentPeriod;
	}
	public Long getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(Long currentNum) {
		this.currentNum = currentNum;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Long getLuckyNo() {
		return luckyNo;
	}
	public void setLuckyNo(Long luckyNo) {
		this.luckyNo = luckyNo;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public Long getArId() {
		return arId;
	}
	public void setArId(Long arId) {
		this.arId = arId;
	}
	public String getOutTimeStr() {
		return null!=outTimeStr?outTimeStr:(null!=outTime?DateUtil.yyyy_MM_ddHHmmss.format(outTime):null);
	}
	public void setOutTimeStr(String outTimeStr) {
		this.outTimeStr = outTimeStr;
	}
	
}
