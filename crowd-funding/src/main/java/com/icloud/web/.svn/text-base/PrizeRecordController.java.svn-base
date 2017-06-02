package com.icloud.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icloud.dto.PrizeRecordListDto;
import com.icloud.dto.UserPrizeInfoDto;
import com.icloud.model.PrizeRecord;
import com.icloud.model.WxFans;
import com.icloud.service.PrizeRecordService;
import com.icloud.service.WxFansService;
import com.icloud.util.RequestUtil;

@RestController
@RequestMapping(value="/prizeRecord")
public class PrizeRecordController extends BaseController{

	@Autowired
	private PrizeRecordService prizeRecordService;
	@Autowired
	private WxFansService wxFansService;
	
	/**
     * 开奖列表
     */
    @RequestMapping(value = { "prizeRecordList" }, method = RequestMethod.POST)
	public void prizeRecordList(HttpServletRequest request, HttpServletResponse response){
    	PrizeRecordListDto dto = null;
		try {
			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
			dto = prizeRecordService.findPrizeRecord(jsonObj.getIntValue("pageNum"), jsonObj.getIntValue("pageSize"), jsonObj.getLong("period"));
		} catch (Exception e) {
			e.printStackTrace();
			dto = new PrizeRecordListDto("fail", "10001", null);
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		String json = JSONObject.toJSONString(dto);
		System.out.println(json);
		outObject(json);
    }
    
    /**
     * 获取开奖列表，用于导出excel
     */
    @RequestMapping(value = { "exportPrizeRecord" }, method = RequestMethod.POST)
	public void exportPrizeRecord(HttpServletRequest request, HttpServletResponse response){
    	PrizeRecordListDto dto = null;
		try {
			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
			dto = prizeRecordService.exportPrizeRecord(jsonObj.getLong("period"));
		} catch (Exception e) {
			e.printStackTrace();
			dto = new PrizeRecordListDto("fail", "10001", null);
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		String json = JSONObject.toJSONString(dto);
		System.out.println(json);
		outObject(json);
    }
    
    /**
     * 获取中奖号码列表
     */
    @RequestMapping(value = { "getLuckyNoList" }, method = RequestMethod.POST)
   	public void getLuckyNoList(HttpServletRequest request, HttpServletResponse response){
    	PrizeRecordListDto dto = null;
   		try {
   			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
   			dto = prizeRecordService.selectLuckyNoByRaiseId(jsonObj.getLong("raiseId"));
   		} catch (Exception e) {
   			e.printStackTrace();
   			dto = new PrizeRecordListDto("fail", "10001", null);
   			dto.setResultCode("10001");
   			dto.setResultType("fail");
   			dto.setErrorMsg("查询失败");
   		}
   		String json = JSONObject.toJSONString(dto);
   		System.out.println(json);
   		outObject(json);
    }
    
    /**
	 * 获取用户中奖信息
	 */
	@RequestMapping(value = "/getUserPrizeInfo")
	public void getUserPrizeInfo(HttpServletRequest request) throws Exception {
		UserPrizeInfoDto dto = null;
		try{
			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
			WxFans wxFans = new WxFans();
			wxFans.setOpenId(jsonObj.getString("openId"));
			List<WxFans> wxFanslist = wxFansService.getFansList(wxFans);
			if(wxFanslist==null || wxFanslist.size()<=0){
				dto = new UserPrizeInfoDto("fail","10001","用户不存在");
				outObject(JSONObject.toJSONString(dto));
				return;
			}
			wxFans = wxFanslist.get(0);
			Long userId = wxFans.getId();
			dto = prizeRecordService.getUserPrizeInfo(jsonObj.getLong("raiseId"), userId);
		}catch(Exception ex){
			dto = new UserPrizeInfoDto("fail", "10001", "查询失败");
		}
		String json = JSONObject.toJSONString(dto);
   		System.out.println(json);
   		outObject(json);
	}
    /**
	 * 众筹id对应的所有开奖信息
	 */
	@RequestMapping(value = "/prizelistAjax")
	public void prizelistAjax(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
		List<PrizeRecord> dto = prizeRecordService.selectRecordByRaiseId(Long.parseLong(jsonText));
		String json = JSONObject.toJSONString(dto);
		outObject(json);
	}	
}
