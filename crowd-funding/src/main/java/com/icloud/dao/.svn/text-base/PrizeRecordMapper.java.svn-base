package com.icloud.dao;

import java.util.List;
import java.util.Map;

import com.icloud.dto.PrizeRecordEntityDto;
import com.icloud.model.PrizeRecord;

public interface PrizeRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrizeRecord record);

    int insertSelective(PrizeRecord record);

    PrizeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrizeRecord record);

    int updateByPrimaryKey(PrizeRecord record);
    
    List<PrizeRecord> findByRecordId(Long raiseId);
    
    List<PrizeRecordEntityDto> listByPage(Map<String, Object> params);
    
    List<PrizeRecordEntityDto> exportPrizeRecord(Map<String, Object> params);

	int updateMsgSendState(PrizeRecord pr);
	
	List<PrizeRecordEntityDto> selectLuckyNoByRaiseId(Long raiseId);
	
	List<PrizeRecord> getUserPrizeInfo(Map<String, Object> params);

	List<PrizeRecord> selectRecordJoinRaise();

	List<PrizeRecord> selectRecordByRaiseId(long raiseId);
	
}