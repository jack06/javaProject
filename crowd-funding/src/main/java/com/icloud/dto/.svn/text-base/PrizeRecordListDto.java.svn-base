package com.icloud.dto;

import java.util.List;

public class PrizeRecordListDto extends BaseDto{

	private List<PrizeRecordEntityDto> prizeRecordList;	//开奖信息列表
	private List<Long> raisePeriodList;		//所有众筹期数
	private Long currentPeriod;	//当前查询期数
	private int pageNum = 1;	//页码
	private int pageSize = 10;	//每页显示记录数量
	private int totalCount;		//总记录数
	private int totalPage;		//总页数
	
	
	public PrizeRecordListDto(){}
	
	public PrizeRecordListDto(String resultType, String resultCode){
		super(resultType, resultCode);
	}
	
	public PrizeRecordListDto(String resultType, String resultCode, String errorMsg){
		super(resultType, resultCode, errorMsg);
	}

	public List<PrizeRecordEntityDto> getPrizeRecordList() {
		return prizeRecordList;
	}

	public void setPrizeRecordList(List<PrizeRecordEntityDto> prizeRecordList) {
		this.prizeRecordList = prizeRecordList;
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

	public List<Long> getRaisePeriodList() {
		return raisePeriodList;
	}

	public void setRaisePeriodList(List<Long> raisePeriodList) {
		this.raisePeriodList = raisePeriodList;
	}

	public Long getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Long currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

}
