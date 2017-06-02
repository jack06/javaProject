package com.icloud.web.signup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.common.ResponseUtils;
import com.icloud.model.signup.SignUpConfig;
import com.icloud.model.signup.SignUpRecord;
import com.icloud.model.staffmanage.Tuserrole;
import com.icloud.service.signup.SignUpConfigService;
import com.icloud.service.signup.SignUpRecordService;
import com.icloud.service.staffmanage.StaffRoleManageService;

@Controller
public class SignUpController {
	
	@Autowired
	private SignUpConfigService signUpConfigService;
	@Autowired
    private StaffRoleManageService staffRoleManageService;	
	@Autowired
	private SignUpRecordService signUpRecordService;
	@RequestMapping("/admin/signup")
	public String openSignUp(){
		return "/signup/signup";
	}
	
	@RequestMapping("/admin/signup_config")
	public String getSignUpConfig(HttpServletRequest request,HttpServletResponse response){
		String eventId = request.getParameter("eventId");
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isBlank(eventId)){
			resultObj.put("errCode","0001");
			resultObj.put("resultMsg","事件ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		SignUpConfig config = new SignUpConfig();
		config.setEventId(eventId);
		List<SignUpConfig> list = signUpConfigService.findForList(config);
		if(null==list||list.size()<=0){
			resultObj.put("errCode","0002");
			resultObj.put("resultMsg","该事件未配置报名模块");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		config = list.get(0);
		JSONObject configJson = new JSONObject();
		configJson.put("startTime", DateTools.convertDateToString(config.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		configJson.put("endTime", DateTools.convertDateToString(config.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		configJson.put("status", config.getIsOpen().equals("0")?"关闭":"开启");
		configJson.put("totalNum", config.getTotalNum());
		configJson.put("id", config.getId());
		resultObj.put("config", configJson);
		if(!config.getIsAllowType().equals("0")){
		    JSONArray array =JSONArray.parseArray(config.getIsAllowType());
		    String[] ids = new String[array.size()];
		    for(int i=0;i<array.size();i++){
		    	ids[i] = array.getString(i);
		    }
		    List<Tuserrole> rolelist = staffRoleManageService.selectRoleList(ids);
		    resultObj.put("roles", rolelist);
		}else{
			resultObj.put("roles","0");
		}
		SignUpRecord record = new SignUpRecord();
		record.setSignUpConfigId(config.getId());
		PageInfo<SignUpRecord> page = signUpRecordService.findForList(1,10, record);
		List<SignUpRecord> relist = page.getList();
		JSONArray alist = new JSONArray();
		for(int i=0;null!=relist&&i<relist.size();i++){
			JSONObject e = new JSONObject();
			e.put("nick", relist.get(i).getNick());
			e.put("headerImg", relist.get(i).getHeaderImg());
			e.put("createDate", DateTools.convertDateToString(relist.get(i).getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
			e.put("name", relist.get(i).getName());
			alist.add(e);
		}
		
		
		resultObj.put("list",alist);
		resultObj.put("pages", page.getPages());
		resultObj.put("errCode","0000");
		resultObj.put("resultMsg","获取事件报名配置成功");
		ResponseUtils.renderJson(response, resultObj.toJSONString());
		return null;
	}
	
	/**获取记录**/
	@RequestMapping("/admin/sign_up_record")
	public String getSignUpRecord(HttpServletRequest request,HttpServletResponse response){
		
		JSONObject resultObj = new JSONObject();
		String moduleId = request.getParameter("moduleId");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		SignUpRecord record = new SignUpRecord();
		record.setSignUpConfigId(moduleId);
		PageInfo<SignUpRecord> page = signUpRecordService.findForList(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo),StringUtils.isBlank(pageSize)?10:Integer.parseInt(pageSize), record);
		List<SignUpRecord> relist = page.getList();
		JSONArray alist = new JSONArray();
		for(int i=0;null!=relist&&i<relist.size();i++){
			JSONObject e = new JSONObject();
			e.put("nick", relist.get(i).getNick());
			e.put("headerImg", relist.get(i).getHeaderImg());
			e.put("createDate", DateTools.convertDateToString(relist.get(i).getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
			alist.add(e);
		}
		resultObj.put("list",alist);
		
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取报名记录成功");
		ResponseUtils.renderJson(response, resultObj.toJSONString());
		
		return null;
		
	}
	

}
