package com.icloud.model.comments;

/**
 * 评论记录表
 * @author leiyi
 *
 */
public class CommentRecord {
	
	private String commentId;
	private String type;//1 文字 2图片
	private String content;
	private int sortNum;
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}



	
	

}
