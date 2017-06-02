package com.icloud.model.staffmanage;

import com.icloud.model.BaseModel;

public class Tuserrole extends BaseModel{
    private String id;

    private String roleName;
    
    private String isNeedVerify;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
    public String getIsNeedVerify() {
        return isNeedVerify;
    }

    public void setIsNeedVerify(String isNeedVerify) {
        this.isNeedVerify = isNeedVerify == null ? null : isNeedVerify.trim();
    }

}