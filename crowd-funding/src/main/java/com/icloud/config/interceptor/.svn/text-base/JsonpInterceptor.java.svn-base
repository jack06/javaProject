package com.icloud.config.interceptor;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.dto.BaseDto;
import com.icloud.vo.UserSession;


/**
 * 
 * 
 * 
 */
public class JsonpInterceptor implements HandlerInterceptor{
	@Autowired
	public RestTemplate restTemplate;
	@Override
	public void afterCompletion(HttpServletRequest reqeust, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest reqeust, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
 
	}

	@Override
	public boolean preHandle(HttpServletRequest reqeust, HttpServletResponse response, Object arg2) throws Exception {
//		response.setHeader("Pragma", "No-cache");  
//		response.setHeader("Cache-Control", "no-cache"); 
//		response.setHeader("Content-type", "application/x-javascript;charset=utf-8");
//		response.setDateHeader("Expires", 0);  
//		
//		String rd_session = reqeust.getParameter("rd_session");
//		UserSession userSession = (com.icloud.vo.UserSession) reqeust.getSession().getAttribute(rd_session);
//		System.out.println("userSession="+userSession);
//		if(userSession!=null){
//			if(System.currentTimeMillis() - userSession.getLoginTime() < userSession.getExpairtime()){
//				return true;
//			} 
//			System.out.println("rd_session已失效");
//		}
//		try {
//			BaseDto dto = new BaseDto("success", "4002", "未登录");
//			response.setContentType("text/html;charset=UTF-8");
//			response.getWriter().print(dto.toString());
//			response.getWriter().close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return false;
		return true;
	}
	

}
