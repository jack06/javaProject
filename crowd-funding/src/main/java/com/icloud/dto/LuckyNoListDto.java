package com.icloud.dto;

import java.util.List;

import com.icloud.model.LuckyNo;

public class LuckyNoListDto extends BaseDto{

	private List<LuckyNo> luckyNoList;
	
	public LuckyNoListDto(String resultType, String resultCode){
		super(resultType, resultCode);
	}
	
	public LuckyNoListDto(String resultType, String resultCode, String errorMsg){
		super(resultType, resultCode, errorMsg);
	}

	public List<LuckyNo> getLuckyNoList() {
		return luckyNoList;
	}

	public void setLuckyNoList(List<LuckyNo> luckyNoList) {
		this.luckyNoList = luckyNoList;
	}
	
	
}
