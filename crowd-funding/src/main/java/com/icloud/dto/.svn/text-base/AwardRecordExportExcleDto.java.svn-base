package com.icloud.dto;

import java.util.Date;

/**
 * 众筹中奖数据导出Dto
 * @author chencl
 * @date 2017-04-12
 */
public class AwardRecordExportExcleDto {
	 	
	private Long orderId;

	private Long raiseId;

	private Long userId;

	private String userNick;
	
    private String productName;	//商品名称

	private String deliveryAddress;

	private String deliveryStatus;

	private String courierName;

	private String courierNo;

	private String deliveryPhone;
	
    private Date createDate;	//创建时间

    public AwardRecordExportExcleDto(){
    	
    }
    
	public AwardRecordExportExcleDto(Long orderId, Long raiseId, Long userId, String userNick, String productName,
			String deliveryAddress, String deliveryStatus, String courierName, String courierNo, String deliveryPhone,
			Date createDate) {
		super();
		this.orderId = orderId;
		this.raiseId = raiseId;
		this.userId = userId;
		this.userNick = userNick;
		this.productName = productName;
		this.deliveryAddress = deliveryAddress;
		this.deliveryStatus = deliveryStatus;
		this.courierName = courierName;
		this.courierNo = courierNo;
		this.deliveryPhone = deliveryPhone;
		this.createDate = createDate;
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
		if(this.deliveryStatus.equals("0")){
			return "未发货";
		}else if(this.deliveryStatus.equals("1")){
			return "已发货";
		}else{
			return deliveryStatus;
		}
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
	
	
}
