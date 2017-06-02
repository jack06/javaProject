package com.icloud.dto;

import java.util.ArrayList;
import java.util.List;

import com.icloud.model.Raise;

/**
 * 
 * 类名称: RaiseQueryDto
 * 类描述:   众筹活动列表
 * 创建人: zdh
 * 创建时间:2017年4月11日 上午11:42:18
 */
public class RaiseQueryDto extends BaseDto {
	
	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	private int startIndex;
	private int pageNum = 1;
	private int pageSize = 10;
	private int totalCount;//总记录数
	private Long totalPage;//总页数
	
	private List<Raise> raiseList = new ArrayList<Raise>();
	
	public RaiseQueryDto() {

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

	public List<Raise> getRaiseList() {
		return raiseList;
	}

	public void setRaiseList(List<Raise> raiseList) {
		this.raiseList = raiseList;
	}

	public RaiseQueryDto(String resultType, String resultCode,
			int pageNum, int pageSize, List<Raise> raiseList,int totalCount,Long totalPage) {
		super(resultType,resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.raiseList = raiseList;
	}
	

	public RaiseQueryDto(String resultType, String resultCode,
			List<Raise> raiseList) {
		super(resultType,resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.raiseList = raiseList;
	}

	public RaiseQueryDto(String resultType, String resultCode,String errorMsg,
			List<Raise> raiseList) {
		super(resultType,resultCode,errorMsg);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
	}
	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

}
