package com.icloud.service;

import java.util.List;
import java.util.Map;

import com.icloud.dto.RaiseDetailDto;
import com.icloud.dto.RaiseEntityDto;
import com.icloud.dto.RaiseListDto;
import com.icloud.dto.RaiseQueryDto;
import com.icloud.form.RaiseForm;
import com.icloud.model.Raise;

public interface RaiseService {

	void add(Raise recoud);
	
	void insertSelective(Raise recoud);
	
	RaiseQueryDto listBypPage(RaiseForm raiseForm);
	
	int updateByPrimaryKeySelective(Raise record);
	
	RaiseQueryDto raisePast(Map<String,Object> map);

	Raise selectByPrimaryKey(Long id);
	
	RaiseDetailDto getRaiseById(Long id);

	RaiseEntityDto raiseGetById(long longValue);
	
	void LuckDraw();

	/**
	 * 活动开始和结束状态处理
	 * @author   : zdh
	 * @date     : 创建时间：2017年4月19日 上午11:34:59  
	 * @version  : 1.0  
	 *
	 */
	void raiseStatusJo();

	void deleteByPrimaryKey(long parseLong);
	
	List<Long> findAllPeriod();
	
	RaiseQueryDto findRaiseCompletedList(RaiseForm raiseForm);
	
	/**
	 * 活动中心专用方法
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param raiseForm
	 * @return   :
	 */
	RaiseQueryDto listCenter(RaiseForm raiseForm);
	
	RaiseEntityDto selectRaiseByOrderId(Long orderId);
	
}
