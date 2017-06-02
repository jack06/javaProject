package com.icloud.dao;

import java.util.List;
import java.util.Map;

import com.icloud.common.QueryBuilder;
import com.icloud.dto.AwardRecordExportExcleDto;
import com.icloud.form.AwardRecordFrom;
import com.icloud.model.AwardRecord;
import com.icloud.vo.AwardRecordVo;

public interface AwardRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AwardRecord record);

    int insertSelective(AwardRecord record);

    AwardRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AwardRecord record);

    int updateByPrimaryKey(AwardRecord record);
    
    List<AwardRecordExportExcleDto> ExportExlceAwardRecord(AwardRecordFrom awardRecordFrom);
    
    List<AwardRecordVo> listByPage(QueryBuilder example);
    
    AwardRecord checkDuplicate(Map<String, Object> params);
    
    List<AwardRecord> getDeliveredRecord();
    
    AwardRecordVo findAwardRecordVoById(Long id);
}