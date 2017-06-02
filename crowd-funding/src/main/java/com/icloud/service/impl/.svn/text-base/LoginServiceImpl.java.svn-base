package com.icloud.service.impl;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.icloud.service.LoginService;
import com.icloud.service.redis.RedisService;
import com.icloud.util.RandomUtil;
import com.icloud.util.wx.HttpRequestUtil;
import com.icloud.util.wx.WxConst;
import com.icloud.vo.UserSession;

@Service
public class LoginServiceImpl extends BaseServiceImple implements LoginService{
	


	public final static Logger log = LoggerFactory
			.getLogger(LoginServiceImpl.class);
	@Autowired
	private RedisService redisService;
	
	/**
	 * 微信登录
	 * @param request
	 * @return
	 */
	@Override
	public Object login(String jsonParams, HttpServletRequest request) {
		JSONObject resjson = new JSONObject();
		String message = "";
		if(!StringUtils.isEmpty(jsonParams)){
			
			JSONObject json =  JSONObject.parseObject(jsonParams);
			String url = WxConst.getOpenIdUrl.replace("APPID", WxConst.appid).replace("SECRET", WxConst.secret)
					.replace("JSCODE",json.getString("code"));
			JSONObject jsonObject = HttpRequestUtil.httpRequest(url,  "GET", null);
			if (!(jsonObject == null) && null != jsonObject.getString("openid")) {
				log.info("JSONOBJ:" + jsonObject.toString());
				String openId = jsonObject.getString("openid");
				String session_key = jsonObject.getString("session_key");
				if(!StringUtils.isEmpty(openId) && !StringUtils.isEmpty(session_key)){
					String rd_session = RandomUtil.getRandomString(20);
					resjson.put("method", "json");
					resjson.put("status", true);
					resjson.put("rd_session", rd_session);
					resjson.put("contents", "获取openid成功");
					
					log.info("openId=" + openId+"; session_key="+session_key);
					
					UserSession userSession =  new UserSession();
					userSession.setOpenId(openId);
					userSession.getSession_key();
					userSession.setLoginTime(System.currentTimeMillis());
					
					redisService.set(rd_session, userSession,1200L);
					return resjson;
				}else{
					message = "openId或者session_key为空";
				}
				
			}else{
				message = "向微信请求返回数据为空,获取openid失败";
			}
		}else{
			message = "code 为空,获取openid失败";
		}
		resjson.put("method", "json");
		resjson.put("status", false);
		resjson.put("contents", message);
		return resjson;
		
	}
	
	@Override
	public Object checkSession(String jsonParams,HttpServletRequest request) {
		// TODO Auto-generated method stub
		JSONObject resjson = new JSONObject();
		if(org.apache.commons.lang.StringUtils.isNotBlank(jsonParams)){
			JSONObject json =  JSONObject.parseObject(jsonParams);
			String rd_session = json.getString("rd_session");
		 if(org.apache.commons.lang.StringUtils.isNotBlank(rd_session)){	
			UserSession user = (UserSession) redisService.getSession(rd_session);
		    if(null==user){
		    	resjson.put("method", "json");
		    	resjson.put("status", false);
		    	resjson.put("contents", "会话失效");
		    	log.info("redis失效式会话失效"); 
				return resjson;
		    }
			Long loginTime = user.getLoginTime();
			if(System.currentTimeMillis()/1000-loginTime/1000>=7200){
				resjson.put("method", "json");
				resjson.put("status", false);
				resjson.put("contents", "会话失效");
				log.info("微信端登录session_key失效式会话失效");
				return resjson;
			}
			resjson.put("method", "json");
			resjson.put("status", true);
			resjson.put("contents", "会话尚未失效");
			return resjson;
			}else{
				resjson.put("method", "json");
				resjson.put("status", false);
				resjson.put("contents", "rd_session is null");
				return resjson;
			}
		}
		resjson.put("method", "json");
		resjson.put("status", false);
		resjson.put("contents", "rd_session is null");
		return resjson;
		
		
	}
	
 
}