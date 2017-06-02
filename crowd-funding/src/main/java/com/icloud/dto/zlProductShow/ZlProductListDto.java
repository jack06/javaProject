package com.icloud.dto.zlProductShow;

import java.util.ArrayList;
import java.util.List;

import com.icloud.dto.BaseDto;
import com.icloud.model.zlProductShow.ZlProduct;

/**
 * 真龙产品展示分页Dto
 * @author chencl
 * @date 2017-05-11
 */
public class ZlProductListDto extends BaseDto {

	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	private int startIndex;
	private int pageNum = 1;
	private int pageSize = 10;
	private int totalCount;//总记录数
	private int totalPage;//总页数
	private List<ZlProduct> zlProducts = new ArrayList<ZlProduct>();
	
	public ZlProductListDto() {
	}
	public ZlProductListDto(String resultType, String resultCode, String errorMsg) {
		super(resultType, resultCode, errorMsg);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
	}
	public ZlProductListDto(String resultType, String resultCode, List<ZlProduct> zlProducts) {
		super(resultType, resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.zlProducts = zlProducts;
	}
	
	public ZlProductListDto(String resultType, String resultCode, String errorMsg, int startIndex, int pageNum,
			int pageSize, int totalCount, int totalPage, List<ZlProduct> zlProducts) {
		super(resultType, resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
		this.startIndex = startIndex;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.zlProducts = zlProducts;
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
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
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
	public List<ZlProduct> getZlProducts() {
		return zlProducts;
	}
	public void setZlProducts(List<ZlProduct> zlProducts) {
		this.zlProducts = zlProducts;
	}
	
	
}
