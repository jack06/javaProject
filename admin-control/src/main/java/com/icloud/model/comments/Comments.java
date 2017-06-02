package com.icloud.model.comments;

import java.util.Date;
import java.util.List;

public class Comments {
	private String id;
	private String commentConfigId;//評論配置ID
	private String eventId;//事件id
	private Date createTime;
	private String userId;
	private String nick;
	private String headrImg;
	private List<CommentRecord> recordList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentConfigId() {
		return commentConfigId;
	}
	public void setCommentConfigId(String commentConfigId) {
		this.commentConfigId = commentConfigId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getHeadrImg() {
		return headrImg;
	}
	public void setHeadrImg(String headrImg) {
		this.headrImg = headrImg;
	}
	public List<CommentRecord> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<CommentRecord> recordList) {
		this.recordList = recordList;
	}
	
	
}
