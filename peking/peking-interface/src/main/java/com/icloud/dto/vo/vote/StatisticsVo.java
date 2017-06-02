package com.icloud.dto.vo.vote;

/**
 * 住于内存，
 * @author z
 *
 */
public class StatisticsVo {

	private int index;
	private String optionId;
	private int count=0;
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
