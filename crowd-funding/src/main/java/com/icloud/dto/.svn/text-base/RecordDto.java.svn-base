package com.icloud.dto;

import java.util.ArrayList;
import java.util.List;

import com.icloud.vo.RecordVo;

/**
 * 
 * 类名称: RaiseQueryDto
 * 类描述:   众筹活动列表
 * 创建人: zdh
 * 创建时间:2017年4月11日 上午11:42:18
 */
public class RecordDto extends BaseDto {
	
	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	private int startIndex;
	private int pageNum = 1;
	private int pageSize = 10;
	private int totalCount=0;//总记录数
	private Long totalPage=0L;//总页数
	
	private List<RecordVo> recordVoList = new ArrayList<RecordVo>();
	
	public RecordDto() {

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



	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public List<RecordVo> getRecordVoList() {
		return recordVoList;
	}

	public void setRecordVoList(List<RecordVo> recordVoList) {
		this.recordVoList = recordVoList;
	}

	public RecordDto(String resultType, String resultCode,
			int pageNum, int pageSize, List<RecordVo> recordVoList,int totalCount,Long totalPage) {
		super(resultType,resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.recordVoList = recordVoList;
	}
	

	public RecordDto(String resultType, String resultCode,
			List<RecordVo> recordVoList) {
		super(resultType,resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.recordVoList = recordVoList;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

}
