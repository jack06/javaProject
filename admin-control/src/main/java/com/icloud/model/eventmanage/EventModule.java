package com.icloud.model.eventmanage;

/**
 * 事件模块关联表
 * @author leiyi
 *
 */
public class EventModule {
	
	private String moduleId;
	private String eventId;
	private String moduleLabel;
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getModuleLabel() {
		return moduleLabel;
	}
	public void setModuleLabel(String moduleLabel) {
		this.moduleLabel = moduleLabel;
	}
	

}
