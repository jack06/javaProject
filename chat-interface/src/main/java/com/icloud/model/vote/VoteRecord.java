package com.icloud.model.vote;

import java.util.Date;

/**
 * 投票记录
 * @author z
 *
 */

public class VoteRecord {
    private String id;

    private String eventId;

    private Date createTime;

    private String voteUser;

    private String optionId;

    private String moduleId;
    
    private String nick;
    private String headerImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVoteUser() {
        return voteUser;
    }

    public void setVoteUser(String voteUser) {
        this.voteUser = voteUser == null ? null : voteUser.trim();
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId == null ? null : optionId.trim();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}
    
}