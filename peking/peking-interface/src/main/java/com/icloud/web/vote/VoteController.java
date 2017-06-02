package com.icloud.web.vote;

import java.util.ArrayList;
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
import com.icloud.dto.vo.vote.Config;
import com.icloud.dto.vo.vote.Data;
import com.icloud.dto.vo.vote.OptionsVo;
import com.icloud.dto.vo.vote.VoteModuleVo;
import com.icloud.model.event.EventAllowAccess;
import com.icloud.model.event.EventModule;
import com.icloud.model.user.User;
import com.icloud.model.vote.VoteConfig;
import com.icloud.model.vote.VoteOption;
import com.icloud.model.vote.VoteRecord;
import com.icloud.service.event.EventAllowAccessService;
import com.icloud.service.event.EventModuleService;
import com.icloud.service.user.UserService;
import com.icloud.service.vote.VoteConfigService;
import com.icloud.service.vote.VoteOptionService;
import com.icloud.service.vote.VoteRecordService;
import com.icloud.web.BaseController;

@RestController
public class VoteController  extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private VoteConfigService voteConfigService;
	@Autowired
	private VoteRecordService voteRecordService;
	@Autowired
	private VoteOptionService voteOptionService;
	@Autowired
	private EventModuleService eventModuleService;
	@Autowired
	private EventAllowAccessService eventAllowAccessService;
	
	/**获取投票模块**/
	@RequestMapping("/getVoteModule")
	public String getVoteModule(HttpServletRequest request){
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
			/* 1、查询投票配置模块*/
			VoteConfig configs = voteConfigService.findByKey(moduleId);
			if(null==configs){
				resultObj.put("errCode", "3008");//
				resultObj.put("resultMsg", "模块不存在");
				return pakageJsonp(resultObj);
			}
			
			Data data = new Data();
			Config config = new Config();
			
			config.setStartTime(DateTools.convertDateToString(configs.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
			config.setEndTime(DateTools.convertDateToString(configs.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
			config.setActive(configs.getIsOpen().equals("1")?true:false);
			
			data.setTitle(configs.getTitle());
			data.setDescription(configs.getDescription());
			
			/*2、是否有权限评价*/
			Boolean isAllowVote = false;
			String userType = configs.getUserType();
			Date currentDate = new Date();
			//等于0,默认通过
			if(StringUtil.checkObj(userType) && userType.equals("0")){
				
				if(currentDate.before(configs.getStartTime())||currentDate.after(configs.getEndTime())){
				   isAllowVote = false;
				}else{
				   isAllowVote = true;
				}
			}else{
				if(null!=user.getUserExt() && "1".equals(user.getUserExt().getIsCertification())){
					if(null!=user.getUserExt()){
						if(StringUtil.checkObj(user.getUserExt().getRoleId())){
							if(StringUtil.checkObj(userType)){
								JSONArray isAllowList = JSONArray.parseArray(userType);
								isAllowVote = isAllowList.contains(user.getUserExt().getRoleId());
							    if(isAllowVote){
							    	if(currentDate.before(configs.getStartTime())||currentDate.after(configs.getEndTime())){
										   isAllowVote = false;
										}else{
										   isAllowVote = true;
									}
							    }
							}
						}
					}
				}
			}
			
			config.setAllowVote(isAllowVote);//是否允许当前用户权限下投票。
			
			/*3、我是否已投票（投票记录） t_vote_record */
			VoteRecord voteRecord = new VoteRecord();
			voteRecord.setModuleId(moduleId);
			voteRecord.setVoteUser(user.getId());
			List<VoteRecord> voteRecordList = voteRecordService.getList(voteRecord);
			if(voteRecordList!=null &&voteRecordList.size()>0){
				data.setVotedOptionId(voteRecordList.get(0).getOptionId());
				data.setHasVoted(true);
			}
			
			/*4、查询模块所有选项 ，选项 t_vote_option */
			VoteOption voteOption = new VoteOption();
			voteOption.setConfigId(moduleId);
			List<VoteOption> voteOptionList = voteOptionService.getList(voteOption);
			List<OptionsVo> optionsVoList = new ArrayList<OptionsVo>();
			for (VoteOption vo:voteOptionList) {
				OptionsVo optionsVo = new OptionsVo();
				optionsVo.setOptionId(vo.getId());
				optionsVo.setOptionName(vo.getOptiond());
				optionsVoList.add(optionsVo);
			}
			/*5、查询模块所有用户各个选项的汇总 */
			voteRecord.setVoteUser(null);
			List<VoteRecord> allVoteRecordList = voteRecordService.getList(voteRecord);
			if(optionsVoList!=null && optionsVoList.size()>0){
				for (VoteRecord record:allVoteRecordList) {
					for (int i = 0; i < optionsVoList.size(); i++) {
						OptionsVo optionsVo = optionsVoList.get(i);
						if(record.getOptionId().equals(optionsVo.getOptionId())){
							optionsVo.setCount(optionsVo.getCount()+1);
							optionsVoList.set(i, optionsVo);
						}
					}
				}
			}
			data.setOptions(optionsVoList);
			VoteModuleVo voteModuleVo = new VoteModuleVo();
			voteModuleVo.setConfig(config);
			voteModuleVo.setData(data);
			
			resultObj.put("resultData", JSON.toJSON(voteModuleVo));
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "获取投票模块成功");
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	
	/**投票
	 * @throws Exception **/
	@RequestMapping("/addVote")
	public String addVote(HttpServletRequest request) throws Exception{
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			if(!parameterObj.containsKey("moduleId")||!parameterObj.containsKey("sid") || !parameterObj.containsKey("optionId")){
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
			
			/* 1、查询投票配置模块*/
			VoteConfig config = voteConfigService.findByKey(moduleId);
			if(null==config){
				resultObj.put("errCode", "3008");//
				resultObj.put("resultMsg", "模块不存在");
				return pakageJsonp(resultObj);
			}
			String votedOptionId = parameterObj.getString("optionId");
			/*2、是否有权限评价*/
			Boolean isAllowVote = false;
			String userType = config.getUserType();
			//等于0,默认通过
			if(StringUtil.checkObj(userType) && userType.equals("0")){
				isAllowVote = true;
			}else{
				if(null!=user.getUserExt() && "1".equals(user.getUserExt().getIsCertification())){
					if(null!=user.getUserExt()){
						if(StringUtil.checkObj(user.getUserExt().getRoleId())){
							if(StringUtil.checkObj(userType)){
								JSONArray isAllowList = JSONArray.parseArray(userType);
								isAllowVote = isAllowList.contains(user.getUserExt().getRoleId());
							}
						}
					}
				}
			}
			if(!isAllowVote){
				resultObj.put("errCode", "3002");
				resultObj.put("rusultMsg","没有权限投票");
				return pakageJsonp(resultObj);
			}
			
			Date currentDate = new Date();
			if(currentDate.before(config.getStartTime())||currentDate.after(config.getEndTime())){
				resultObj.put("errCode", "3009");
				resultObj.put("resultMsg","评价时间已过或未到");
				return pakageJsonp(resultObj);
			}
			
			if(config.getIsOpen().equals("0")){
				resultObj.put("errCode", "3006");//用户不存在
				resultObj.put("resultMsg","尚未开启，请耐心等待");
				return pakageJsonp(resultObj);
			}
			
			JSONObject resultData = new JSONObject();
			/*2、是否已投票（投票记录） t_vote_record*/
			VoteRecord voteRecord = new VoteRecord();
			voteRecord.setModuleId(moduleId);
			voteRecord.setVoteUser(user.getId());
			List<VoteRecord> voteRecordList = voteRecordService.getList(voteRecord);
			if(voteRecordList!=null &&voteRecordList.size()>0){
				resultObj.put("errCode","3001");
				resultObj.put("resultMsg", "已经投票");
				return pakageJsonp(resultObj);
			}else{
				voteRecord.setOptionId(votedOptionId);
				voteRecord.setCreateTime(new Date());
				voteRecord.setNick(user.getNick());
				voteRecord.setHeaderImg(user.getWxHeadImg());
				voteRecordService.save(voteRecord);
				resultData.put("voteId", voteRecord.getId());
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "投票成功");
				resultObj.put("resultData", resultData);
				return pakageJsonp(resultObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}

	/** 添加投票配置 
	 * @throws Exception **/
	@RequestMapping("/addVotoConfig")
	public String addVotoConfig(HttpServletRequest request) throws Exception{
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
		String options = parameterObj.getString("options");
		if(StringUtils.isBlank(configStr)){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失options");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		String moduleId = parameterObj.getString("moduleId");
		String moduleType = parameterObj.getString("moduleType");
		JSONObject configJson = JSONObject.parseObject(configStr);
		VoteConfig config = new VoteConfig();
		config.setDescription(configJson.getString("description"));
		config.setTitle(configJson.getString("title"));
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
			config.setUserType("0");
		}else{
			JSONArray array = new JSONArray(); 
			for(EventAllowAccess aa:accessList){
			  array.add(aa.getUserRoleId());
			}
			config.setUserType(array.toJSONString());
		 }
		config.setEventId(eventId);
		if(StringUtils.isBlank(moduleId)){
		    voteConfigService.save(config);
		    moduleId = config.getId();
		    
		    JSONArray optionArray = JSONArray.parseArray(options);
			String optionContent = null;
			VoteOption option = null;
			for(int i=0;null!=optionArray&&i<optionArray.size();i++){
				optionContent = optionArray.getString(i);
				option = new VoteOption();
				option.setEventId(eventId);
				option.setConfigId(config.getId());
				option.setOptiond(optionContent);
				voteOptionService.save(option);
			}
			
			EventModule em = new EventModule();
			em.setEventId(eventId);
			em.setModuleLabel(moduleType);
			eventModuleService.delete(em);
			em.setModuleId(config.getId());
			eventModuleService.save(em);
		}else{
			config.setId(moduleId);
			voteConfigService.update(config);
			voteOptionService.deleteByModule(moduleId);
			JSONArray optionArray = JSONArray.parseArray(options);
			String optionContent = null;
			VoteOption option = null;
			for(int i=0;null!=optionArray&&i<optionArray.size();i++){
				optionContent = optionArray.getString(i);
				option = new VoteOption();
				option.setEventId(eventId);
				option.setConfigId(config.getId());
				option.setOptiond(optionContent);
				voteOptionService.save(option);
			}
			
		}
		JSONObject resultData = new JSONObject();
		resultData.put("moduleId", moduleId);
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg","投票模块添加成功");
		return pakageJsonp(resultObj);
	}
	
}
