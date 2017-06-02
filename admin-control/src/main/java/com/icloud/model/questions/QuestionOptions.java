package com.icloud.model.questions;

/**
 * 问题选项
 * @author z
 *
 */
public class QuestionOptions {
    private String id;

    private String questionId;

    private String optionsContent;

    private String isCorrect;

    private String questionnaireId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getOptionsContent() {
        return optionsContent;
    }

    public void setOptionsContent(String optionsContent) {
        this.optionsContent = optionsContent == null ? null : optionsContent.trim();
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect == null ? null : isCorrect.trim();
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId == null ? null : questionnaireId.trim();
    }
}