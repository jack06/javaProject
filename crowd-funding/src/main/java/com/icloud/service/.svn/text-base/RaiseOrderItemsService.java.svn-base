package com.icloud.service;

import java.util.List;

import com.icloud.dto.RaiseOrderDetailDto;
import com.icloud.model.RaiseOrderItems;

public interface RaiseOrderItemsService {
    int deleteByPrimaryKey(Long id);

    int insert(RaiseOrderItems record);

    int insertSelective(RaiseOrderItems record);

    RaiseOrderItems selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RaiseOrderItems record);

    int updateByPrimaryKey(RaiseOrderItems record);
    
    List<RaiseOrderDetailDto> selectOrderDetailByRaiseId(Long id);
}