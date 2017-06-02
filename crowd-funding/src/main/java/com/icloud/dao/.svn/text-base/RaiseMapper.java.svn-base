package com.icloud.dao;

import java.util.List;
import java.util.Map;

import com.icloud.common.QueryBuilder;
import com.icloud.model.Raise;

public interface RaiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Raise record);

    int insertSelective(Raise record);

    Raise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Raise record);

    int updateByPrimaryKey(Raise record);
	
    long countByExample(QueryBuilder example);

    List<Raise> selectByExample(QueryBuilder example);
    
    List<Raise> selectRaseJoinPrizeRecord(Map<String,Object> map);
    
    List<Raise> selectRaiseForLuckDraw();
    
    List<Raise> findCompletedList(QueryBuilder example);
    
    List<Long> findAllPeriod();

	int selectRaseJoinPrizeRecordCount(Map<String, Object> map);
	
	Raise selectRaiseByOrderId(Long orderId);
}