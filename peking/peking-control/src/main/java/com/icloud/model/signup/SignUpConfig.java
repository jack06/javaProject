package com.icloud.model.signup;

import java.util.Date;

/**
 * 报名配置
 * @author leiyi
 *
 */
public class SignUpConfig {
	
	private String id;//配置Id
	private String eventId;//事件Id
	private Date startTime;
	private Date endTime;
	private String model;//评论模式 1传统列表 2弹幕模式
	private String isOpen;//是否开启
	private int totalNum;//总报名人数
	private String isAllowType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public String getIsAllowType() {
		return isAllowType;
	}
	public void setIsAllowType(String isAllowType) {
		this.isAllowType = isAllowType;
	}
	
	

}
