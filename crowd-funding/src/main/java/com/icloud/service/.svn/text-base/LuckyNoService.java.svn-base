package com.icloud.service;

import java.util.List;
import com.icloud.common.QueryBuilder;
import com.icloud.dto.LuckyNoListDto;
import com.icloud.model.LuckyNo;

public interface LuckyNoService {

    int deleteByPrimaryKey(Long id);

    int insert(LuckyNo record);

    int insertSelective(LuckyNo record);

    LuckyNo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LuckyNo record);

    int updateByPrimaryKey(LuckyNo record);
    
    List<LuckyNo> selectByLuckyNo(LuckyNo record);
    
    Long selectMaxLuckyNo(Long raiseId);
    
    LuckyNoListDto selectByRaiseId(Long id);
}
