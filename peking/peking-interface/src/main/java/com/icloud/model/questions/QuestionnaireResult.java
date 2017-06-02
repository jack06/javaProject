package com.icloud.model.questions;

/**
 * 问卷结果
 * @author z
 *
 */
public class QuestionnaireResult {
    private String id;

    private String questionnaireId;

    private String userId;

    private String questionnaireResult;

    private String resultNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId == null ? null : questionnaireId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getQuestionnaireResult() {
        return questionnaireResult;
    }

    public void setQuestionnaireResult(String questionnaireResult) {
        this.questionnaireResult = questionnaireResult == null ? null : questionnaireResult.trim();
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote == null ? null : resultNote.trim();
    }
}