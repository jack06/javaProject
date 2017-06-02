package com.icloud.form;

/**
 * 
 *创建时间：2016年11月10日 下午4:59:59
 *
 */
public class RecordForm extends BaseForm{
	private int pageNum = 1;//第几页
	private int pageSize = 10;//每页多少条
	private Long totalPage;//总页数
	private Long raiseId;//活动id

	private int offset;//分页查询偏移量
	
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

	public int getOffset(){
		offset = (pageNum-1)*pageSize;
		return offset ;
	}


	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Long getRaiseId() {
		return raiseId;
	}

	public void setRaiseId(Long raiseId) {
		this.raiseId = raiseId;
	}
	
}
