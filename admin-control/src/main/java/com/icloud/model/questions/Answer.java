package com.icloud.model.questions;

import java.util.Date;

/**
 * 问卷答案
 * @author z
 *
 */
public class Answer {
    private String id;

    private String questioonnaireId;

    private String questionId;

    private String optionsId;

    private String isCorrect;

    private String userId;
    
    private Questions question;
    
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQuestioonnaireId() {
        return questioonnaireId;
    }

    public void setQuestioonnaireId(String questioonnaireId) {
        this.questioonnaireId = questioonnaireId == null ? null : questioonnaireId.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId == null ? null : optionsId.trim();
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect == null ? null : isCorrect.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
    
}