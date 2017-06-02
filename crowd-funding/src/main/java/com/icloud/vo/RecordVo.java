package com.icloud.vo;


import java.util.Date;

import com.icloud.util.DateUtil;

public class RecordVo {

	private String userId;
	  
	private String raiseId;
	  
	private String orderId;
	
    private String raiseName;

    private Long currentPeriod;

    private Long prizeAmount;

    private Long currentNum;

    private Date outTime;
    
    private String outTimeStr;

    private String productName;
    
    private Date createDate;
    
    private String createDateStr;
    
    private String phone;
    
    private String headPhotoUrl;
    
    private String nickName;
    
    private Long luckyNo;
    
    private String currentStatus;
    
    private Long winStatus;//等于0的时候 为未中奖，否则为中奖号码
    
    private String deliveryStatus; //等于 no 的时候 为用户未领奖，等于0的时候 提醒发货，等于1是已发货，可以查看详情

    private String deliveryAddress;	//收货地址
    
    private String userName;		//用户真实姓名
    
    private String raiseIcon;		//活动图片
    
    private String deliveryType;//发货类型（0：EMS发货；1：京东发货）
    
	public String getRaiseName() {
		return raiseName;
	}

	public void setRaiseName(String raiseName) {
		this.raiseName = raiseName;
	}

	public Long getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Long currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public Long getPrizeAmount() {
		return prizeAmount;
	}

	public void setPrizeAmount(Long prizeAmount) {
		this.prizeAmount = prizeAmount;
	}

	public Long getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Long currentNum) {
		this.currentNum = currentNum;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadPhotoUrl() {
		return headPhotoUrl;
	}

	public void setHeadPhotoUrl(String headPhotoUrl) {
		this.headPhotoUrl = headPhotoUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getLuckyNo() {
		return luckyNo;
	}

	public void setLuckyNo(Long luckyNo) {
		this.luckyNo = luckyNo;
	}

	public Long getWinStatus() {
		return winStatus;
	}

	public void setWinStatus(Long winStatus) {
		this.winStatus = winStatus;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getRaiseId() {
		return raiseId;
	}

	public void setRaiseId(String raiseId) {
		this.raiseId = raiseId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRaiseIcon() {
		return raiseIcon;
	}

	public void setRaiseIcon(String raiseIcon) {
		this.raiseIcon = raiseIcon;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getCreateDateStr() {
		return null!=createDateStr?createDateStr:(null!=createDate?DateUtil.yyyy_MM_ddHHmmss.format(createDate):null);
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getOutTimeStr() {
		return null!=outTimeStr?outTimeStr:(null!=outTime?DateUtil.yyyy_MM_ddHHmmss.format(outTime):null);
	}

	public void setOutTimeStr(String outTimeStr) {
		this.outTimeStr = outTimeStr;
	}
	
}