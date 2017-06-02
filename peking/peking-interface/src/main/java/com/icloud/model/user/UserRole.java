package com.icloud.model.user;

/**
 * 用户角色表
 * @author leiyi
 *
 */
public class UserRole {
	private String id;
	private String roleName;
	private String isNeedVerify;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getIsNeedVerify() {
		return isNeedVerify;
	}
	public void setIsNeedVerify(String isNeedVerify) {
		this.isNeedVerify = isNeedVerify;
	}
	

}
