package com.icloud.service;

import com.icloud.dto.AwardRecordListDto;
import com.icloud.dto.BaseDto;
import com.icloud.dto.ExportExcleDto;
import com.icloud.form.AwardRecordFrom;
import com.icloud.model.AwardRecord;
import com.icloud.vo.AwardRecordVo;

public interface AwardRecordService {
	/**
	 * 查询出要导出的中奖数据
	 * @return
	 */
	ExportExcleDto ExportExlceAwardRecord(AwardRecordFrom awardRecordFrom);
	
	AwardRecord findAwardRecordById(String id);
	
	/**
	 * 后台分页
	 * @param from
	 * @return
	 */
	AwardRecordListDto listByPage(AwardRecordFrom from);
	
	/**
	 * 通过id查询
	 * @return
	 */
	AwardRecord findByOpenid(String id);
	
	AwardRecordVo findAwardRecordVoById(String id);
	
	BaseDto updateAwardRecord(AwardRecord record);
	
	BaseDto addAwardRecord(AwardRecord record);
	
	BaseDto deleteById(String id);
	
	void getEmsNo();
}
