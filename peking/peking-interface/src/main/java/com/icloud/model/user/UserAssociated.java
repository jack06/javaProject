package com.icloud.model.user;

/**
 * 好友关联表
 * @author leiyi
 *
 */
public class UserAssociated {

	private String masterUserId;
	private String slaveUserId;
	
	public String getMasterUserId() {
		return masterUserId;
	}
	public void setMasterUserId(String masterUserId) {
		this.masterUserId = masterUserId;
	}
	public String getSlaveUserId() {
		return slaveUserId;
	}
	public void setSlaveUserId(String slaveUserId) {
		this.slaveUserId = slaveUserId;
	}
	

}
