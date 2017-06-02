package com.icloud.model;

import java.util.Date;

public class RaiseOrder {
    private Long id;

    private String userNick;//用户的昵称

    private Long userId;//对应用户ID

    private Long raiseId;//对应众筹表ID

    private Long totalLedou;// 所需的总乐豆

    private Long totalShare;//总购买的份数

    private Date createDate;//参与时间
    
    private String isAlert;		//是否提示用户中奖或未中奖：0未提示，1已提示

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRaiseId() {
		return raiseId;
	}

	public void setRaiseId(Long raiseId) {
		this.raiseId = raiseId;
	}

	public Long getTotalLedou() {
		return totalLedou;
	}

	public void setTotalLedou(Long totalLedou) {
		this.totalLedou = totalLedou;
	}

	public Long getTotalShare() {
		return totalShare;
	}

	public void setTotalShare(Long totalShare) {
		this.totalShare = totalShare;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(String isAlert) {
		this.isAlert = isAlert;
	}
    
}