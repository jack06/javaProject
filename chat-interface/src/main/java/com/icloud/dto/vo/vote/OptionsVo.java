package com.icloud.dto.vo.vote;
/**
 * @filename      : OptionsVo.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年4月27日 下午3:36:32   
 * @copyright     : zhumeng.com@chat-interface
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class OptionsVo {

	private String optionName;//选项名称
	private String optionId;//选项id
	private Long count=0L;//投票数
	
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
