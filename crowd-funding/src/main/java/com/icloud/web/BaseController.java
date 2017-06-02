package com.icloud.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.x509.qualified.TypeOfBiometricData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.icloud.service.impl.LoginServiceImpl;
 
/**
 * Controller基类 
 * @author 
 *  
 */
public abstract class BaseController {

	@Autowired
	protected  HttpServletRequest request;
	@Autowired
	protected HttpServletResponse  response;
	
	public final static Logger log = LoggerFactory
			.getLogger(BaseController.class);

	/**
	 * 将对象转成json字符串，并组装成jsonp格式
	 * @param obj javaBeand对象
	 * @return    jsonp格式字符串
	 */
	protected String pakageJsonp(Object obj) { 
		String callbackFun = request.getParameter("callbackFun");
		String result = "";
		if(obj instanceof JSONObject ){
			 result = obj.toString();
		}else{
			 result = JSONObject.toJSONString(obj);
		}
		log.info("返回参数:"+result);
		
		if (StringUtils.isBlank(callbackFun)) {
			return result;
		}
		String jsonpCallback = callbackFun+"("+  result  +");";
		return jsonpCallback;
	}
	
	public void outObject(Object obj) {
		try {
			response.setContentType("application/json; charset=utf-8");  
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(obj.toString());

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
