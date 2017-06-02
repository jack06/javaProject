package com.icloud.web.staffmanage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.ResponseUtils;
import com.icloud.model.staffmanage.Tuserrole;
import com.icloud.service.staffmanage.StaffRoleManageService;

@Controller
public class StaffRoleManageController extends StaffManageBaseController<Tuserrole>{

	@Autowired
	private StaffRoleManageService staffRoleManageService;
	
	
	@RequestMapping("/staffmanage/staffrole_list")
	public String list(HttpServletRequest request,Tuserrole userrole) throws Exception{
	  List<Tuserrole> list = staffRoleManageService.findList(userrole);
	  request.setAttribute("userrole", userrole);
	  request.setAttribute("list", list);
	  return "staffmanage/staffrole_list";
	}
	
	@RequestMapping("/staffmanage/staffrole_getlist")
	public String getList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String roleName = request.getParameter("roleName");
		String pageNo = request.getParameter("pageNo");		
		JSONObject json = new JSONObject();
		Tuserrole userrole = new Tuserrole();
		if(StringUtils.isNotBlank(roleName)){
			userrole.setRoleName(roleName);
		}
		if(StringUtils.isNotBlank(pageNo)){
			userrole.setPageNo(Integer.parseInt(pageNo));
		}
		List<Tuserrole> list = staffRoleManageService.findList(userrole);
		json.put("pages", userrole.getPages());
		JSONArray array = new JSONArray();
		for(Tuserrole t:list){
			JSONObject sub = new JSONObject();
			sub.put("id",t.getId());
			sub.put("roleName", t.getRoleName());
			if(t.getIsNeedVerify().equals("0")){
				sub.put("isNeedVerify","是");}
			else if(t.getIsNeedVerify().equals("1")){
				sub.put("isNeedVerify","否");}
			
			array.add(sub);
		}
		json.put("list", array);
		ResponseUtils.renderJson(response, json.toJSONString());
		return null;
		
		
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/staffmanage/staffrole_to_input")
	public String toinput(String id, HttpServletRequest request) throws Exception{
		if(StringUtils.isNotBlank(id)){
			Tuserrole userrole = staffRoleManageService.findByKey(id);
			request.setAttribute("userrole", userrole);
		}else{
			request.setAttribute("userrole", null);
		}
				
		return "staffmanage/staffrole_input";
		
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/staffmanage/staffrole_input")
	public String input(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String id = request.getParameter("id");
		String roleName = request.getParameter("roleName");
		String isNeedVerify = request.getParameter("isNeedVerify");
		
		
		if(StringUtils.isNotBlank(id)){
			Tuserrole userrole = staffRoleManageService.findByKey(id);
			userrole.setRoleName(roleName);
			userrole.setIsNeedVerify(isNeedVerify);
			staffRoleManageService.update(userrole);
			ResponseUtils.renderText(response, "0002");
			return null;
			
		}else{
			int count = staffRoleManageService.selectCountByName(roleName);
			if(count>0){
				ResponseUtils.renderText(response, "0001");
				return null;
			}
			
			Tuserrole userrole = new Tuserrole();
			userrole.setRoleName(roleName);
			userrole.setIsNeedVerify(isNeedVerify);
			
			staffRoleManageService.save(userrole);
		}
		
		ResponseUtils.renderText(response, "0000");
		return null;
	}
	
	@RequestMapping("/staffmanage/staffrole_del")
	public String del(String id, HttpServletResponse response) throws Exception {
	
		Tuserrole userrole = staffRoleManageService.findByKey(id);
		if(null!=userrole){
			
		staffRoleManageService.delete(id);
		ResponseUtils.renderText(response, "0000");
		return null;
				
		}
		ResponseUtils.renderText(response, "0001");
		return null;
	}
	
	

}
