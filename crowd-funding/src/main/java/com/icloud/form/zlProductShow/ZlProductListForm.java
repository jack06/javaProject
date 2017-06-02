package com.icloud.form.zlProductShow;

public class ZlProductListForm {

	private int pageNum = 1;//第几页
	private int pageSize = 10;//每页多少条
	private Long totalPage;//总页数
	private Long totalCount;//总记录数
	private String productName;//香烟名称 
	private String cigaretteType;//卷烟类型
	private String tarContent;
	private Double price;//价格
	private String priceOrderType;//按价格排序类型0：升序；1：倒序
	
	private int offset;//分页查询偏移量
	private int endRownum;//结束列

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

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public String getCigaretteType() {
		return cigaretteType;
	}

	public void setCigaretteType(String cigaretteType) {
		this.cigaretteType = cigaretteType;
	}

	public String getTarContent() {
		return tarContent;
	}

	public void setTarContent(String tarContent) {
		this.tarContent = tarContent;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getOffset() {
		offset = (pageNum-1)*pageSize;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getEndRownum() {
		if(totalCount != null){
			if((offset + pageSize)<totalCount){
				endRownum = offset + pageSize;
			}else{
				endRownum = totalCount.intValue();
			}
		}else{
			endRownum = offset + pageSize;
		}
		return endRownum;
	}

	public void setEndRownum(int endRownum) {
		this.endRownum = endRownum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPriceOrderType() {
		return priceOrderType;
	}

	public void setPriceOrderType(String priceOrderType) {
		this.priceOrderType = priceOrderType;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		if(totalCount != null){
			if((offset + pageSize)<totalCount){
				this.endRownum = offset + pageSize;
			}else{
				this.endRownum = totalCount.intValue();
			}
		}else{
			this.endRownum = offset + pageSize;
		}
		this.totalCount = totalCount;
	}
	
	
}
