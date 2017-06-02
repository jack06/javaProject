package com.icloud.model;


import java.util.Date;

public class PrizeRecord {
    private Long id;

    private Long raiseId;//众筹id

    private Date outTime;//开奖时间

    private Long luckyNo;//中奖幸运号

    private Long userId;//中奖用户id

    private String userNick;//中奖人的昵称

    private Long orderId;// 中奖对应的订单
    
    //级联众筹
    private Raise raise;

    private String isNotify;

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

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Long getLuckyNo() {
		return luckyNo;
	}

	public void setLuckyNo(Long luckyNo) {
		this.luckyNo = luckyNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getIsNotify() {
		return isNotify;
	}

	public void setIsNotify(String isNotify) {
		this.isNotify = isNotify;
	}

	public Raise getRaise() {
		return raise;
	}

	public void setRaise(Raise raise) {
		this.raise = raise;
	}

    
}