package com.icloud.dto.vo.questions;
/**
 * @filename      : AnswersVo.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年4月26日 下午4:33:17   
 * @copyright     : zhumeng.com@chat-interface
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class AnswersVo {
	
	 private String  questionId;//问题id
	 private String optionValue;//问题答案， 单选题格式为单个字段；多选题为多个数字 用逗号分隔；填空题为填空内容
	 
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	 
}
