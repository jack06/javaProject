package com.icloud.dto;

import java.util.ArrayList;
import java.util.List;

public class ExportExcleDto extends BaseDto {
	
	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	
	private List<AwardRecordExportExcleDto> exportDto = new ArrayList<AwardRecordExportExcleDto>();

	
	
	public ExportExcleDto() {
	}

	public ExportExcleDto(String resultType, String resultCode, String errorMsg) {
		super(resultType, resultCode, errorMsg);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
		this.exportDto = exportDto;
		// TODO Auto-generated constructor stub
	}

	public ExportExcleDto(String resultType, String resultCode, List<AwardRecordExportExcleDto> exportDto) {
		super(resultType, resultCode);
		// TODO Auto-generated constructor stub
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.exportDto = exportDto;
	}

	public ExportExcleDto(String resultType, String resultCode, String errorMsg,
			List<AwardRecordExportExcleDto> exportDto) {
		super(resultType,resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
		this.exportDto = exportDto;
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

	public List<AwardRecordExportExcleDto> getExportDto() {
		return exportDto;
	}

	public void setExportDto(List<AwardRecordExportExcleDto> exportDto) {
		this.exportDto = exportDto;
	}

	
}
