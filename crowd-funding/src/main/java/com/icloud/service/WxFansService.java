package com.icloud.service;

import java.math.BigDecimal;
import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.dto.AddressEditSucessDto;
import com.icloud.dto.WxFansQueryDto;
import com.icloud.model.WxFans;

public interface WxFansService {

	int deleteByPrimaryKey(Long id);

    int insert(WxFans record);

    int insertSelective(WxFans record);

    WxFans selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxFans record);

    int updateByPrimaryKey(WxFans record);
    
    AddressEditSucessDto updateWxFansAddress(WxFans record);
	
	WxFansQueryDto findWxFansByOpenid(String openid);

	public List<WxFans> getFansList(WxFans wxFans);
	
	int updateByOpenId(WxFans record);
	
	
}
