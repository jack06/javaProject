package com.icloud.dto;

import java.util.ArrayList;
import java.util.List;

import com.icloud.model.AwardRecord;
import com.icloud.vo.AwardRecordVo;

/**
 * 领奖及发货记录分页Dto
 * @author chencl
 * @date 2017-04-18
 */
public class AwardRecordListDto extends BaseDto {
	
	private String resultType;//请求结果类型
	private String resultCode;//返回结果码
	private String errorMsg;//错误提示信息
	private int startIndex;
	private int pageNum = 1;
	private int pageSize = 10;
	private int totalCount;//总记录数
	private Long totalPage;//总页数
	
	private List<AwardRecordVo> awardRecords = new ArrayList<AwardRecordVo>(); 
	
	public AwardRecordListDto() {

	}
	public AwardRecordListDto(String resultType, String resultCode, String errorMsg, int startIndex, int pageNum,
			int pageSize, int totalCount, Long totalPage, List<AwardRecordVo> awardRecords) {
		super(resultType,resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
		this.startIndex = startIndex;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.awardRecords = awardRecords;
	}

	public AwardRecordListDto(String resultType, String resultCode, String errorMsg) {
		super(resultType, resultCode, errorMsg);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
	}
	
	public AwardRecordListDto(String resultType, String resultCode, List<AwardRecordVo> awardRecords) {
		super(resultType,resultCode);
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.awardRecords = awardRecords;
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

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public List<AwardRecordVo> getAwardRecords() {
		return awardRecords;
	}

	public void setAwardRecords(List<AwardRecordVo> awardRecords) {
		this.awardRecords = awardRecords;
	}
}
