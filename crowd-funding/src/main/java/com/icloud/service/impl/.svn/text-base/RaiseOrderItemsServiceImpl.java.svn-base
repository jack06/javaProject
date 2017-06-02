package com.icloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.RaiseOrderItemsMapper;
import com.icloud.dto.RaiseOrderDetailDto;
import com.icloud.model.RaiseOrderItems;
import com.icloud.service.RaiseOrderItemsService;
@Service
public class RaiseOrderItemsServiceImpl implements RaiseOrderItemsService{


	@Autowired
	private RaiseOrderItemsMapper raiseOrderItemsMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return raiseOrderItemsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RaiseOrderItems record) {
		// TODO Auto-generated method stub
		return raiseOrderItemsMapper.insert(record);
	}

	@Override
	public int insertSelective(RaiseOrderItems record) {
		// TODO Auto-generated method stub
		return raiseOrderItemsMapper.insertSelective(record);
	}

	@Override
	public RaiseOrderItems selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return raiseOrderItemsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RaiseOrderItems record) {
		// TODO Auto-generated method stub
		return raiseOrderItemsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RaiseOrderItems record) {
		// TODO Auto-generated method stub
		return raiseOrderItemsMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RaiseOrderDetailDto> selectOrderDetailByRaiseId(Long id) {
		return raiseOrderItemsMapper.selectOrderDetailByRaiseId(id);
	}
 
}