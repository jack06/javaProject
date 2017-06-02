package com.icloud.dao;

import java.util.List;
import java.util.Map;

import com.icloud.common.QueryBuilder;
import com.icloud.form.RecordForm;
import com.icloud.model.RaiseOrder;
import com.icloud.vo.RecordVo;

public interface RaiseOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RaiseOrder record);

    int insertSelective(RaiseOrder record);

    RaiseOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RaiseOrder record);

    int updateByPrimaryKey(RaiseOrder record);
    
    List<RecordVo> selectRecordByPage(RecordForm recordForm);
    
    
    long countByExample(QueryBuilder example);

    List<RaiseOrder> selectByExample(QueryBuilder example);
    
    Long insertSelectId(RaiseOrder record);
    
    List<RecordVo> selectByRaiseId(Map<String, Object> params);
    
    void updateAlertStatus(Map<String, Object> params);
    
    RaiseOrder getOrderByWxFans(Map<String, Object> params);
}