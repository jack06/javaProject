package com.icloud.model.evaluate;

import java.util.Date;

/**
 * 评价配置
 * @author z
 *
 */
public class EvaluationConfig {
    private String id;

    private String eventId;

    private String isOpen;

    private Date startTime;

    private Date endTime;

    private String isAllowType;

    private String model;

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

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen == null ? null : isOpen.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIsAllowType() {
        return isAllowType;
    }

    public void setIsAllowType(String isAllowType) {
        this.isAllowType = isAllowType == null ? null : isAllowType.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }
}