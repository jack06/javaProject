package com.icloud.dto;

import com.icloud.model.AwardRecord;

public class AwardRecordQueryDto extends BaseDto {
	
	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	private AwardRecord awardRecord;
	
	public AwardRecordQueryDto(){
		
	}
	
	public AwardRecordQueryDto(String resultType, String resultCode, AwardRecord awardRecord) {
		super(resultType, resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.awardRecord = awardRecord;
	}
	
	public AwardRecordQueryDto(String resultType, String resultCode, String errorMsg) {
		super(resultType, resultCode, errorMsg);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
	}
	
	public AwardRecordQueryDto(String resultType, String resultCode, String errorMsg, AwardRecord awardRecord) {
		super(resultType, resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
		this.awardRecord = awardRecord;
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
	public AwardRecord getAwardRecord() {
		return awardRecord;
	}
	public void setAwardRecord(AwardRecord awardRecord) {
		this.awardRecord = awardRecord;
	}
}
