package com.icloud.model.zlProductShow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZlProduct {
	private Long id;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 卷烟类型
	 */
	private String cigaretteType;
	/**
	 * 投产时间（2013年
	 */
	private Date intoOperationTime;
	private String intoOperationTimeStr;
	/**
	 * 产地
	 */
	private String productionPlace;
	/**
	 * 包装类型（条盒硬盒）
	 */
	private String packType;
	/**
	 * 烟支规格84（30+50）mm X 24.5mm
	 */
	private String cigaretteSpecification;
	/**
	 * 焦油量（11mg）
	 */
	private Double tarContent;
	/**
	 * 烟气烟碱量（1.1mg）
	 */
	private Double nicotineContent;
	/**
	 * 烟气一氧化碳量（1.2mg）
	 */
	private Double carbonMonoxideContent;
	/**
	 * 件条形码
	 */
	private String pieceBarcode;
	/**
	 * 条条形码
	 */
	private String cartonBarcode;
	/**
	 * 盒条形码
	 */
	private String boxBarcode;
	/**
	 * 价类
	 */
	private String priceType;
	/**
	 * 建议零售价
	 */
	private Double price;
	/**
	 * 商品类型（0：普通，1：新品，2：人气，3：成熟）
	 */
	private String productType;
	/**
	 * 商品图片URL
	 */
	private String productLogo;
	/**
	 * 商品详情图片URL
	 */
	private String productDetailLogo;
	private Date createTime;
	private Date lastModifyTime;
	/**
	 * 类型（0：特色嘴棒，1：低焦卷烟，2：细支卷烟，3：扫码得龙币，4：扫码的乐豆）
	 */
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCigaretteType() {
		return cigaretteType;
	}

	public void setCigaretteType(String cigaretteType) {
		this.cigaretteType = cigaretteType;
	}

	public Date getIntoOperationTime() {
		return intoOperationTime;
	}

	public void setIntoOperationTime(Date intoOperationTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		this.intoOperationTime = intoOperationTime;
		this.intoOperationTimeStr = df.format(intoOperationTime);
	}

	public String getIntoOperationTimeStr() {
		return intoOperationTimeStr;
	}

	public void setIntoOperationTimeStr(String intoOperationTimeStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.intoOperationTimeStr = intoOperationTimeStr;
		try {
			this.intoOperationTime = df.parse(intoOperationTimeStr+"-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getProductionPlace() {
		return productionPlace;
	}

	public void setProductionPlace(String productionPlace) {
		this.productionPlace = productionPlace;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public String getCigaretteSpecification() {
		return cigaretteSpecification;
	}

	public void setCigaretteSpecification(String cigaretteSpecification) {
		this.cigaretteSpecification = cigaretteSpecification;
	}

	public Double getTarContent() {
		return tarContent;
	}

	public void setTarContent(Double tarContent) {
		this.tarContent = tarContent;
	}

	public Double getNicotineContent() {
		return nicotineContent;
	}

	public void setNicotineContent(Double nicotineContent) {
		this.nicotineContent = nicotineContent;
	}

	public Double getCarbonMonoxideContent() {
		return carbonMonoxideContent;
	}

	public void setCarbonMonoxideContent(Double carbonMonoxideContent) {
		this.carbonMonoxideContent = carbonMonoxideContent;
	}

	public String getPieceBarcode() {
		return pieceBarcode;
	}

	public void setPieceBarcode(String pieceBarcode) {
		this.pieceBarcode = pieceBarcode;
	}

	public String getCartonBarcode() {
		return cartonBarcode;
	}

	public void setCartonBarcode(String cartonBarcode) {
		this.cartonBarcode = cartonBarcode;
	}

	public String getBoxBarcode() {
		return boxBarcode;
	}

	public void setBoxBarcode(String boxBarcode) {
		this.boxBarcode = boxBarcode;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductLogo() {
		return productLogo;
	}

	public void setProductLogo(String productLogo) {
		this.productLogo = productLogo;
	}

	public String getProductDetailLogo() {
		return productDetailLogo;
	}

	public void setProductDetailLogo(String productDetailLogo) {
		this.productDetailLogo = productDetailLogo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	 
}