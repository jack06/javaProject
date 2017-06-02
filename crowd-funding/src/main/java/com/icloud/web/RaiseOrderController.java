package com.icloud.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icloud.dto.BaseDto;
import com.icloud.dto.RaiseOrderItemsDto;
import com.icloud.dto.RaiseOrderListDto;
import com.icloud.form.RecordForm;
import com.icloud.model.AwardRecord;
import com.icloud.model.Raise;
import com.icloud.model.RaiseOrder;
import com.icloud.model.WxFans;
import com.icloud.service.RaiseOrderService;
import com.icloud.service.RaiseService;
import com.icloud.service.WxFansService;
import com.icloud.util.ConfigUtil;
import com.icloud.util.DateUtils;
import com.icloud.util.MD5Util;
import com.icloud.util.RequestUtil;
import com.icloud.util.ThreeDES;


@RestController
@RequestMapping(value = "/raise")
public class RaiseOrderController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private RaiseService raiseService;
	@Autowired
	private RaiseOrderService raiseOrderService;
	@Autowired
	private WxFansService wxFansService;
 
	/**下单**/
	@RequestMapping(value = "/order")
	 public void listByPage(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
		logger.error("jsonText=="+jsonText);
		RecordForm recordForm = new 	RecordForm(); 
		BaseDto  baseDto = null;
		
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				recordForm = JSON.parseObject(jsonText,RecordForm.class);
			}
			//参数、签名验证
			if(recordForm.getEncryOpenid()==null || "".equals(recordForm.getEncryOpenid())
					|| recordForm.getSign()==null || "".equals(recordForm.getSign()) 
					|| recordForm.getRaiseId()==null){
				 baseDto = new BaseDto("fail","10001","参数不正确");
				 outObject(JSONObject.toJSONString(baseDto));
				 return;
			}
			String newsign = MD5Util.encode2hex(recordForm.getEncryOpenid());
			if(!newsign.equals(recordForm.getSign())){
				 baseDto = new BaseDto("fail","10001","参数不正确签名不正确");
				 outObject(JSONObject.toJSONString(baseDto));
				 return;
			}
			 String openid = new String(ThreeDES.decode(recordForm.getEncryOpenid()),"UTF-8");
		
			//1、查询活动,判断是否在活动期间
			Raise raise = raiseService.selectByPrimaryKey(recordForm.getRaiseId());
			if(raise==null){
				 baseDto = new BaseDto("fail","10001","找不到活动");
				 outObject(JSONObject.toJSONString(baseDto));
				 return;
			}
			if(!"1".equals(raise.getCurrentStatus())){
				 baseDto = new BaseDto("fail","10001","活动不可参与");
				 outObject(JSONObject.toJSONString(baseDto));
				 return;
			}
			if(!DateUtils.isInTheTimeRange(raise.getStartDate(), raise.getEndDate(), new Date())){
				 baseDto = new BaseDto("fail","10001","现在不是活动时间");
				 outObject(JSONObject.toJSONString(baseDto));
				 return;
			}
	    	//2、查询用户,判断乐豆是否足够下单
			WxFans wxFans = new WxFans();
			wxFans.setOpenId(openid);
			List<WxFans> wxFanslist = wxFansService.getFansList(wxFans);
			if(wxFanslist==null || wxFanslist.size()<=0){
				 baseDto = new BaseDto("fail","10001","用户不存在");
				 outObject(JSONObject.toJSONString(baseDto));
				 return;
			}
			wxFans = wxFanslist.get(0);
			if(raise.getEachTotal()>wxFans.getSmokeBean()){
				 baseDto = new BaseDto("fail","10001","乐豆数量不足 ,差"+(raise.getEachTotal()-wxFans.getSmokeBean()+"乐豆"));
				 outObject(JSONObject.toJSONString(baseDto));
				 return;
			}
	    	//3、查询下单记录,判断用户是否还有下单机会
			RaiseOrder raiseOrder = new RaiseOrder();
			raiseOrder.setUserId(wxFans.getId());
			raiseOrder.setRaiseId(raise.getId());
			Long mycount = raiseOrderService.countByRaiseOrder(raiseOrder);
			logger.error("mycount="+mycount);
			if(mycount!=null && mycount>=raise.getMaxSare()){
				 baseDto = new BaseDto("fail","10001","你已参与活动，不能再参与");
				 outObject(JSONObject.toJSONString(baseDto));
				 return;
			}
			synchronized(RaiseOrderController.class){
				//等于-1 不限制抽奖人数
//				if(raise.getTotalShare()!=-1){
				long total_share = Long.valueOf((ConfigUtil.get("total_share").toString()));
				logger.error("total_share="+total_share+"&total_share==-1"+(total_share!=-1));
				if(total_share!=-1l){
					raiseOrder.setUserId(null);
					Long totalcount = raiseOrderService.countByRaiseOrder(raiseOrder);
					logger.error("totalcount="+totalcount);
					if(totalcount!=null && totalcount>=total_share){
						 baseDto = new BaseDto("fail","10001","参与人数已满额，不能再参与");
						 outObject(JSONObject.toJSONString(baseDto));
						 return;
					}
				}
				//下单
				raiseOrderService.toOrder(raise,wxFans);
			}
			baseDto = new BaseDto("success","10000","参与成功");
			outObject(JSONObject.toJSONString(baseDto));
		} catch (Exception e) {
		   	 e.printStackTrace();
		}
		
		baseDto = new BaseDto("fail","10001","参与失败");
		outObject(JSONObject.toJSONString(baseDto));
		return;
    }
	
	/**
	 * 查询众筹订单列表
	 */
	@RequestMapping(value = "/findOrderByRaiseId")
	public void findOrderByRaiseId(HttpServletRequest request) throws Exception {
		RaiseOrderListDto dto = null;
		try {
			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
			dto = raiseOrderService.findOrderByRaiseId(jsonObj.getLongValue("id"), jsonObj.getIntValue("pageNum"), jsonObj.getString("nickName"), jsonObj.getLong("luckyNo"));
		} catch (Exception e) {
			e.printStackTrace();
			dto = new RaiseOrderListDto("fail", "10001", null);
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		String json = JSONObject.toJSONString(dto);
		logger.error(json);
		outObject(json);
	}
	
	/**
	 * 领奖
	 */
	@RequestMapping(value = "/getPrize")
	public void getPrize(HttpServletRequest request) throws Exception {
		BaseDto dto = null;
		try {
			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
			AwardRecord awardRecord = new AwardRecord();
			awardRecord.setRaiseId(jsonObj.getLong("raiseId"));
			awardRecord.setOrderId(jsonObj.getLong("orderId"));
			awardRecord.setUserId(jsonObj.getLong("userId"));
			awardRecord.setUserNick(jsonObj.getString("userName"));
			awardRecord.setDeliveryAddress(jsonObj.getString("deliveryAddress"));
			awardRecord.setDeliveryPhone(jsonObj.getString("phone"));
			dto = raiseOrderService.getPrize(awardRecord);
		} catch (Exception e) {
			e.printStackTrace();
			dto = new BaseDto("fail", "10001", null);
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		String json = JSONObject.toJSONString(dto);
		logger.error(json);
		outObject(json);
	}
	/**
	 * 通过orderId查询众筹订单详细项
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrderByOrderId",method = { RequestMethod.GET , RequestMethod.POST })
	public void getOrderItemByOrderId(HttpServletRequest request) throws Exception {
		String orderId = RequestUtil.readPostContent(request);
		logger.error("--------getOrderItemByOrderId---参数="+orderId+"-------");
		RaiseOrderItemsDto dto = null;
		
		dto = raiseOrderService.getOrderItemByOrderId(orderId);
		
		String json = JSONObject.toJSONString(dto);
		outObject(json);
	}
	
	
}