package com.icloud.dto.vo.questions;

public class ConfigVo {

	private boolean isActive;//当前模块是否激活
	//模块有效时间
	private String startTime;
	private String endTime;
	private boolean isAllowTest;//是否允许当前用户权限下。
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
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
	public boolean isAllowTest() {
		return isAllowTest;
	}
	public void setAllowTest(boolean isAllowTest) {
		this.isAllowTest = isAllowTest;
	}
	
}
