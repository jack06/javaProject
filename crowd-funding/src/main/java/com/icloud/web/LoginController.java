package com.icloud.web;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icloud.service.LoginService;
import com.icloud.service.redis.RedisService;
import com.icloud.util.RequestUtil;


@RestController
public class LoginController extends BaseController {
	@Autowired
	 private LoginService loginService;
	@Autowired
	private RedisService redisService;
	
 
	/**微信小程序登录接口**/
	@RequestMapping(value = "/wx/login")
	 public Object login(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
		System.out.println("jsonText=="+jsonText);
		return  pakageJsonp(loginService.login(jsonText,request));
    }
	
	/**登录会话有效性验证**/
	@RequestMapping(value="/wx/checkSession")
	public Object checkSession(HttpServletRequest request){
		String jsonText = RequestUtil.readPostContent(request);
		Object obj = loginService.checkSession(jsonText, request);
		
		return pakageJsonp(obj);
	}
	
	@RequestMapping(value="/wx/redis")
	public Object checkRedis(HttpServletRequest request){
		String  t = request.getParameter("t");
		
		redisService.set("tt", t);
		return null;
		
	}
	
}