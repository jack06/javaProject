package com.icloud.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;


/**
 * 基础Controller 提供常用方法
 * @author luoqw
 * 2016-9-6下午3:21:07
 */
public class BaseServiceImple {
	
	@Autowired
	public RestTemplate restTemplate; 

	
	
	/**
	 * 发送http请求
	 * @param url 请求地址
	 * @param jsonParams 请求的参数（json字符串形式）
	 * @return json字符串
	 */
	public  String doPost(String url,String jsonParams) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(jsonParams, headers);
		String result = restTemplate.postForObject(url, formEntity, String.class); 
		return result;
	}
	
	public <T> T  doPost(String url,String jsonParams,Class<T> cType) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(jsonParams, headers);
		T result = restTemplate.postForObject(url, formEntity, cType); 
		return result;
	}
	
	/**
	 * 发送http请求
	 * @param url 请求地址
	 * @param JSONObject 请求的参数（json对象）
	 * @return json字符串
	 */
	public  String doPost(String url,JSONObject jsonObj) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
		String result = restTemplate.postForObject(url, formEntity, String.class);
		return result;
	}
	
	public  <T> T doPost(String url,JSONObject jsonObj,Class<T> ctype) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
		T result = restTemplate.postForObject(url, formEntity, ctype);
		return result;
	}
	

	
	public  String doGet(String url) {
		String result  = restTemplate.getForObject(url, String.class);
		return result;
	}
 
	
	public  <T> T doGet(String url,Class<T> ctype) {
		return restTemplate.getForObject(url, ctype);
	}

	
	
	
	/*
	public  String doPost(String url,Map<String, Object> params) {
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
		bodyMap.setAll(params);
		String result = restTemplate.postForObject(url, bodyMap, String.class);
		return result;
	}
	
	public  <T> T doPost(String url,Map<String, Object> params,Class<T> type) {
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
		bodyMap.setAll(params);
		T result = restTemplate.postForObject(url, bodyMap, type);
		return result;
	}
	
	
	public  String doPost(String url, Object obj ) {
		Map<String, Object> params = transBean2Map(obj);
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
		bodyMap.setAll(params);
		String result = restTemplate.postForObject(url, bodyMap, String.class);
		return result;
	}
	
	
	public <T>T doPost(String url, Object obj,Class<T> ctype ) {
		Map<String, Object> params = transBean2Map(obj);
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
		bodyMap.setAll(params);
		T result = restTemplate.postForObject(url, bodyMap, ctype);
		return result;
	}
	
	 // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public  Map<String, Object> transBean2Map(Object obj) {  
  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
  
        return map;  
  
    }  */
	
 
	
}
