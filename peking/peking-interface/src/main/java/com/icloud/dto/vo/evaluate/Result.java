package com.icloud.dto.vo.evaluate;
/**
 * @filename      : Result.java
 * @description   :  返回评价的结果
 * @author        : zdh
 * @create        : 2017年4月27日 下午2:02:34   
 * @copyright     : zhumeng.com@chat-interface
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class Result {


	private Integer option;//评价等级
	private String count;//当前选项的人数
	private String accounted;//当前选项的占比
	
	public Integer getOption() {
		return option;
	}
	public void setOption(Integer option) {
		this.option = option;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getAccounted() {
		return accounted;
	}
	public void setAccounted(String accounted) {
		this.accounted = accounted;
	}
	
}
