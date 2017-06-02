package com.icloud.web.signup;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.model.event.EventAllowAccess;
import com.icloud.model.event.EventModule;
import com.icloud.model.signup.SignUpConfig;
import com.icloud.model.signup.SignUpRecord;
import com.icloud.model.user.User;
import com.icloud.service.event.EventAllowAccessService;
import com.icloud.service.event.EventModuleService;
import com.icloud.service.signup.SignUpConfigService;
import com.icloud.service.signup.impl.SignUpRecordService;
import com.icloud.service.user.UserService;
import com.icloud.web.BaseController;

@RestController
public class SignUpController extends BaseController {
	@Autowired
	private SignUpConfigService signUpConfigService;
	@Autowired
	private SignUpRecordService signUpRecordService;
	@Autowired
	private UserService userService;
	@Autowired
	private EventModuleService eventModuleService;
	@Autowired
	private EventAllowAccessService eventAllowAccessService;
	/**获取报名详情 **/
	@RequestMapping("/getEnrollModule")
	public String getEnrollModule(HttpServletRequest request) throws Exception{
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
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
		
		JSONObject resultData = new JSONObject();
		String moduleId = parameterObj.getString("moduleId");
		SignUpConfig config = signUpConfigService.findByKey(moduleId);
		
		JSONObject jsonConfig = new JSONObject();
		if(null!=config){
			jsonConfig.put("startTime", DateTools.convertDateToString(config.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
			jsonConfig.put("endTime",  DateTools.convertDateToString(config.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
			jsonConfig.put("model", config.getModel());
			jsonConfig.put("isActive", config.getIsOpen().equals("1")?true:false);
			String isAllowType = config.getIsAllowType();
			Date nowDate = new Date();
			if("0".equals(isAllowType)){
				//0表示不限制評論
				if(nowDate.before(config.getStartTime())||nowDate.after(config.getEndTime())){
					jsonConfig.put("isAllowEnroll",false);
				}else{
			        jsonConfig.put("isAllowEnroll",true);
				}
			}else {
				if(null!=user.getUserExt()&&user.getUserExt().getIsCertification().equals("1")){
					JSONArray isAllowList = JSONArray.parseArray(isAllowType);
					Boolean isAllowComment = isAllowList.contains(user.getUserExt().getRoleId());
					if(isAllowComment){
						if(nowDate.before(config.getStartTime())||nowDate.after(config.getEndTime())){
							jsonConfig.put("isAllowEnroll",false);
						}else{
					        jsonConfig.put("isAllowEnroll",true);
						}
					}
				}else{
					jsonConfig.put("isAllowEnroll",false);
				}
			}
			
		}
		resultData.put("config", jsonConfig);
		SignUpRecord record = new SignUpRecord();
		record.setSignUpConfigId(moduleId);
		PageInfo<SignUpRecord> page = signUpRecordService.findByPage(0, 10, record);
		List<SignUpRecord> list = page.getList();
		JSONObject data = new JSONObject();
		if(null!=list&&list.size()>0){
			JSONArray icons = new JSONArray();
			for(int i=0;i<list.size();i++){
				 record = list.get(i);
				 icons.add(record.getHeaderImg());
			}
			data.put("icons", icons);
		}
		
		
		SignUpRecord userRecord = new SignUpRecord();
		userRecord.setSignUpConfigId(moduleId);
		userRecord.setSignUpUserId(user.getId());
		SignUpRecord signUpRecord = signUpRecordService.findByUser(userRecord);
		data.put("hasEnrolled",null==signUpRecord?false:true);
		
		int totalCount = signUpRecordService.findCount(record);
		data.put("totalCount", totalCount);
		data.put("leftCount",config.getTotalNum()-totalCount);
		resultData.put("data", data);
		
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "报名详情获取成功");
		
		return pakageJsonp(resultObj);
	}
	
	/**报名 
	 * @throws Exception **/
	@RequestMapping("/addEnroll")
	public String addEnroll(HttpServletRequest request) throws Exception{
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
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
		SignUpConfig config = signUpConfigService.findByKey(moduleId);
		if(null!=config){
			if(config.getModel().equals("2")){
			String isAllowType = config.getIsAllowType();
				if(!"0".equals(isAllowType)){
					if(null!=user.getUserExt()&&user.getUserExt().getIsCertification().equals("1")){
						JSONArray isAllowList = JSONArray.parseArray(isAllowType);
						Boolean isAllowComment = isAllowList.contains(user.getUserExt().getRoleId());
					if(!isAllowComment){
						resultObj.put("errCode", "3002");//用户不存在
						resultObj.put("resultMsg","没有报名权限");
						return pakageJsonp(resultObj);
					}
			       }
		      }
			}
			Date nowDate = new Date();
			if(nowDate.before(config.getStartTime())||nowDate.after(config.getEndTime())){
				resultObj.put("errCode", "3005");
				resultObj.put("resultMsg","报名时间已过或尚未开始");
				return pakageJsonp(resultObj);
			}
			
			if(config.getIsOpen().equals("0")){
				resultObj.put("errCode", "3006");
				resultObj.put("resultMsg","报名还未开启，请耐心等待");
				return pakageJsonp(resultObj);
			}
		}else{
			resultObj.put("errCode", "3008");//
			resultObj.put("resultMsg", "模块不存在");
			return pakageJsonp(resultObj);
		}
		
		JSONObject resultData = new JSONObject();
		SignUpRecord record = new SignUpRecord();
		record.setSignUpConfigId(moduleId);
		int count = signUpRecordService.findCount(record);
		if(config.getTotalNum()>0&&config.getTotalNum()<=count){
			resultObj.put("errCode","3000");
			resultObj.put("resultMsg", "当前事件报名人数已满");
			return pakageJsonp(resultObj);
		}
		record.setSignUpUserId(user.getId());
		if(null==signUpRecordService.findByUser(record)){
			/*String name = parameterObj.getString("name");
			String phone = parameterObj.getString("phone");
			String email = parameterObj.getString("email");*/
			record.setSignUpConfigId(moduleId);
			record.setCreateTime(new Date());
			record.setNick(user.getNick());
			if(config.getModel().equals("2")){
				record.setPhone(user.getUserExt().getPhone());
				record.setEmail(user.getUserExt().getEmail());
				record.setName(user.getUserExt().getRealName());
			}
			record.setHeaderImg(user.getWxHeadImg());
			record.setSignUpUserId(user.getId());
		
			signUpRecordService.save(record);
			resultData.put("enrollId", record.getId());
			resultData.put("icon",record.getHeaderImg());
			resultObj.put("errCode", "0000");
			
			resultObj.put("resultMsg", "报名成功");
			resultObj.put("resultData", resultData);
			return pakageJsonp(resultObj);
		}else{
			resultObj.put("errCode","3001");
			resultObj.put("resultMsg", "已经报名过了，无需重复报名");
			return pakageJsonp(resultObj);
		}
		
	}
	
	/**新增报名配置 
	 * @throws Exception **/
	@RequestMapping("/addEnrolModulConfig")
	public String addEnrolModulConfig(HttpServletRequest request) throws Exception{

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
		JSONObject configJSON = JSONObject.parseObject(configStr);
		
		String eventId = parameterObj.getString("eventId");
		
		String moduleType = parameterObj.getString("moduleType");
		String moduleId = parameterObj.getString("moduleId");
		Boolean isActive  = configJSON.getBoolean("isActive");
		String startTime = configJSON.getString("startTime");
		String endTime = parameterObj.getString("endTime");
		String model = configJSON.getString("model");
		String totalCount = configJSON.getString("totalCount");
		
		
		SignUpConfig config = new SignUpConfig();
		
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
		
		Calendar nowDate = Calendar.getInstance();
		if(StringUtils.isNotBlank(startTime)){
			config.setStartTime(DateTools.str2Date(startTime,"yyyy-MM-dd HH:mm:ss"));
		}else{
			config.setStartTime(nowDate.getTime());
		}
		config.setEndTime(DateTools.str2Date(endTime,"yyyy-MM-dd HH:mm:ss"));
		if(StringUtils.isNotBlank(endTime)){
			config.setEndTime(DateTools.str2Date(endTime,"yyyy-MM-dd HH:mm:ss"));
		}else{
			nowDate.set(Calendar.YEAR, nowDate.get(Calendar.YEAR)+1);
			config.setEndTime(nowDate.getTime());
		}
		
		config.setEventId(eventId);
		
		config.setIsOpen(isActive?"1":"0");
		
		config.setModel(model);
		config.setTotalNum(Integer.parseInt(totalCount));
        if(StringUtils.isBlank(moduleId)){		
		
		    signUpConfigService.save(config);
		    moduleId = config.getId();
		    
		    EventModule em = new EventModule();
			em.setEventId(eventId);
			em.setModuleLabel(moduleType);
			eventModuleService.delete(em);
			em.setModuleId(config.getId());
			eventModuleService.save(em);
        }else{
        	config.setId(moduleId);
        	signUpConfigService.update(config);
        }
		
    	JSONObject resultData = new JSONObject();
		resultData.put("moduleId", moduleId);
		resultObj.put("resultData", resultData);
		
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg","报名模块添加成功");
		return pakageJsonp(resultObj);
	}
	
}
