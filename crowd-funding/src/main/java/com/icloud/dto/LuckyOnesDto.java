package com.icloud.dto;


/**
 * 类名称： LuckyOnesDto
 * 类描述： 中奖人信息
 * 创建人： weiwy
 * 创建时间： 2017-4-14 上午9:26:57
 *
 */
public class LuckyOnesDto {

	private String userNick;		//昵称
	private Long luckyNo;			//幸运号
	private String outTime;			//开奖时间
	
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public Long getLuckyNo() {
		return luckyNo;
	}
	public void setLuckyNo(Long luckyNo) {
		this.luckyNo = luckyNo;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	
}
