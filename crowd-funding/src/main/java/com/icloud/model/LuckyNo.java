package com.icloud.model;


public class LuckyNo {
    private Long id;

    private Long raiseId;//对应活动id

    private Long currentNo;//当前幸运号基数，从原始基数累加

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRaiseId() {
		return raiseId;
	}

	public void setRaiseId(Long raiseId) {
		this.raiseId = raiseId;
	}

	public Long getCurrentNo() {
		return currentNo;
	}

	public void setCurrentNo(Long currentNo) {
		this.currentNo = currentNo;
	}

    
}