package com.icloud.form;

/**
 * 
 * 类名称: BaseForm
 * 类描述: 用于数据安全检验,用于 不同网段的服务器间数据交互，内网服务器间不用交互
 * 创建人: zdh
 * 创建时间:2017年4月11日 上午10:41:43
 */
public class BaseForm {
	
	private String openid;
	private String encryOpenid;
	private String sign;
	
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getEncryOpenid() {
		return encryOpenid;
	}
	public void setEncryOpenid(String encryOpenid) {
		this.encryOpenid = encryOpenid;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
