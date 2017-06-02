package com.icloud.web.evaluate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.DateTools;
import com.icloud.common.util.StringUtil;
import com.icloud.dto.vo.evaluate.AddEvaluationRecordResultVo;
import com.icloud.dto.vo.evaluate.ConfigVo;
import com.icloud.dto.vo.evaluate.EvaluationModuleVo;
import com.icloud.dto.vo.evaluate.EvaluationVo;
import com.icloud.dto.vo.evaluate.Result;
import com.icloud.model.evaluate.EvaluationConfig;
import com.icloud.model.evaluate.EvaluationRecord;
import com.icloud.model.event.EventAllowAccess;
import com.icloud.model.event.EventModule;
import com.icloud.model.user.User;
import com.icloud.service.evaluate.EvaluationConfigService;
import com.icloud.service.evaluate.EvaluationRecordService;
import com.icloud.service.event.EventAllowAccessService;
import com.icloud.service.event.EventModuleService;
import com.icloud.service.user.UserService;
import com.icloud.web.BaseController;

/**
 * 
 * @author z
 *
 */
@RestController
public class EvaluationController extends BaseController {
	//评价配置service
	@Autowired
	private EvaluationConfigService evaluationConfigService;
	//评价记录service
	@Autowired
	private EvaluationRecordService evaluationRecordService;
	@Autowired
	private UserService userService;
	@Autowired
	private EventModuleService eventModuleService;
	@Autowired
	private EventAllowAccessService eventAllowAccessService;
	
		
	/**获取 评价列表**/
	@RequestMapping("/getEvaluationModule")
	public String  getEvaluationModule(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			
			if(!parameterObj.containsKey("moduleId")||!parameterObj.containsKey("sid")){
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			String sessionKey = parameterObj.getString("sid");
			User user = userService.getUserFromSession(sessionKey);
			if(null==user){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
				return pakageJsonp(resultObj);
			}
			
			String moduleId = parameterObj.getString("moduleId");
			
			/*1、评价配置信息*/
			EvaluationConfig evaluationConfig = evaluationConfigService.findByKey(moduleId);
			if(null==evaluationConfig){
				resultObj.put("errCode", "3008");//
				resultObj.put("resultMsg", "模块不存在");
				return pakageJsonp(resultObj);
			}
			
			EvaluationModuleVo evaluationModuleVo = new EvaluationModuleVo();
			ConfigVo config = new ConfigVo();//配置
			EvaluationVo data = new EvaluationVo();//数据
			
			config.setStartTime(DateTools.convertDateToString(evaluationConfig.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
			config.setEndTime(DateTools.convertDateToString(evaluationConfig.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
			config.setIsActive(evaluationConfig.getIsOpen().equals("1")?true:false);
			String userType = evaluationConfig.getIsAllowType();
			
			/*2、是否有权限评价*/
			Date currentDate = new Date();
			Boolean isAllowEvaluation = false;
			//等于0,默认通过
			if(StringUtil.checkObj(userType) && userType.equals("0")){
				
				if(currentDate.before(evaluationConfig.getStartTime())||currentDate.after(evaluationConfig.getEndTime())){
                    isAllowEvaluation =false;
				}else{
				    isAllowEvaluation = true;
				}
			}else{
				if(null!=user.getUserExt() && "1".equals(user.getUserExt().getIsCertification())){
					if(null!=user.getUserExt()){
						if(StringUtil.checkObj(user.getUserExt().getRoleId())){
							if(StringUtil.checkObj(userType)){
								JSONArray isAllowList = JSONArray.parseArray(userType);
								isAllowEvaluation = isAllowList.contains(user.getUserExt().getRoleId());
							    if(isAllowEvaluation){
							    	if(currentDate.before(evaluationConfig.getStartTime())||currentDate.after(evaluationConfig.getEndTime())){
					                    isAllowEvaluation =false;
									}else{
									    isAllowEvaluation = true;
									}
							    }
							}
						}
					}
				}
			}
			config.setAllowEvaluation(isAllowEvaluation);//当前用户是否有权限
		
			/*3、我的评价记录信息*/
			EvaluationRecord evaluationRecord = new EvaluationRecord();
			evaluationRecord.setModuleId(moduleId);
			evaluationRecord.setEvaluationUser(user.getId());
			List<EvaluationRecord> evaluationRecordList = evaluationRecordService.getList(evaluationRecord);
			if(evaluationRecordList!=null && evaluationRecordList.size()>0){
				evaluationModuleVo.setHasEvaluation(true);
				data.setOption(evaluationRecordList.get(0).getEvaluationLevel());
				data.setContent(evaluationRecordList.get(0).getEvaluationContent());
			}
			
			evaluationModuleVo.setEvaluation(data);
			evaluationModuleVo.setConfig(config);
			resultObj.put("resultData", JSON.toJSON(evaluationModuleVo));
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "获取评价信息成功");
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**提交评价
	 * @throws Exception **/
	@RequestMapping("/addEvaluation")
	public String addEvaluation(HttpServletRequest request) throws Exception{
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			if(!parameterObj.containsKey("moduleId")||!parameterObj.containsKey("sid")){
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			String sessionKey = parameterObj.getString("sid");
			User user = userService.getUserFromSession(sessionKey);
			if(null==user){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
				return pakageJsonp(resultObj);
			}
			if(null==user.getUserExt()||user.getUserExt().getIsCertification().equals("2")){
				resultObj.put("errCode", "0013");
				resultObj.put("resultMsg","用户尚未认证");
				return pakageJsonp(resultObj);
			}
			
			if(user.getUserExt().getIsCertification().equals("0")){
				resultObj.put("errCode", "0020");
				resultObj.put("resultMsg","用户身份正在审核中");
				return pakageJsonp(resultObj);
			}
			
			String moduleId = parameterObj.getString("moduleId");
			/*1、评价配置信息*/
			EvaluationConfig evaluationConfig = evaluationConfigService.findByKey(moduleId);
			if(null==evaluationConfig){
				resultObj.put("errCode", "3008");//用户不存在
				resultObj.put("resultMsg", "模块不存在");
				return pakageJsonp(resultObj);
			}
			
			/*2、是否有权限评价*/
			String userType = evaluationConfig.getIsAllowType();
			Boolean isAllowEvaluation = false;
			//等于0,默认通过
			if(StringUtil.checkObj(userType) && userType.equals("0")){
				isAllowEvaluation = true;
			}else{
				if(null!=user.getUserExt()){
					if(StringUtil.checkObj(user.getUserExt().getRoleId())){
						if(StringUtil.checkObj(userType)){
							JSONArray isAllowList = JSONArray.parseArray(userType);
							isAllowEvaluation = isAllowList.contains(user.getUserExt().getRoleId());
						}
					}
				}
			}
			if(!isAllowEvaluation){
				resultObj.put("errCode", "3002");
				resultObj.put("rusultMsg","没有权限评价");
				return pakageJsonp(resultObj);
			}
			Date currentDate = new Date();
			if(currentDate.before(evaluationConfig.getStartTime())||currentDate.after(evaluationConfig.getEndTime())){
				resultObj.put("errCode", "3009");//用户不存在
				resultObj.put("resultMsg","评价时间已过或未到");
				return pakageJsonp(resultObj);
			}
			
			if(evaluationConfig.getIsOpen().equals("0")){
				resultObj.put("errCode", "3006");//用户不存在
				resultObj.put("resultMsg","尚未开启，请耐心等待");
				return pakageJsonp(resultObj);
			}
			/*3、是否已评价*/
			EvaluationRecord evaluationRecord = new EvaluationRecord();
			evaluationRecord.setModuleId(moduleId);
			evaluationRecord.setEvaluationUser(user.getId());
			List<EvaluationRecord> evaluationRecordList = evaluationRecordService.getList(evaluationRecord);
			if(evaluationRecordList!=null && evaluationRecordList.size()>0){
				resultObj.put("errCode","3001");
				resultObj.put("resultMsg", "已经评价");
				return pakageJsonp(resultObj);
			}
			
			/*4、保存数据*/
			JSONObject evaluationObj = parameterObj.getJSONObject("evaluation");
			Integer option = evaluationObj.getInteger("option");
			String content = evaluationObj.getString("content");
//			EvaluationRecord evaluationRecord = new EvaluationRecord();
			evaluationRecord.setEvaluationLevel(option.toString());
			evaluationRecord.setEvaluationContent(content);
			evaluationRecord.setModuleId(moduleId);
			evaluationRecord.setEventId(evaluationConfig.getEventId());
			evaluationRecord.setNick(user.getNick());
			evaluationRecord.setHeaderImg(user.getWxHeadImg());
			evaluationRecord.setEvaluationUser(user.getId());
			evaluationRecord.setEvaluationTime(new Date());
			evaluationRecordService.save(evaluationRecord);
			
			/*5、获取返回结果*/
			EvaluationRecord query = new EvaluationRecord();
			query.setModuleId(moduleId);
			List<EvaluationRecord> allOptionList = evaluationRecordService.getList(evaluationRecord);
			query.setEvaluationLevel(option.toString());
			List<EvaluationRecord> currentOptionList = evaluationRecordService.getList(evaluationRecord);
			Integer count = 0;
			Integer accounted = 0;
			if(currentOptionList!=null){
				count = currentOptionList.size();
			}
			if(allOptionList!=null && allOptionList.size()>0){
				accounted = count/allOptionList.size()*100;
			}
			AddEvaluationRecordResultVo addResult = new AddEvaluationRecordResultVo();
			addResult.setEvaluationId(evaluationRecord.getId());
			Result result = new Result();
			result.setAccounted(accounted.toString());
			result.setCount(count.toString());
			addResult.setResult(result);
			
			resultObj.put("resultData", JSON.toJSON(addResult));
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "提交评价成功");
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
			resultObj.put("resultData", null);
			resultObj.put("errCode", "1001");
			resultObj.put("resultMsg", "系统内部错误");
			return pakageJsonp(resultObj);
		}
	}
	
	/** 添加评论模块	 */
	@RequestMapping("/addEvaluationConfig")
	public String addEvaluationConfig(HttpServletRequest request) throws Exception{
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventId");
			return pakageJsonp(resultObj);
		}
		if(!parameterObj.containsKey("moduleType")){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失moduleType");
			return pakageJsonp(resultObj);
		}
		String configStr = parameterObj.getString("config");
		if(StringUtils.isBlank(configStr)){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失config");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		String moduleType = parameterObj.getString("moduleType");
		String moduleId = parameterObj.getString("moduleId");
		JSONObject configJson = JSONObject.parseObject(configStr);
		EvaluationConfig config = new EvaluationConfig();
		config.setIsOpen(configJson.getBoolean("isActive")?"1":"0");
		Calendar nowDate = Calendar.getInstance();
		if(StringUtils.isNotBlank(configJson.getString("startTime"))){
		   config.setStartTime(DateTools.str2Date(configJson.getString("startTime"),"yyyy-MM-dd HH:mm:ss"));
		}else{
			config.setStartTime(nowDate.getTime());
		}
		
		if(StringUtils.isNotBlank(configJson.getString("endTime"))){
		   config.setEndTime(DateTools.str2Date(configJson.getString("endTime"),"yyyy-MM-dd HH:mm:ss"));
		}else{
			nowDate.set(Calendar.YEAR, nowDate.get(Calendar.YEAR)+1);
			config.setEndTime(nowDate.getTime());
		}
		
		//取对应事件的权限作为模块的权限
		List<EventAllowAccess> accessList = eventAllowAccessService.findListByEvent(eventId);
		if(null==accessList||accessList.size()<=0){
			config.setIsAllowType("0");
		}else{
			JSONArray array = new JSONArray(); 
			for(EventAllowAccess aa:accessList){
			  array.add(aa.getUserRoleId());
			}
			config.setIsAllowType(array.toJSONString());
		 }
		config.setEventId(eventId);
		
		if(StringUtils.isBlank(moduleId)){
		
		   evaluationConfigService.save(config);
		   
		    EventModule em = new EventModule();
			em.setEventId(eventId);
			em.setModuleLabel(moduleType);
			eventModuleService.delete(em);
			
			em.setModuleId(config.getId());
			eventModuleService.save(em);
			
		    moduleId = config.getId();
		}else{
			config.setId(moduleId);
			evaluationConfigService.update(config);
		}
		
		
		
		JSONObject resultData = new JSONObject();
		resultData.put("moduleId", config.getId());
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg","评价模块添加成功");
		return pakageJsonp(resultObj);
	}
	
}
