package com.icloud.dto;

import com.icloud.model.WxFans;

/**
 * 微信粉丝信息查询dto
 * @author chencl
 * @date 2017-04-13
 */
public class WxFansQueryDto extends BaseDto {
	
	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	private WxFans wxFans = new WxFans();

	
	public WxFansQueryDto() {
	}

	public WxFansQueryDto(String resultType, String resultCode, WxFans wxFans) {
		super(resultType, resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.wxFans = wxFans;
	}
	
	public WxFansQueryDto(String resultType, String resultCode, String errorMsg) {
		super(resultType, resultCode, errorMsg);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
	}

	public WxFansQueryDto(String resultType, String resultCode) {
		super(resultType, resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
	}

	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public WxFans getWxFans() {
		return wxFans;
	}
	public void setWxFans(WxFans wxFans) {
		this.wxFans = wxFans;
	}
	
	
}
