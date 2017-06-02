package com.icloud.model.event;

/**
 * 事件访问类型关联
 * @author leiyi
 *
 */
public class EventAllowAccess {

	private String eventId;
	private String userRoleId;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	
}
