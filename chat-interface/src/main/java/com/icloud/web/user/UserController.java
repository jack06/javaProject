package com.icloud.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.model.user.User;
import com.icloud.model.user.UserExt;
import com.icloud.model.user.UserRole;
import com.icloud.service.redis.RedisService;
import com.icloud.service.user.UserExtService;
import com.icloud.service.user.UserRoleService;
import com.icloud.service.user.UserService;
import com.icloud.web.BaseController;

@RestController
public class UserController extends BaseController {
	public final static Logger log = LoggerFactory
			.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserExtService userExtService;
	@Autowired
	private UserRoleService roleService;
	@Autowired
	private RedisService redisService;
//	
//	private UserRoleService userRoleService;

	/** 个人中心页 **/
	@RequestMapping("/myCenter")
	public String myCenter(HttpServletRequest request) {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失sid");
			return pakageJsonp(resultObj);
		}

		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
			return pakageJsonp(resultObj);
		}
		JSONObject resultData = new JSONObject();
		resultData.put("nick", user.getNick());
		resultData.put("headerImg", user.getWxHeadImg());
		if (null != user.getUserExt()) {
			if(null!=user.getUserExt().getUserRole()){
			
				if( user.getUserExt().getIsCertification().equals("1")){
				  resultData.put("isCertification",true);
				  resultData.put("roleName", user.getUserExt().getUserRole().getRoleName());
				}else if( user.getUserExt().getIsCertification().equals("0")){
				  resultData.put("isCertification",true);
				  resultData.put("roleName", "审核中");
				}else{
				  resultData.put("isCertification",false);
				  resultData.put("roleName", "身份认证失败");
				}
			}else{
				  resultData.put("isCertification",false);
				  resultData.put("roleName", "未知身份");
			}
		} else {
			resultData.put("isCertification", false);
			resultData.put("roleName", "未提交审核");
		}
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "进入个人中心页成功");
		return pakageJsonp(resultObj);
	}

	@RequestMapping("/myCard")
	public String myCard(HttpServletRequest request) {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失sid");
			return pakageJsonp(resultObj);
		}
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
			return pakageJsonp(resultObj);
		}
		/*if(null==user.getUserExt()||user.getUserExt().getIsCertification().equals("0")){
			resultObj.put("errCode", "0013");// 用户不存在
			resultObj.put("resultMsg", "用户尚未认证，请先认证");
			return pakageJsonp(resultObj);
		}*/
		JSONObject resultData = new JSONObject();
		UserExt ext = user.getUserExt();
		if (null != ext) {
			resultData.put("realName", ext.getRealName());
			resultData.put("photo", ext.getPhoto());
			resultData.put("phone", ext.getPhone());
			resultData.put("email", ext.getEmail());
			resultData.put("degree", ext.getGrade());
			resultData.put("school", ext.getDepartments());
			JSONArray hobbiesArray = JSONArray.parseArray(ext.getHobby());
			resultData.put("hobbies", hobbiesArray);
			resultData.put("declaration", ext.getDeclaration());
		}
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取名片成功");
		return pakageJsonp(resultObj);
	}

	/** 编辑名片 **/
	@RequestMapping("/editCard")
	public String editCard(HttpServletRequest request) {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失sid");
			return pakageJsonp(resultObj);
		}
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
			return pakageJsonp(resultObj);
		}
		/*if(null==user.getUserExt()||!user.getUserExt().getIsCertification().equals("1")){
			resultObj.put("errCode", "0013");// 用户不存在
			resultObj.put("resultMsg", "用户尚未认证，请先认证");
			return pakageJsonp(resultObj);
		}*/
		String data = parameterObj.getString("data");
		if (StringUtils.isBlank(data)) {
			resultObj.put("errCode", "0000");// 用户不存在
			resultObj.put("resultMsg", "修改成功");
			return pakageJsonp(resultObj);
		}
		JSONObject cardData = JSONObject.parseObject(data);
		String realName = cardData.getString("realName");
		String photo = cardData.getString("photo");
		String phone = cardData.getString("phone");
		String code = cardData.getString("code");
		String school = cardData.getString("school");
		String degree = cardData.getString("degree");
		String email = cardData.getString("email");
		String hobbies = cardData.getString("hobbies");
		String declaration = cardData.getString("declaration");
		
		UserExt ext = user.getUserExt();
		UserExt phoneExt = userExtService.findByphone(phone);
		if (null == ext) {
			if(StringUtils.isNotBlank(phone)){
				if(StringUtils.isBlank(code)){
					resultObj.put("errCode", "1000");
					resultObj.put("resultMsg", "参数缺失code");
					return pakageJsonp(resultObj);
				 }
			}
			if(null!=phoneExt){
				resultObj.put("errCode", "0015");
				resultObj.put("resultMsg", "手机号已被使用");
				return pakageJsonp(resultObj);
			}
			 if(StringUtils.isBlank(code)){
				 resultObj.put("errCode", "1000");
				 resultObj.put("resultMsg", "参数缺失code");
			 	 return pakageJsonp(resultObj);
			 }
			 /*String vCode = (String) redisService.get(phone);
				if(code.equals(vCode)){
					resultObj.put("errCode", "0014");
					resultObj.put("resultMsg", "验证码错误");
					return pakageJsonp(resultObj);
				}*/
			ext = new UserExt();
			if (StringUtils.isNotBlank(declaration))
				ext.setDeclaration(declaration);
			if (StringUtils.isNotBlank(photo))
				ext.setPhoto(photo);
			if (StringUtils.isNotBlank(phone))
				ext.setPhone(phone);
			if (StringUtils.isNotBlank(email))
				ext.setEmail(email);
			if (StringUtils.isNotBlank(hobbies))
				ext.setHobby(hobbies);
			if (StringUtils.isNotBlank(realName))
				ext.setRealName(realName);
			if (StringUtils.isNotBlank(school))
				ext.setDepartments(school);
			if (StringUtils.isNotBlank(degree))
				ext.setGrade(degree);
			ext.setIsCertification("0");
			userExtService.save(ext);
		} else {
			if(StringUtils.isNotBlank(phone)){
				if(null!=phoneExt){
					if(!phoneExt.getId().equals(ext.getId())){
						resultObj.put("errCode", "0015");
						resultObj.put("resultMsg", "手机号已被使用");
						return pakageJsonp(resultObj);
					}
				}else{
				 if(StringUtils.isBlank(code)){
					 resultObj.put("errCode", "1000");
					 resultObj.put("resultMsg", "参数缺失code");
				 	 return pakageJsonp(resultObj);
				 }
				 /*String vCode = (String) redisService.get(phone);
					if(code.equals(vCode)){
						resultObj.put("errCode", "0014");
						resultObj.put("resultMsg", "验证码错误");
						return pakageJsonp(resultObj);
					}*/
				}
			}
			
			if (StringUtils.isNotBlank(declaration))
				ext.setDeclaration(declaration);
			if (StringUtils.isNotBlank(photo))
				ext.setPhoto(photo);
			if (StringUtils.isNotBlank(phone))
				ext.setPhone(phone);
			if (StringUtils.isNotBlank(email))
				ext.setEmail(email);
			if (StringUtils.isNotBlank(hobbies))
				ext.setHobby(hobbies);
			if (StringUtils.isNotBlank(realName))
				ext.setRealName(realName);
			if (StringUtils.isNotBlank(school))
				ext.setDepartments(school);
			if (StringUtils.isNotBlank(degree))
				ext.setGrade(degree);
			userExtService.update(ext);
		}
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "修改成功");
		return pakageJsonp(resultObj);
	}

	/** 获取角色列表 **/
	@RequestMapping("/getRoleList")
	public String getRoleList(HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();
		JSONObject resultDate = new JSONObject();
		List<UserRole> list = roleService.findAll();
		JSONArray jsonList = new JSONArray();
		JSONObject role = null;
		for (int i = 0; null != list && i < list.size(); i++) {
			role = new JSONObject();
			role.put("roleId", list.get(i).getId());
			role.put("roleName", list.get(i).getRoleName());
			jsonList.add(role);
		}
		resultDate.put("list", jsonList);
		resultObj.put("resultData", resultDate);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取成功");
		return pakageJsonp(resultObj);
	}

	/**认证 手机号跟邮箱认证**/
	@RequestMapping("/certification")
	public String certification(HttpServletRequest request) {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失sid");
			return pakageJsonp(resultObj);
		}
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
			return pakageJsonp(resultObj);
		}
		String data = parameterObj.getString("data");
		if (StringUtils.isBlank(data)) {
			resultObj.put("errCode", "0000");// 
			resultObj.put("resultMsg", "修改成功");
			return pakageJsonp(resultObj);
		}
		JSONObject cardData = JSONObject.parseObject(data);
		String phone = cardData.getString("phone");
		String email = cardData.getString("email");
		String realName = cardData.getString("realName");
		if (StringUtils.isBlank(phone)) {
			resultObj.put("errCode", "1000");// 用户不存在
			resultObj.put("resultMsg", "参数缺失1000，phone");
			return pakageJsonp(resultObj);
		}
		if (StringUtils.isBlank(email)) {
			resultObj.put("errCode", "1000");// 用户不存在
			resultObj.put("resultMsg", "参数缺失1000，email");
			return pakageJsonp(resultObj);
		}
		String code = cardData.getString("code");
		String roleId = cardData.getString("roleId");
			if (StringUtils.isBlank(code)) {
				resultObj.put("errCode", "1000");// 
				resultObj.put("resultMsg", "参数缺失1000,code");
				return pakageJsonp(resultObj);
			}
                if(StringUtils.isBlank(roleId)){
                	resultObj.put("errCode", "1000");// 
    				resultObj.put("resultMsg", "参数缺失roleId");
    				return pakageJsonp(resultObj);
                }
                log.error("roleId=="+roleId);
                if(null==roleId || "".equals(roleId)){
                	resultObj.put("errCode", "1000");// 
    				resultObj.put("resultMsg", "参数缺失roleId");
    				return pakageJsonp(resultObj);
                }
                if(null==roleService.findByKey(roleId)){
                	resultObj.put("errCode", "0016");// 
    				resultObj.put("resultMsg", "参数缺失roleId");
    				return pakageJsonp(resultObj);
                }
                
			/*String Code = (String) redisService.get(phone);
			if (Code == null) {
				resultObj.put("errCode", "0014");// 
				resultObj.put("resultMsg", "验证码错误");
				return pakageJsonp(resultObj);
			}
			if (!code.equals(Code)) {
				resultObj.put("errCode", "0014");// 用户不存在
				resultObj.put("resultMsg", "验证码错误");
				return pakageJsonp(resultObj);
			}
			redisService.remove(phone);*/
			UserExt ext = userExtService.findByphone(phone);
			if (null != ext) {
				if(StringUtils.isNotBlank(ext.getUserId())&&!ext.getUserId().equals(user.getId())){
					resultObj.put("errCode", "0015");// 
					resultObj.put("resultMsg", "该手机号已被使用");
					return pakageJsonp(resultObj);
				}
				ext.setUserId(user.getId());//用户不为空 已经导入直接设置关联账户
				ext.setEmail(email);
				ext.setPhone(phone);
				userExtService.update(ext);
			} else {
				ext = userExtService.findByUser(user.getId());
				if (null == ext) {
					ext = new UserExt();
					ext.setRoleId(roleId);
					ext.setEmail(email);
					ext.setPhone(phone);
					ext.setUserId(user.getId());
					ext.setIsCertification("0");
					ext.setRealName(realName);
					userExtService.save(ext);
				} else {
					ext.setEmail(email);
					ext.setPhone(phone);
					ext.setRoleId(roleId);
					ext.setUserId(user.getId());
					ext.setIsCertification("0");
					ext.setRealName(realName);
					userExtService.update(ext);
				}

			}
		
		resultObj.put("errCode", "0000");// 用户不存在
		resultObj.put("resultMsg", "提交认证成功");
		return pakageJsonp(resultObj);
	}
	
	/**发送短信**/
	@RequestMapping("/sendSms")
    public String sendSms(HttpServletRequest request){
    	JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("phone")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失phone");
			return pakageJsonp(resultObj);
		}
		String phone = parameterObj.getString("phone");
		JSONObject resultData = new JSONObject();
		log.info("短信发送成功");
		redisService.set(phone, "2222", 120L);
		resultData.put("code", "2222");
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "短信发送成功");
		return pakageJsonp(resultObj);
    }
	/** 解绑**/
	@RequestMapping("/unBind")
	public String unBind(HttpServletRequest request){
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失sid");
			return pakageJsonp(resultObj);
		}

		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
			return pakageJsonp(resultObj);
		}
		if(null!=user.getUserExt()){
			UserExt ext = user.getUserExt();
			ext.setUserId("0");
			ext.setPhone("0");
			userExtService.update(ext);
		}
		resultObj.put("errCode", "0000");// 用户不存在
		resultObj.put("resultMsg", "解绑成功");
		return pakageJsonp(resultObj);
		
		
	}

}
