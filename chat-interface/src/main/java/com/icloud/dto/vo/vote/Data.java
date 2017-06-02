package com.icloud.dto.vo.vote;

import java.util.List;

public class Data {
	
	 private boolean hasVoted=false;//我是否已投票
	 private String  votedOptionId;//如果以投票，我的投票选项id是
	 private String  title;//投票标题
	 private String description;//投票描述
	 private List<OptionsVo> options;//所有投票选项
	 
	public boolean isHasVoted() {
		return hasVoted;
	}
	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

	public String getVotedOptionId() {
		return votedOptionId;
	}
	public void setVotedOptionId(String votedOptionId) {
		this.votedOptionId = votedOptionId;
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
	public List<OptionsVo> getOptions() {
		return options;
	}
	public void setOptions(List<OptionsVo> options) {
		this.options = options;
	}
	
}
