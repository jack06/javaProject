package com.icloud.dto;

import com.icloud.vo.AwardRecordVo;

public class AwardRecordVoDto extends BaseDto {
	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	private AwardRecordVo awardRecordVo;

	public AwardRecordVoDto(){
		
	}
	
	public AwardRecordVoDto(String resultType, String resultCode, String errorMsg) {
		super(resultType, resultCode, errorMsg);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
	}

	public AwardRecordVoDto(String resultType, String resultCode,AwardRecordVo awardRecordVo) {
		super(resultType, resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.awardRecordVo = awardRecordVo;
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

	public AwardRecordVo getAwardRecordVo() {
		return awardRecordVo;
	}

	public void setAwardRecordVo(AwardRecordVo awardRecordVo) {
		this.awardRecordVo = awardRecordVo;
	}
	
	
}
