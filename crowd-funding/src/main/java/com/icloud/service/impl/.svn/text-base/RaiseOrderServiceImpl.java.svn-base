package com.icloud.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.dao.AwardRecordMapper;
import com.icloud.dao.RaiseMapper;
import com.icloud.dao.RaiseOrderMapper;
import com.icloud.dto.BaseDto;
import com.icloud.dto.RaiseOrderItemsDto;
import com.icloud.dto.RaiseOrderListDto;
import com.icloud.dto.RecordDto;
import com.icloud.form.RecordForm;
import com.icloud.model.AwardRecord;
import com.icloud.model.LuckyNo;
import com.icloud.model.Raise;
import com.icloud.model.RaiseOrder;
import com.icloud.model.RaiseOrderItems;
import com.icloud.model.WxFans;
import com.icloud.service.LuckyNoService;
import com.icloud.service.RaiseOrderItemsService;
import com.icloud.service.RaiseOrderService;
import com.icloud.service.WxFansService;
import com.icloud.util.ConfigUtil;
import com.icloud.vo.RecordVo;

/**
 * 
 * 类名称: RaiseOrderServiceImpl
 * 类描述: 众筹订单service
 * 创建人: zdh
 * 创建时间:2017年4月14日 下午6:15:44
 */
@Service
public class RaiseOrderServiceImpl implements RaiseOrderService{

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RaiseOrderMapper raiseOrderMapper;
	@Autowired
	private WxFansService wxFansService;
	@Autowired
	private LuckyNoService luckyNoService;
	@Autowired
	private RaiseOrderItemsService raiseOrderItemsService;
	@Autowired
	private RaiseMapper raiseMapper;
	@Autowired
	private AwardRecordMapper awardRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return raiseOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RaiseOrder record) {
		// TODO Auto-generated method stub
		return raiseOrderMapper.insert(record);
	}

	@Override
	public int insertSelective(RaiseOrder record) {
		// TODO Auto-generated method stub
		return raiseOrderMapper.insert(record);
	}

	@Override
	public RaiseOrder selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return raiseOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RaiseOrder record) {
		// TODO Auto-generated method stub
		return raiseOrderMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(RaiseOrder record) {
		// TODO Auto-generated method stub
		return raiseOrderMapper.updateByPrimaryKey(record);
	}

	
//	/**
//	 * 参与的用户记录
//	 * 
//	 * 我参与过的记录
//	 * @author   : zdh
//	 * @date     : 创建时间：2017年4月14日 下午6:20:05  
//	 * @version  : 1.0  
//	 */
//	public void recondList(RecordForm recordForm){
//		//openid=userId,订单表（T_RAISE_ORDER）、中奖表（T_PRIZE_RECORD）、活动表（T_ZL_RAISE）、粉丝表（ zl_wx_fans）
//		
//	
//		
//		com.github.pagehelper.Page  pages = PageHelper.startPage(recordForm.getPageNum(),recordForm.getPageSize());
//		String sql = "select raiseOrderItem.lucky_no luckyNo,raiseOrderItem.create_date createDate"
//				+ " zlRaise.raise_Name raiseName,zlRaise.current_Period currentPeriod,zlRaise.product_Name"
//				+ " zlRaise.prize_Amount prizeAmount,zlRaise.current_Status currentStatus,"
//				+ " raiseOrder.user_id userId,NVL(prizeRecord.lucky_no,0) winStatus,"
//				+ " wxFans.phone,wxFans.nick_name nickName,wxFans.head_photo_url headPhotoUrl"
//				+ " from T_RAISE_ORDER_ITEMS raiseOrderItem left join T_RAISE_ORDER raiseOrder on raiseOrderItem.order_id=raiseOrder.id"
//				+ " left join T_PRIZE_RECORD prizeRecord on raiseOrderItem.order_id=prizeRecord.order_id"
//				+ " left join T_ZL_RAISE zlRaise on raiseOrderItem.raise_id= zlRaise.id"
//				+ " left join zl_wx_fans wxFans on raiseOrderItem.user_id = wxFans.openid"
//				+"  where raiseOrderItem.raise_id="+recordForm.getRaiseId(); 
//		if(recordForm.getOpenid()!=null && !"".equals(recordForm.getOpenid().trim())){
//			sql+= "  and raiseOrderItem.raise_id="+recordForm.getRaiseId(); 
//		}
//		if(recordForm.getOpenid()!=null && !"".equals(recordForm.getOpenid().trim())){
//			sql+= " and raiseOrderItem.user_id="+recordForm.getOpenid().trim(); 
//		}
//		logger.warn("sql="+sql);
//	}
	
	
	
	/**
	 * 参与的用户记录
	 * 我参与过的记录
	 * @author   : zdh
	 * @date     : 创建时间：2017年4月14日 下午6:20:05  
	 * @version  : 1.0  
	 */
	@SuppressWarnings("rawtypes")
	public RecordDto recondList(RecordForm recordForm){
		//openid=userId,订单表（T_RAISE_ORDER）、订单明细表（T_RAISE_ORDER_ITEMS）、中奖表（T_PRIZE_RECORD）、活动表（T_ZL_RAISE）、粉丝表（ zl_wx_fans）
		com.github.pagehelper.Page  pages = PageHelper.startPage(recordForm.getPageNum(),recordForm.getPageSize());
		List<RecordVo> list = raiseOrderMapper.selectRecordByPage(recordForm);
		System.out.println("list.size="+list.size());
		RecordDto dto = new RecordDto("success","10000",list);
		dto.setPageNum(pages.getPageNum());
	    dto.setPageSize(pages.getPageSize()); 
	    dto.setTotalCount(Long.valueOf(pages.getTotal()).intValue());
	    dto.setTotalPage(Long.valueOf(pages.getPages()));
		dto.setRecordVoList(list);
		return dto;
	}

	/**
	 * 查询订单数
	 */
	@Override
	public long countByRaiseOrder(RaiseOrder record) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(record!=null){
			if(record.getUserId()!=null){
				criteria.andFieldEqualTo("user_id", record.getUserId());
			}
			if(record.getRaiseId()!=null){
				criteria.andFieldEqualTo("raise_id", record.getRaiseId());
			}
		}
		return raiseOrderMapper.countByExample(example);
	}

	/**
	 * 下单
	 */
	@Override
	@Transactional
	public void toOrder(Raise raise, WxFans wxFans) {
		
		//订单
		RaiseOrder raiseOrder = new RaiseOrder();
		raiseOrder.setRaiseId(raise.getId());
		System.out.println("getId="+raise.getId());
		raiseOrder.setUserId(wxFans.getId());
		raiseOrder.setUserNick(wxFans.getNickName());
		raiseOrder.setTotalLedou(raise.getEachTotal());
		raiseOrder.setCreateDate(new Date());
		Long orderId = raiseOrderMapper.insertSelectId(raiseOrder);
		System.out.println("orderId="+orderId);
		//抽奖号码
		LuckyNo luckyNo = new LuckyNo();
		luckyNo.setRaiseId(raise.getId());
		Long currentNo = luckyNoService.selectMaxLuckyNo(raise.getId());
		if(currentNo==null){
			currentNo = Long.valueOf(ConfigUtil.get("luckyNo"));
			luckyNo.setCurrentNo(currentNo);
			luckyNoService.insert(luckyNo);
		}else{
			currentNo = currentNo + 1;
			luckyNo.setCurrentNo(currentNo);
			luckyNoService.insert(luckyNo);
		}
		//订单明细
		RaiseOrderItems raiseOrderItems = new RaiseOrderItems();
		raiseOrderItems.setOrderId(orderId);
		raiseOrderItems.setRaiseId(raise.getId());
		raiseOrderItems.setUserId(wxFans.getId());
		raiseOrderItems.setCreateDate(new Date());
		raiseOrderItems.setLuckyNo(currentNo);
		raiseOrderItems.setOrderId(raiseOrder.getId());
		raiseOrderItemsService.insert(raiseOrderItems);
		//扣减乐豆
		long let = wxFans.getSmokeBean().intValue()-raise.getEachTotal().intValue();
		logger.error("let==================="+let);
		wxFans.setSmokeBean(let);
		logger.error("wxFans.getSmokeBean()==================="+wxFans.getSmokeBean());
		wxFansService.updateByOpenId(wxFans);
	}
	
	@Override
	public RaiseOrderListDto findOrderByRaiseId(Long id, int pageNum, String nickName, Long luckyNo) {
		pageNum = (pageNum==0?1:pageNum);
		
		int pageSize = 10;
		String pageSizeStr = null;
		try{
			pageSizeStr = ConfigUtil.get("default_page_size");
			pageSize = Integer.parseInt(pageSizeStr);
		}catch(Exception ex){
			logger.error("findOrderByRaiseId page size=["+pageSizeStr+"]", ex);
		}
		Page pages = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("nickName", nickName);
		params.put("luckyNo", "".equals(luckyNo)?null:luckyNo);
		List<RecordVo> list = raiseOrderMapper.selectByRaiseId(params);
		RaiseOrderListDto dto = new RaiseOrderListDto("success", "10000");
		dto.setRaiseVoList(list);
		dto.setPageNum(pages.getPageNum());
		dto.setPageSize(pages.getPageSize());
		dto.setTotalCount(Long.valueOf(pages.getTotal()).intValue());
		dto.setTotalPage(pages.getPages());
		return dto;
	}

	@Override
	public BaseDto getPrize(AwardRecord awardRecord) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("raiseId", awardRecord.getRaiseId());
		params.put("orderId", awardRecord.getOrderId());
		params.put("userId", awardRecord.getUserId());
		AwardRecord ar = awardRecordMapper.checkDuplicate(params);	//检查是否重复领取
		if(null != ar){
			return new BaseDto("fail", "10001");
		}
		Raise raise = raiseMapper.selectByPrimaryKey(awardRecord.getRaiseId());
		awardRecord.setProductName(raise.getProductName());
		awardRecord.setCreateDate(new Date());
		awardRecord.setDeliveryStatus("0");
		awardRecordMapper.insert(awardRecord);
		return new BaseDto("success", "10000");
	}

	@Override
	public RaiseOrderItemsDto getOrderItemByOrderId(String orderId) {
		RaiseOrderItemsDto dto = null;
		try {
			RaiseOrder raiseOrder = raiseOrderMapper.selectByPrimaryKey(Long.valueOf(orderId));
			dto = new RaiseOrderItemsDto("success", "10000",raiseOrder);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			dto = new RaiseOrderItemsDto("fail", "10001","查询众筹订单失败");
		}
		return dto;
	}

	@Override
	public RaiseOrder getOrderByWxFans(Long raiseId, Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("raiseId", raiseId);
		params.put("userId", userId);
		return raiseOrderMapper.getOrderByWxFans(params);
	}
}
