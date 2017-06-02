package com.icloud.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AwardRecordVo {
	private Long id;

    private Long orderId;//订单id

    private Long raiseId;//众筹id

    private Long userId;//中奖（领奖人id）

    private String userNick;//领奖人昵称

    private String deliveryAddress;//发货地址（领奖后填写）

    private String deliveryStatus;//发货状态

    private String courierName;//快递名称

    private String courierNo;//快递名称

    private String deliveryPhone;
    
    private Date createDate;	//创建时间
    
    private String createDateStr;
    
    private String productName;	//商品名称
    
    private String deliveryType;//发货类型（0：EMS发货；1：京东发货）

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getRaiseId() {
		return raiseId;
	}

	public void setRaiseId(Long raiseId) {
		this.raiseId = raiseId;
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

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	public String getCourierNo() {
		return courierNo;
	}

	public void setCourierNo(String courierNo) {
		this.courierNo = courierNo;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createDate = createDate;
		this.createDateStr = df.format(createDate);
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createDateStr = createDateStr;
		try {
			this.createDate = df.parse(createDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
}
