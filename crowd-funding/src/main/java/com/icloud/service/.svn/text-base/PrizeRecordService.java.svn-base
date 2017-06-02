package com.icloud.service;

import java.util.List;

import com.icloud.dto.PrizeRecordListDto;
import com.icloud.dto.UserPrizeInfoDto;
import com.icloud.model.PrizeRecord;

public interface PrizeRecordService {

	PrizeRecordListDto findPrizeRecord(int pageNum, int pageSize, Long period);
	
	PrizeRecordListDto exportPrizeRecord(Long period);
	
	void updateMsgSendState(PrizeRecord pr);

	String getOPenidByUserId(PrizeRecord pr);

	PrizeRecord sendMsgToLucker(PrizeRecord pr);
	
	PrizeRecordListDto selectLuckyNoByRaiseId(Long raiseId);
	
	UserPrizeInfoDto getUserPrizeInfo(Long raiseId, Long userId);

	List<PrizeRecord> selectRecordJoinRaise();

	List<PrizeRecord> selectRecordByRaiseId(long raiseId);
}
