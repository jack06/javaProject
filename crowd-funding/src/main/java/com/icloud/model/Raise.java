package com.icloud.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Raise {
    private Long id;

    private String raiseName;//众筹名称

    private Long currentPeriod;//当前期数
    
    private Long currentNum;//当期号数

    private String raiseIcon;//众筹图标

    private Long totalLedou;//总乐豆数

    private Long maxSare;//每人最多可购买份数

    private Long totalShare;//总份额数

    private Long eachTotal;//每份所需乐豆数

    private Date startDate;//开始时间

    private Date endDate;//截至时间

    private String currentStatus;//当前状态(0即将开始、1正在众筹、2已截止待开奖、3已完成)

    private String productDetails;//众筹商品详情

    private String productSpecifications;//众筹商品规格

    private Long prizeAmount;//奖品数量

    private Date outTime;//计划开奖时间

    private String productName;//商品名称

    private Double productPrice;//商品价格
    
    private Date createTime;//创建时间
    
    private String deliveryType;//发货类型（0：EMS发货；1：京东发货）
    private String prizeType;//奖品类型值参数（type）由酷码提供
    
    
    private int luckyNum;
    
    private List<RaiseOrder> raiseOrders = new ArrayList<RaiseOrder>();	//众筹订单

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getRaiseIcon() {
		return raiseIcon;
	}

	public void setRaiseIcon(String raiseIcon) {
		this.raiseIcon = raiseIcon;
	}

	public Long getTotalLedou() {
		return totalLedou;
	}

	public void setTotalLedou(Long totalLedou) {
		this.totalLedou = totalLedou;
	}

	public Long getMaxSare() {
		return maxSare;
	}

	public void setMaxSare(Long maxSare) {
		this.maxSare = maxSare;
	}

	public Long getTotalShare() {
		return totalShare;
	}

	public void setTotalShare(Long totalShare) {
		this.totalShare = totalShare;
	}

	public Long getEachTotal() {
		return eachTotal;
	}

	public void setEachTotal(Long eachTotal) {
		this.eachTotal = eachTotal;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getProductSpecifications() {
		return productSpecifications;
	}

	public void setProductSpecifications(String productSpecifications) {
		this.productSpecifications = productSpecifications;
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

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	

	public List<RaiseOrder> getRaiseOrders() {
		return raiseOrders;
	}

	public void setRaiseOrders(List<RaiseOrder> raiseOrders) {
		this.raiseOrders = raiseOrders;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public int getLuckyNum() {
		return luckyNum;
	}

	public void setLuckyNum(int luckyNum) {
		this.luckyNum = luckyNum;
	}

	public String getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}

   
}