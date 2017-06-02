package com.icloud.dto.vo.vote;

public class Config {

	private boolean isActive;//当前模块是否激活
	//模块有效时间
	private String startTime;
	private String endTime;
	private boolean isAllowVote;//是否允许当前用户权限下投票。
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
	public boolean isAllowVote() {
		return isAllowVote;
	}
	public void setAllowVote(boolean isAllowVote) {
		this.isAllowVote = isAllowVote;
	}
}
