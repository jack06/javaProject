package com.icloud.dao;

import java.util.List;
import java.util.Map;

import com.icloud.dto.RaiseOrderDetailDto;
import com.icloud.model.RaiseOrderItems;

public interface RaiseOrderItemsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RaiseOrderItems record);

    int insertSelective(RaiseOrderItems record);

    RaiseOrderItems selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RaiseOrderItems record);

    int updateByPrimaryKey(RaiseOrderItems record);
    
    List<RaiseOrderDetailDto> selectOrderDetailByRaiseId(Long id);
    
    List<RaiseOrderDetailDto> selectForPrizeInfo(Map<String, Object> params);
}