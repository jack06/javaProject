package com.icloud.dto.vo.questions;

import java.util.List;

public class DataVo {
	 private boolean  hasTested=false;//是否有权限答问卷
	 private String  title;//问卷标题
	 private String description;//问卷说明
	 private List<QuestionsVo> questions;//问卷题目
	 
	public boolean isHasTested() {
		return hasTested;
	}
	public void setHasTested(boolean hasTested) {
		this.hasTested = hasTested;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<QuestionsVo> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionsVo> questions) {
		this.questions = questions;
	}
	
}
