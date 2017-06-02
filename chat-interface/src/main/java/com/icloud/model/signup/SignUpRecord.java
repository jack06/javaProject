package com.icloud.model.signup;

import java.util.Date;

/**
 * 报名记录
 * @author leiyi
 *
 */
public class SignUpRecord {
	
	private String id;
	private String nick;
	private String headerImg;
	private String phone;
	private String email;
	private String name;
	private String signUpConfigId;
	private Date createTime;
	private String signUpUserId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getHeaderImg() {
		return headerImg;
	}
	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSignUpConfigId() {
		return signUpConfigId;
	}
	public void setSignUpConfigId(String signUpConfigId) {
		this.signUpConfigId = signUpConfigId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSignUpUserId() {
		return signUpUserId;
	}
	public void setSignUpUserId(String signUpUserId) {
		this.signUpUserId = signUpUserId;
	}
	

}
