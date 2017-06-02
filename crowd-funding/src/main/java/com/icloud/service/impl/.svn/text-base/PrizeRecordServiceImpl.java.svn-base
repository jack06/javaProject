package com.icloud.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.*;
import com.github.pagehelper.PageHelper;
import com.icloud.config.quartz.job.LuckSendWxModelMsgJob;
import com.icloud.dao.PrizeRecordMapper;
import com.icloud.dao.RaiseOrderItemsMapper;
import com.icloud.dao.RaiseOrderMapper;
import com.icloud.dao.WxFansMapper;
import com.icloud.dto.PrizeRecordEntityDto;
import com.icloud.dto.PrizeRecordListDto;
import com.icloud.dto.RaiseOrderDetailDto;
import com.icloud.dto.UserPrizeInfoDto;
import com.icloud.model.PrizeRecord;
import com.icloud.model.Raise;
import com.icloud.model.WxFans;
import com.icloud.service.PrizeRecordService;
import com.icloud.service.RaiseService;
import com.icloud.util.ConfigUtil;
import com.icloud.util.wx.WxSendModelMessageUtil;


@Service
public class PrizeRecordServiceImpl implements PrizeRecordService{

	@Autowired
	private PrizeRecordMapper prizeRecordMapper;
	@Autowired
	private RaiseService raiseService;
	@Autowired
	private RaiseOrderMapper raiseOrderMapper;
	@Autowired
	private WxFansMapper wxFansMapper;
	@Autowired
	private RaiseOrderItemsMapper roiMapper;
	
	public final static Logger log = LoggerFactory.getLogger(PrizeRecordServiceImpl.class);
	
	
	
	@Override
	public PrizeRecordListDto findPrizeRecord(int pageNum, int pageSize, Long period) {
		pageNum = (pageNum==0?1:pageNum);
		pageSize = (pageSize==0?10:pageSize);
		com.github.pagehelper.Page  pages = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("period", period);
		List<PrizeRecordEntityDto> list = prizeRecordMapper.listByPage(params);
		List<Long> periodList = raiseService.findAllPeriod();
		PrizeRecordListDto dto = new PrizeRecordListDto("success", "10000");
		dto.setPrizeRecordList(list);
		dto.setRaisePeriodList(periodList);
		dto.setCurrentPeriod(period);
		dto.setPageNum(pages.getPageNum());
		dto.setPageSize(pages.getPageSize());
		dto.setTotalCount(Long.valueOf(pages.getTotal()).intValue());
		dto.setTotalPage(pages.getPages());
		
		return dto;
	}

	@Override
	public PrizeRecordListDto exportPrizeRecord(Long period) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("period", period);
		List<PrizeRecordEntityDto> list = prizeRecordMapper.exportPrizeRecord(params);
		PrizeRecordListDto dto = new PrizeRecordListDto("success", "10000");
		dto.setPrizeRecordList(list);
		return dto;
	}
	
	/**
	 * 给中奖用户发送模板消息,
	 * 中奖记录表不记录openId,对此造成3倍性能开销对此...  ...  
	 */
	@Override
	public  PrizeRecord sendMsgToLucker(PrizeRecord pr){
		
		//一.数据准备
		//1.1.通过众筹中奖记录表的用户的openId,-->去粉丝表找到openid.
		String openid = getOPenidByUserId(pr);
		
		log.info("查询得到的openId=="+openid+"/n");
		//System.out.println("新公众号中openId==oREekjnGeLC2nh3tOs-sDpjiB2ns");
		//openid = "oREekjnGeLC2nh3tOs-sDpjiB2ns";
		
		//1.2获取模板消息
		String modelId =ConfigUtil.get("send_msg_to_lucker_modelid");
		//1.3通过众筹id获取众筹数据--已经级联查出来
		//Raise raise = raiseService.selectByPrimaryKey(pr.getOrderId());
		//1.4构造json数据
		//数据封装
		//发送时最终转为字符串的对象-->json.toString()*/		
		JSONObject json = new JSONObject();
			JSONObject data = new JSONObject();
			//发送数据
			//设置开头语
			JSONObject first = new JSONObject();
			first.put("value", "恭喜您小豆夺宝中奖！");// 开头语,默认黑色first.put("color", "#173177");
			
			//活动奖品
			JSONObject keyword1 = new JSONObject();
			keyword1.put("value", pr.getRaise().getProductName());
			
			//开奖时间
			JSONObject keyword2 = new JSONObject();
			keyword2.put("value",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(pr.getRaise().getOutTime()) );
			
			//备注
			JSONObject remark = new JSONObject();
			remark.put("value", "请到真龙服务号【龙粉之家】-【我的乐豆】-【小豆夺宝中心领奖】！");
			
			//数据嵌套
			data.put("first", first);
			data.put("keyword1", keyword1);
			data.put("keyword2", keyword2);
			data.put("remark", remark);
			
			json.put("data", data);	
			json.put("template_id",modelId);
			json.put("touser", openid);	
			json.put("url",ConfigUtil.get("raise_past_url"));
		
		//二.通过OpenId,模板ID,中奖记录==>构建发送模板消息的Json数据 ,调用工具类的发送方法.
		String sendResult="";
		try {
			sendResult = WxSendModelMessageUtil.sendModelMessage(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("发送的结果为"+sendResult+"/n");
		if("0".equals(sendResult)){
			//三.如果发送成功,修改中奖记录表的is_notify=1.
			pr.setIsNotify("1");
			updateMsgSendState(pr);
			return null;
		}else{
			//四.如果发送失败,修改中奖记录表的is_notify=1,并保存如发送失败列表返回发送失败的对象.
			pr.setIsNotify("0");
			updateMsgSendState(pr);
			return pr;
		}	
	}
	@Override
	public String getOPenidByUserId(PrizeRecord pr){
		
		WxFans wxFans = wxFansMapper.selectByPrimaryKey( pr.getUserId() );
		return wxFans.getOpenId();
	}
	@Override
	public void updateMsgSendState(PrizeRecord pr){
		
		int updateCount = prizeRecordMapper.updateMsgSendState(pr);
	}

	@Override
	public PrizeRecordListDto selectLuckyNoByRaiseId(Long raiseId) {
		List<PrizeRecordEntityDto> list = prizeRecordMapper.selectLuckyNoByRaiseId(raiseId);
		PrizeRecordListDto dto = new PrizeRecordListDto("success", "10000");
		dto.setPrizeRecordList(list);
		return dto;
	}

	@Override
	public UserPrizeInfoDto getUserPrizeInfo(Long raiseId, Long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("raiseId", raiseId);
		params.put("userId", userId);
		List<PrizeRecord> list = prizeRecordMapper.getUserPrizeInfo(params);
		List<RaiseOrderDetailDto> orderDetailList = roiMapper.selectForPrizeInfo(params);
		UserPrizeInfoDto dto = new UserPrizeInfoDto("success", "10000");
		if(orderDetailList.size() == 0){
			dto = new UserPrizeInfoDto("fail", "10001", "未参与夺宝！");
			return dto;
		}else{
			if(list.size() == 0){	//未中奖
				dto.setIsPrize(false);
				dto.setLuckyNo(orderDetailList.get(0).getLuckyNo());	//未中奖，从订单中拿该用户的幸运号
			}else{
				dto.setIsPrize(true);
				dto.setLuckyNo(list.get(0).getLuckyNo());	//已中奖，直接从中奖表中拿该用户的幸运号
			}
			if("0".equals(orderDetailList.get(0).getIsAlert()))	//未提示
				dto.setIsAlert(false);
			else
				dto.setIsAlert(true);
			
			params.put("isAlert", "1"); //修改提示状态为已经提示
			raiseOrderMapper.updateAlertStatus(params);	 //更新DB提示状态
		}
		return dto;
	}
	@Override
	public List<PrizeRecord> selectRecordJoinRaise() {
		// TODO Auto-generated method stub
		return prizeRecordMapper.selectRecordJoinRaise();
	}

	@Override
	public List<PrizeRecord> selectRecordByRaiseId(long raiseId) {
		// TODO Auto-generated method stub
		return prizeRecordMapper.selectRecordByRaiseId(raiseId);
	}
}
