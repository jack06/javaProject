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
import com.icloud.model.staffmanage.Tuserext;
import com.icloud.service.staffmanage.PersonnelAuthenticationService;

@Controller
public class PersonnelAuthenticationController extends StaffManageBaseController<Tuserext>{

	@Autowired
	private PersonnelAuthenticationService personnelAuthenticationService;

	
	@RequestMapping("/staffmanage/personnel_authentication")
	public String list(HttpServletRequest request,Tuserext userext) throws Exception{
		userext.setPageSize(10);
	  List<Tuserext> list = personnelAuthenticationService.findList(userext);
	  request.setAttribute("userext", userext);
	  request.setAttribute("list", list);
	  return "staffmanage/personnel_authentication";
	}
	

	
	@RequestMapping("/staffmanage/authentication_getlist")
	public String getList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String realName = request.getParameter("realName");
		String wordNo = request.getParameter("wordNo");
		String phone = request.getParameter("phone");
		String isCertification= request.getParameter("isCertification"); 
		String pageNo = request.getParameter("pageNo");	
		
		JSONObject json = new JSONObject();
		Tuserext userext= new Tuserext();
			
		if(StringUtils.isNotBlank(realName)){
			userext.setRealName(realName);
		}
		if(StringUtils.isNotBlank(wordNo)){
			userext.setWordNo(wordNo);		
		}
		
		if(StringUtils.isNotBlank(phone)){
			userext.setPhone(phone);		
		}
		
		if(StringUtils.isNotBlank(isCertification)){
			userext.setIsCertification(isCertification);	
		}		
		
		if(StringUtils.isNotBlank(pageNo)){
			userext.setPageNo(Integer.parseInt(pageNo));
		}	
		userext.setPageSize(10);
		List<Tuserext> list = personnelAuthenticationService.findList(userext);
		json.put("pages", userext.getPages());
		JSONArray array = new JSONArray();
		for(Tuserext u:list){
			JSONObject sub = new JSONObject();
			sub.put("id",u.getId());
			sub.put("realName", u.getRealName());
			sub.put("wordNo", u.getWordNo());
			sub.put("departments", u.getDepartments());
			sub.put("nativePlace", u.getNativePlace());
			if(StringUtils.isNotBlank(u.getIsNeedVerify())){
				
				if(u.getGender()==null){
					sub.put("gender", u.getGender());}
				else if(u.getGender().equals("0")){
					sub.put("gender", "男");}
				else if(u.getGender().equals("1")){
						sub.put("gender", "男");}		
			}else{
				if(u.getGender()==null){
					sub.put("gender", u.getGender());}
				else if(u.getGender().equals("0")){
					sub.put("gender", "男");}
				else if(u.getGender().equals("1")){
						sub.put("gender", "男");}
			}
			sub.put("professional", u.getProfessional());
			sub.put("grade", u.getGrade());
			sub.put("phone", u.getPhone());
			if(StringUtils.isNotBlank(u.getIsNeedVerify())){
				if(u.getIsCertification().equals("0")){
					sub.put("isCertification","认证中");}
				else if(u.getIsCertification().equals("1")){
					sub.put("isCertification","认证成功");}
				else{
					sub.put("isCertification","认证失败");}
			}else{
				sub.put("isCertification","认证中");
			}	
			sub.put("declaration", u.getDeclaration());
			sub.put("hobby", u.getHobby());		
			sub.put("roleName", u.getRoleName());
			if(StringUtils.isNotBlank(u.getIsNeedVerify())){
				if(u.getIsNeedVerify().equals("0")){
					sub.put("isNeedVerify","是");}
				else if(u.getIsNeedVerify().equals("1")){
					sub.put("isNeedVerify","否");}
			}else{
				sub.put("isNeedVerify","是");
			}
			sub.put("nick", u.getNick());
			sub.put("wxHeadImg", u.getWxHeadImg());
			
			array.add(sub);
		}
		json.put("list", array);
		ResponseUtils.renderJson(response, json.toJSONString());
		return null;
		
		
	}
	
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * 审核通过方法
	 */
	@RequestMapping("/authentication_auditing")
	public String input(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String id = request.getParameter("id"); 

			Tuserext userext= personnelAuthenticationService.findByKey(id);
			userext.setIsCertification("1");
	
			personnelAuthenticationService.update(userext);
			ResponseUtils.renderText(response, "0000");
			return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * 审核不通过方法
	 */
	@RequestMapping("/authentication_fail")
	public String fail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String id = request.getParameter("id"); 

			Tuserext userext= personnelAuthenticationService.findByKey(id);
			userext.setIsCertification("2");
	
			personnelAuthenticationService.update(userext);
			ResponseUtils.renderText(response, "0000");
			return null;
	}
	
	

	@Override
	public String del(String id, HttpServletResponse response)
			throws Exception {
		return null;
	}



	@Override
	public String toinput(String id, HttpServletRequest request)
			throws Exception {
		return null;
	}
		
	
}
