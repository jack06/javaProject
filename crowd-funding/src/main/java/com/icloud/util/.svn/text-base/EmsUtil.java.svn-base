package com.icloud.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * EMS发货、物流查询工具类
 * @author chencl
 * @date 2017-4-20
 */
public class EmsUtil {

	public static String post2Server_GetJsonData(String url, String json) throws ClientProtocolException, IOException{
		DefaultHttpClient httpclient = new DefaultHttpClient(/*httpclient 4.2.3�汾*/
				new ThreadSafeClientConnManager());
		httpclient.getParams().setBooleanParameter(
					"http.protocol.expect-continue", false);
		HttpPost httpost = new HttpPost(url);
		httpost.addHeader("Connection", "close");
			
		StringEntity stringEntity = new StringEntity(json,"utf-8");//���������������    
		stringEntity.setContentEncoding("UTF-8");    
		stringEntity.setContentType("application/json");    
	    httpost.setEntity(stringEntity);    

		HttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();
		// -------------------------------------------------------------------

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		String resource = "";
		String temp;
		while ((temp = br.readLine()) != null) {
//			System.out.println("Web content--------------"+temp);
			resource += temp;
		}
		EntityUtils.consume(entity);
			
		return resource;
	}
	
	public static String post2Server_GetJsonData(String url, List<NameValuePair> nvps) throws ClientProtocolException, IOException{
		DefaultHttpClient httpclient = new DefaultHttpClient(/*httpclient 4.2.3�汾*/
				new ThreadSafeClientConnManager());
		httpclient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		HttpPost httpost = new HttpPost(url);
		httpost.addHeader("Connection", "close");

		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		HttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();
		// -------------------------------------------------------------------

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,
				"UTF-8"));
		String resource = "";
		String temp;
		while ((temp = br.readLine()) != null) {
//			System.out.println("Web content--------------"+temp);
			resource += temp;
		}
		EntityUtils.consume(entity);
		
		return resource;
	}
	
	public static String post2Server_GetJsonDataByEmsNo(String url, String mailcodeList) throws ClientProtocolException, IOException{
		DefaultHttpClient httpclient = new DefaultHttpClient(/*httpclient 4.2.3版本*/
				new ThreadSafeClientConnManager());
		httpclient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		HttpPost httpost = new HttpPost(url);
		httpost.addHeader("Connection", "close");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
//		String ip="180.136.146.54";
		String ip=ConfigUtil.get("server_ip");
		String reverseIp = new StringBuilder(ip).reverse().toString();
//		String Ip = getReverseIp();
			
		nvps.add(new BasicNameValuePair("validateKey", EmsUtil.getMd5String(reverseIp)));
		nvps.add(new BasicNameValuePair("mailcodeList", mailcodeList));

		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		HttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();
		// -------------------------------------------------------------------

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,
				"UTF-8"));
		String resource = "";
		String temp;
		while ((temp = br.readLine()) != null) {
//			System.out.println("Web content--------------"+temp);
			resource += temp;
		}
		EntityUtils.consume(entity);
		
		return resource;
	}
    
    public static String post2Server_GetJsonDataByOrderId(String url, String ordernoList) throws ClientProtocolException, IOException{
		DefaultHttpClient httpclient = new DefaultHttpClient(/*httpclient 4.2.3版本*/
				new ThreadSafeClientConnManager());
		httpclient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		HttpPost httpost = new HttpPost(url);
		httpost.addHeader("Connection", "close");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
//		String ip="180.136.146.54";
		String ip=ConfigUtil.get("server_ip");
		String reverseIp = new StringBuilder(ip).reverse().toString();
//		String Ip = getReverseIp();
			
		nvps.add(new BasicNameValuePair("validateKey", EmsUtil.getMd5String(reverseIp)));
		nvps.add(new BasicNameValuePair("ordernoList", ordernoList));

		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		HttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();
		// -------------------------------------------------------------------

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,
				"UTF-8"));
		String resource = "";
		String temp;
		while ((temp = br.readLine()) != null) {
//			System.out.println("Web content--------------"+temp);
			resource += temp;
		}
		EntityUtils.consume(entity);
		
		return resource;
	}
	    
	/**
	 * MD5加密
	 * @param input
	 * @return
	 */
	public static String getMd5String(String input) {
		byte[] md5byte = null;
		String md5String = "";
		try {
			// use MD5 of java core
			MessageDigest alg;

			alg = MessageDigest.getInstance("MD5");

			md5byte = alg.digest(input.getBytes());
			for (byte b : md5byte) {
				md5String += b + "";
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5String;
	}
	/**
	 * 获取本地ip
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getReverseIp() throws UnknownHostException{
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();//获得本机IP，如果有路由请使用路由的IP
		String reverseIp = new StringBuilder(ip).reverse().toString();
		return reverseIp;
	}
}
