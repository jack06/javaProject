package com.icloud.common;

import org.apache.commons.lang.StringUtils;


/**
 * 图片储存类
 * @author leiyi
 *
 */
public class ImageStorage {
	/**
	 * 商品图片文件名扩展名
	 */
	public static final String PRODUCT_IMAGE_FILE_EXTENSION = "jpg";

	/**
	 * 放大图后缀
	 */
	public static final String BIG_GOODS_IMAGE_FILE_NAME_SUFFIX = "_800x800";
	/**
	 * 详情页展示图后缀
	 */
	public static final String SHOW_GOODS_IMAGE_FILE_NAME_SUFFIX = "_360x360";

	/**
	 * 列表展示图片后缀
	 */
	public static final String MIDDLE_GOODS_IMAGE_FILE_NAME_SUFFIX = "_180x180";

	/**
	 * 小图后缀
	 */
	public static final String THUMBNAIL_PRODUCT_IMAGE_FILE_NAME_SUFFIX = "_60x60";

	/**
	 * 正方形后缀
	 */
	public static final String SQUARE_PRODUCT_IMAGE_FILE_NAME_SUFFIX = "_SQUARE";

	/**
	 * 图片id
	 */
	private String id;

	/**
	 * 图片文件名扩展名
	 */
	private String sourceFileNameSuffix = "jpg";
	/**
	 * 图片（原）路径
	 */
	private String sourceImagePath;

	/**
	 * 图片（正方形）路径
	 */
	private String squareImagePath;

	
	/**
	 * 图片（详情展示）路径
	 */
	private String showImagePath;
	/**
	 * 图片（列表）路径
	 */
	private String middleImagePath;
	/**
	 * 图片（缩略）路径
	 */
	private String thumbnailImagePath;
	/**
	 * 图片描述
	 */
	private String description;
	/**
	 * 图片排序
	 */
	private String orderList;
	
	/**
	 * 是否列表展示图
	 */
	private String isIndexImg;

	/**
	 * 源图片后缀名称
	 * 
	 * @author:
	 * @return
	 */
	public String getSourceFileNameSuffix() {
		if (StringUtils.isNotEmpty(sourceImagePath)) {
			sourceFileNameSuffix = StringUtils.substringAfterLast(
					sourceImagePath, ".").toLowerCase();
		}
		return sourceFileNameSuffix;
	}

	public void setSourceFileNameSuffix(String sourceFileNameSuffix) {
		this.sourceFileNameSuffix = sourceFileNameSuffix;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getSourceImagePath() {
		return sourceImagePath;
	}

	public void setSourceImagePath(String sourceImagePath) {
		this.sourceImagePath = sourceImagePath;
	}

	public String getSquareImagePath() {
		return squareImagePath;
	}

	public void setSquareImagePath(String squareImagePath) {
		this.squareImagePath = squareImagePath;
	}

	public String getShowImagePath() {
		return showImagePath;
	}

	public void setShowImagePath(String showImagePath) {
		this.showImagePath = showImagePath;
	}

	public String getMiddleImagePath() {
		return middleImagePath;
	}

	public void setMiddleImagePath(String middleImagePath) {
		this.middleImagePath = middleImagePath;
	}

	public String getThumbnailImagePath() {
		return thumbnailImagePath;
	}

	public void setThumbnailImagePath(String thumbnailImagePath) {
		this.thumbnailImagePath = thumbnailImagePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrderList() {
		return orderList;
	}

	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getIsIndexImg() {
		return isIndexImg;
	}

	public void setIsIndexImg(String isIndexImg) {
		this.isIndexImg = isIndexImg;
	}

	
}
