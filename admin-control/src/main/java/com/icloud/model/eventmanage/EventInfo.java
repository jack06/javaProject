package com.icloud.model.eventmanage;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.icloud.dto.vo.EventIamge;

public class EventInfo {
    private String id;

    private String eventName;

    private String eventPics;//json 

    private String typeId;//事件类型

    private String isPublic="1";//默认0不公开

    private String eventOriginator;//事件发布者

    private String publisherType;//事件发布者人员类型

    private Date startDate;

    private Date endDate;

    private String isCycle;

    private Date createDate;//发布时间

    private String eventStatus;  //0提交未发布  1已提交待审核  2 已发布  3审核失败  4截止 \结束

    private String parentId;
    
    private String address;
    
    private String longitude;//經度
    
    private String latitude;
    
    private List<EventAllowAccess> accessList;

    private String currentRoleId;//当前查询的角色ID，仅用于查询
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

  

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

    public String getEventOriginator() {
        return eventOriginator;
    }

    public void setEventOriginator(String eventOriginator) {
        this.eventOriginator = eventOriginator == null ? null : eventOriginator.trim();
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getIsCycle() {
        return isCycle;
    }

    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle == null ? null : isCycle.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus == null ? null : eventStatus.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

	public String getEventPics() {
		return eventPics;
	}

	public void setEventPics(String eventPics) {
		this.eventPics = eventPics;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取事件图片集合
	 * @return
	 */
	public JSONArray getPictureUrls(){
		if(StringUtils.isBlank(this.eventPics)){
			return new JSONArray();
		}
		List<EventIamge> list= JSONArray.parseArray(this.eventPics, EventIamge.class);
		JSONArray array = new JSONArray();
		
		for(int i=0;null!=list&&i<list.size();i++){
			array.add(list.get(i).getUrl());
		}
		
		return array;	
	}
	
	/**
	 * 设置事件图片集合
	 * @param list
	 */
	public void PictureUrls(List<EventIamge> list){
		JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
		this.eventPics = array.toJSONString();
	}
	
	/**
	 * 获取事件大图
	 * @return
	 */
	public String getIndexUrl(){
		if(StringUtils.isBlank(this.eventPics)){
			return null;
		}
		List<EventIamge> list= JSONArray.parseArray(this.eventPics, EventIamge.class);
		
		for(int i=0;null!=list&&i<list.size();i++){
			if(list.get(i).getIsIndex()){
				return list.get(i).getUrl();
			}
		}
		
		return null;	
	}
	public String getPublisherType() {
		return publisherType;
	}

	public void setPublisherType(String publisherType) {
		this.publisherType = publisherType;
	}

	public List<EventAllowAccess> getAccessList() {
		return accessList;
	}

	public void setAccessList(List<EventAllowAccess> accessList) {
		this.accessList = accessList;
	}

	public String getCurrentRoleId() {
		return currentRoleId;
	}

	public void setCurrentRoleId(String currentRoleId) {
		this.currentRoleId = currentRoleId;
	}
	
    
}