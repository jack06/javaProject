package com.icloud.dto.vo.questions;

import java.util.List;

public class QuestionsVo {

	 private String  questionId;//问题id
	 private String content;//问题内容
	 private int type;//1:单选 2，多选 3：填空
	 private List<OptionsVo> options; //如果是选择题，这里是选项
	 
	 
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<OptionsVo> getOptions() {
		return options;
	}
	public void setOptions(List<OptionsVo> options) {
		this.options = options;
	}
}
