package com.icloud.service.user.impl;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.icloud.service.user.LoginService;
import com.icloud.service.user.UserService;
import com.icloud.model.user.User;
import com.icloud.service.BaseServiceImple;
import com.icloud.service.redis.RedisService;
import com.icloud.common.util.RandomUtil;
import com.icloud.common.util.wx.HttpRequestUtil;
import com.icloud.common.util.wx.WxConst;
import com.icloud.dto.vo.UserSession;

@Service
public class LoginServiceImpl extends BaseServiceImple implements LoginService{
	


	public final static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	private RedisService redisService;
	@Autowired
	private UserService userService;
	
	/**
	 * 微信登录
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Object login(String jsonParams, HttpServletRequest request) throws Exception{
		JSONObject resjson = new JSONObject();
		if(!StringUtils.isEmpty(jsonParams)){
			JSONObject json =  JSONObject.parseObject(jsonParams);
			String url = WxConst.getOpenIdUrl.replace("APPID", WxConst.appid).replace("SECRET", WxConst.secret)
					.replace("JSCODE",json.getString("code"));
			JSONObject jsonObject = HttpRequestUtil.httpRequest(url,  "GET", null);
			if (!(jsonObject == null) && null != jsonObject.getString("openid")) {
				log.info("jsonObj:" + jsonObject.toString());
				String openId = jsonObject.getString("openid");
				String session_key = jsonObject.getString("session_key");
				if(!StringUtils.isEmpty(openId) && !StringUtils.isEmpty(session_key)){
					User user = userService.findByOpenId(openId);
					JSONObject user_raw = JSONObject.parseObject(json.getString("user_raw"));
					if(null==user){
						user = new User();
						user.setNick(user_raw.getString("nickName"));
						user.setOpenId(openId);
						user.setWxHeadImg(user_raw.getString("avatarUrl"));
						userService.save(user);
					}else{
						user.setNick(user_raw.getString("nickName"));
						user.setOpenId(openId);
						user.setWxHeadImg(user_raw.getString("avatarUrl"));
						userService.update(user);
					}
					String rd_session = RandomUtil.getRandomString(20);
					JSONObject resultData = new JSONObject();
					resjson.put("errCode", "0000");
					resjson.put("resultMsg", "登陆成功");
					resjson.put("resultData", resultData);
					resultData.put("sid", rd_session);
				
					
					log.info("openId=" + openId+"; session_key="+session_key);
					
					UserSession userSession =  new UserSession();
					userSession.setOpenId(openId);
					userSession.getSession_key();
					userSession.setLoginTime(System.currentTimeMillis());
					redisService.set(rd_session, userSession,1200L);
					
					return resjson;
				}else{
					
					resjson.put("errCode", "0002");
					resjson.put("resultMsg", "openid获取失败");
				}
				
			}else{
				resjson.put("errCode", "0002");
				resjson.put("resultMsg", "openid获取失败");
			}
		}else{
			resjson.put("errCode", "1000");
			resjson.put("resultMsg", "参数缺失");
		}
	 
		return resjson;
		
	}
	
	@Override
	public Object checkSession(String jsonParams,HttpServletRequest request) {
		JSONObject resjson = new JSONObject();
		if(org.apache.commons.lang.StringUtils.isNotBlank(jsonParams)){
			JSONObject json =  JSONObject.parseObject(jsonParams);
			String rd_session = json.getString("sid");
		 if(org.apache.commons.lang.StringUtils.isNotBlank(rd_session)){	
			UserSession user = (UserSession) redisService.getSession(rd_session);
		    if(null==user){
		    	resjson.put("errCode", "0011");
				resjson.put("resultMsg", "本地会话失效");
		    	log.info("redis失效式会话失效"); 
				return resjson;
		    }
			Long loginTime = user.getLoginTime();
			if(System.currentTimeMillis()/1000-loginTime/1000>=7200){
				resjson.put("errCode", "0012");
				resjson.put("resultMsg", "微信登录状态失效");
				return resjson;
			}
				resjson.put("resultCode", "0000");
				resjson.put("resultMsg", "会话尚未失效");
				return resjson;
			}else{
				resjson.put("errCode", "1000");
				resjson.put("resultMsg", "参数缺失");
				return resjson;
			}
		}
		resjson.put("errCode", "1000");
		resjson.put("resultMsg", "参数缺失");
		return resjson;
		
		
	}
	
 
}