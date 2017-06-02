package com.icloud.dto.vo.questions;

import com.icloud.common.util.StringUtil;

/**
 * 
 * @author z
 *
 */
public class InnerQuetions {

	private Integer index;//对应前台选项下标,填空题没有
	private String questionId;//问题id
	private String optionId;//选项id
	private String type;//类型
	
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null && index!=null ? (questionId+index).hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InnerQuetions)) {
            return false;
        }
        InnerQuetions other = (InnerQuetions) object;
        if(StringUtil.checkObj(this.index) && StringUtil.checkObj(this.questionId)
        		&& StringUtil.checkObj(other.getIndex()) && StringUtil.checkObj(other.getOptionId())
        		&& this.index.equals(other.getIndex()) && this.questionId.equals(other.getOptionId())){
        	 return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "InnerQuetions[ questionId=" + questionId + "index=" + index + " ]";
    }
}
