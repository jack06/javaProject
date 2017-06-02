package com.icloud.dto;

import com.icloud.model.RaiseOrder;

public class RaiseOrderItemsDto extends BaseDto {
	
	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	private RaiseOrder raiseOrder;
	
	
	public RaiseOrderItemsDto() {
	}
	public RaiseOrderItemsDto(String resultType, String resultCode, String errorMsg) {
		super(resultType, resultCode, errorMsg);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
	}
	public RaiseOrderItemsDto(String resultType, String resultCode, RaiseOrder raiseOrder) {
		super(resultType, resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.raiseOrder = raiseOrder;
	}
	public RaiseOrderItemsDto(String resultType, String resultCode, String errorMsg, RaiseOrder raiseOrder) {
		super();
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
		this.raiseOrder = raiseOrder;
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
	public RaiseOrder getRaiseOrder() {
		return raiseOrder;
	}
	public void setRaiseOrder(RaiseOrder raiseOrder) {
		this.raiseOrder = raiseOrder;
	}
	
}
