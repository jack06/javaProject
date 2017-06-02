package com.icloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.dao.WxFansMapper;
import com.icloud.dto.AddressEditSucessDto;
import com.icloud.dto.WxFansQueryDto;
import com.icloud.model.WxFans;
import com.icloud.service.WxFansService;
@Service
public class WxFansServiceImpl implements WxFansService{

	@Autowired
	private WxFansMapper wxFansMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return wxFansMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxFans record) {
		// TODO Auto-generated method stub
		List<WxFans> list = getFansList(record);
		if(list!=null && list.size()>0){
			WxFans oldxFans = list.get(0);
			if(oldxFans.getNickName()==null || "".equals(oldxFans.getNickName())){
				oldxFans.setNickName(record.getNickName());
				oldxFans.setHeadUrl(record.getHeadUrl());
				oldxFans.setHeadPhotoUrl(record.getHeadPhotoUrl());
				return wxFansMapper.updateByPrimaryKeySelective(oldxFans);
			}else{
				return 0;
			}
		}
		return wxFansMapper.insert(record);
	}

	@Override
	public int insertSelective(WxFans record) {
		// TODO Auto-generated method stub
		return wxFansMapper.insertSelective(record);
	}

	@Override
	public WxFans selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return wxFansMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxFans record) {
		// TODO Auto-generated method stub
		return wxFansMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WxFans record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<WxFans> getFansList(WxFans wxFans){
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(wxFans!=null ){
			if(wxFans.getOpenId()!=null && !"".equals(wxFans.getOpenId())){
				criteria.andFieldEqualTo("open_id", wxFans.getOpenId());
			}
		}
		List<WxFans> list = wxFansMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public AddressEditSucessDto updateWxFansAddress(WxFans record) {
		AddressEditSucessDto dto = null;
		try {
			wxFansMapper.updateByPrimaryKeySelective(record);
			dto = new AddressEditSucessDto("success","10000",
					record.getReallyName(),record.getPhone(),record.getDeliveryAddress());
		} catch (Exception e) {
			e.printStackTrace();
			dto = new AddressEditSucessDto("fail","10001","修改失败");
		}
		return dto;
	}

	@Override
	public WxFansQueryDto findWxFansByOpenid(String openid) {
		WxFansQueryDto dto = null;
		try {
			WxFans wxFans = wxFansMapper.findWxFansByOpenid(openid);
			dto = new WxFansQueryDto("success","10000",wxFans);
		} catch (Exception e) {
			e.printStackTrace();
			dto = new WxFansQueryDto("fail","10001","查询失败");
		}
		return dto;
	}

	@Override
	public int updateByOpenId(WxFans record) {
		return wxFansMapper.updateByOpenId(record);
	}


}
