package com.icloud.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * HttpServletRequest 读取入参数据
 * @author luoqw
 *2016年9月13日 下午3:21:25
 */
public class RequestUtil {
	
	public final static Logger logger = LoggerFactory
			.getLogger(RequestUtil.class);
	
	/** 
	 * 读取写入的字符串
	 * @param request
	 * @return
	 */
	public static String readPostContent(HttpServletRequest request){
		BufferedReader in= null;
		String content = null;
		String line = null;
		try {
			in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder buf = new StringBuilder();
			while ((line = in.readLine()) != null) {
				buf.append(line);
			}
			content = buf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String uri = request.getRequestURI(); 
		logger.info("请求地址："+uri+" 参数："+content);
		return content; 
	}
	
	/**
	 * 将读取的字符串转成JSONObject
	 * @param request
	 * @return
	 */
	public static JSONObject readToJSONObect(HttpServletRequest request){
		String jsonText = readPostContent(request);
		JSONObject jsonObj = JSONObject.parseObject(jsonText, JSONObject.class);
		return jsonObj;
	}
	
	
}
