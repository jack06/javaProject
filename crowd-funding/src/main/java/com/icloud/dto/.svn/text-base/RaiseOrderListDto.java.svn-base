package com.icloud.dto;

import java.util.List;

import com.icloud.vo.RecordVo;

public class RaiseOrderListDto extends BaseDto{

	private List<RecordVo> raiseVoList;
	private int pageNum = 1;	//页码
	private int pageSize = 10;	//每页显示记录数量
	private int totalCount;		//总记录数
	private int totalPage;		//总页数
	
	public RaiseOrderListDto(String resultType, String resultCode){
		super(resultType, resultCode);
	}
	
	public RaiseOrderListDto(String resultType, String resultCode, String errorMsg){
		super(resultType, resultCode, errorMsg);
	}

	public List<RecordVo> getRaiseVoList() {
		return raiseVoList;
	}

	public void setRaiseVoList(List<RecordVo> raiseVoList) {
		this.raiseVoList = raiseVoList;
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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
