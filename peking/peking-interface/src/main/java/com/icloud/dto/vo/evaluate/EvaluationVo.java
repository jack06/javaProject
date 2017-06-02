package com.icloud.dto.vo.evaluate;

public class EvaluationVo {

	 private String  option;//1 好评 2中评 3差评
	 private String content;//评价内容
	
	 public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
