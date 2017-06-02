package com.icloud.bean;

import java.util.List;

public class Page<T> {

	private int pageNum = 1; // 页码
	private int pageSize = 	10; // 每页显示的数量
	private int totalCount; // 总记录数
	private int totalPage; // 总页数
	private List<T> recordList; // 本页的数据列表 

	
	
	public Page() {
		super();
	}

	
	
	public Page(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}


	public Page(int pageNum, int pageSize, int totalCount,List<T> recordList) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount; 
		this.recordList = recordList;
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
		 this.totalPage = (totalCount+pageSize-1)/pageSize;
		 return totalPage;
	}


	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

}
