package com.icloud.dto.vo.evaluate;

public class ConfigVo {

	private boolean isActive=false;//当前模块是否激活
	//模块有效时间
	private String startTime;
	private String endTime;
	private boolean isAllowEvaluation;//是否允许当前用户权限下。
	
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public boolean isAllowEvaluation() {
		return isAllowEvaluation;
	}
	public void setAllowEvaluation(boolean isAllowEvaluation) {
		this.isAllowEvaluation = isAllowEvaluation;
	}
	
}
