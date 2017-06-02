package com.icloud.service;

import javax.servlet.http.HttpServletRequest;

import com.icloud.dto.BaseDto;
import com.icloud.dto.RaiseOrderItemsDto;
import com.icloud.dto.RaiseOrderListDto;
import com.icloud.dto.RecordDto;
import com.icloud.form.RecordForm;
import com.icloud.model.AwardRecord;
import com.icloud.model.Raise;
import com.icloud.model.RaiseOrder;
import com.icloud.model.WxFans;

public interface RaiseOrderService {
	
	    int deleteByPrimaryKey(Long id);

	    int insert(RaiseOrder record);

	    int insertSelective(RaiseOrder record);

	    RaiseOrder selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(RaiseOrder record);

	    int updateByPrimaryKey(RaiseOrder record);
	    
	    public RecordDto recondList(RecordForm recordForm);
	    
	    long countByRaiseOrder(RaiseOrder record);

		void toOrder(Raise raise, WxFans wxFans);
		
		RaiseOrderListDto findOrderByRaiseId(Long id, int pageNum, String nickName, Long luckyNo);
		
		BaseDto getPrize(AwardRecord awardRecord);
		
		RaiseOrderItemsDto getOrderItemByOrderId(String orderId);
		
		RaiseOrder getOrderByWxFans(Long raiseId, Long userId);
}
