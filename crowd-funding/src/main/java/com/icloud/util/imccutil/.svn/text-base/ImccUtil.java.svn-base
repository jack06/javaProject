package com.icloud.util.imccutil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.Node;

public class ImccUtil {
	private static Logger log = Logger.getLogger(ImccUtil.class);

	// imcc
	/**
	 hostTel=15709
     hostNumber=gh_43224dd38126
	 */

	public static String MCURL = "http://host:port/mc/comm_protocol?hostel=HOSTTEL&imtype=IMTYPE&hostnumber=HOSTNUMBER&waitret=true"; // mc
	public static String TOKENCONTENT = "gethostnumberinfo"; // TOKENCONTENT
	public static String JSAPICONTENT = "getjsapi_ticket"; // IMCC jsapi_ticket
															// content

	static String host = "119.29.25.86:7003";
	static String hosttel = "15709";
	static String hostnumber = "gh_43224dd38126"; // GH号
 
	/**
	 * @Description: 获取token接口
	 * @Author zhanghaitao
	 * @param hostTel
	 * @param hostNumber
	 * @param waitret
	 * @return
	 */

	public static String getToken2(){
		String imtype = "161"; // 161代表微信
		String addr = MCURL.replace("host:port", host).replace("HOSTTEL", hosttel).replace("IMTYPE", imtype)
				.replace("HOSTNUMBER", hostnumber);
		String state = "";
		String apptoken;
		String xml = invokeComm(addr, TOKENCONTENT);
		System.out.println("返回的字符串是="+xml);
		//<apptoken>
		//</apptoken>
		int beginIndex = xml.indexOf("<apptoken>");
		int endIndex   = xml.indexOf("</apptoken>");
		String access_token = xml.substring(beginIndex, endIndex).replace("<apptoken>", "");
		System.out.println(access_token);
		return access_token;
	}
	public static String getToken() {
		String imtype = "161"; // 161代表微信
		String addr = MCURL.replace("host:port", host).replace("HOSTTEL", hosttel).replace("IMTYPE", imtype)
				.replace("HOSTNUMBER", hostnumber);
		String state = "";
		String apptoken;
		String xml = invokeComm(addr, TOKENCONTENT);
		System.out.println("xml======"+xml);
		try {
			if (xml != null && !"".equals(xml)) {
				XmlParser parser = new XmlParser(xml);
				List<Node> rtncodeProperties = parser.getNodes("/response/result");
				if (rtncodeProperties.size() > 0) {
					Element e1 = (Element) rtncodeProperties.get(0);
					state = e1.getText();
				}
				if (state != null && state.equals("0")) {
					// 获得token
					List<Node> orderProperties = parser.getNodes("/response/retcontent/apptoken");
					if (orderProperties.size() > 0) {
						Element e2 = (Element) orderProperties.get(0);
						apptoken = e2.getText();
						System.out.println("apptoken:" + apptoken);
						return apptoken;
					}

				} else {
					log.error("接口参数验证失败!" + xml);
				}

			} else {
				log.error("http请求发送失败!" + xml);
			}
		} catch (Exception e) {
			log.error("获取参数失败!" + xml);
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 平台管理的accessToken
	 * @param sid
	 * @return
	 */
	/*public static String getToken(Long sid) {
		AccessToken token =  AccessTokenUtil.getAccessToken(sid);
		if(null==token){
			return null;
		}
		return token.getToken();

	}*/
	
	/**
	 * @Description: 获取jsapi_ticket接口
	 * 
	 * @return
	 */
	public static String getJsapiTicket() {

		String imtype = "161"; // 161代表微信

		String addr = MCURL.replace("host:port", host).replace("HOSTTEL", hosttel).replace("IMTYPE", imtype)
				.replace("HOSTNUMBER", hostnumber);
		String state = "";
		String appticket;
		String xml = invokeComm(addr, JSAPICONTENT);
		try {
			if (xml != null && !"".equals(xml)) {
				XmlParser parser = new XmlParser(xml);
				List<Node> rtncodeProperties = parser.getNodes("/response/result");
				if (rtncodeProperties.size() > 0) {
					Element e1 = (Element) rtncodeProperties.get(0);
					state = e1.getText();
				}
				if (state != null && state.equals("0")) {
					// 获得token
					List<Node> orderProperties = parser.getNodes("/response/retcontent/appticket");
					if (orderProperties.size() > 0) {
						Element e2 = (Element) orderProperties.get(0);
						appticket = e2.getText();
						System.out.println("apptoken:" + appticket);
						return appticket;
					}
				} else {
					log.error("接口参数验证失败!" + xml);
				}

			} else {
				log.error("http请求发送失败!" + xml);
			}
		} catch (Exception e) {
			log.error("获取参数失败!" + xml);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 调用mc通用接口
	 */

	@SuppressWarnings("unused")
	public static String invokeComm(String addr, String content) {
		StringBuilder rtn = new StringBuilder();
		OutputStream os = null;
		PrintWriter pw = null;
		BufferedReader in = null;
		System.out.println("addr:" + addr);
		System.out.println("content:" + content);
		try {
			URL url = new URL(addr);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			uc.setConnectTimeout(2000); // 连接超时设置为2s
			uc.setReadTimeout(35000); // 读超时设置为35s
			uc.setDoOutput(true); // 因为这个是post请求，参数要放在http正文内，因此需要设为true，默认情况下是false
			uc.setUseCaches(false); // post请求不能使用缓存
			// 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			uc.setRequestProperty("Content-type", "text/xml;charset=utf-8");
			uc.setRequestMethod("POST"); // 设定请求的方法为"POST"，默认是GET
			uc.connect();

			os = uc.getOutputStream();
			OutputStreamWriter out = new OutputStreamWriter(os, "utf-8");
			pw = new PrintWriter(out);
			pw.print(content);
			pw.flush();

			/* 获取服务器端返回信息 */
			in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				rtn.append(inputLine).append("\r\n");
			}

		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			return "time_out";
		} catch (Exception ex) {
			System.out.println("调用IMCC监控中心接口异常" + ex);
		} finally {
			try {
				if (pw != null) {
					pw.close();
					pw = null;
				}

				if (os != null) {
					os.close();
					os = null;
				}

				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception ex) {
				System.out.println("释放资源异常" + ex);
			}
		}
		System.out.println("ret:" + rtn.toString());
		return rtn.toString();
	}

	@SuppressWarnings("unused")
	private static String invoke(String addr, String xml) {
		StringBuilder rtn = new StringBuilder();
		OutputStream os = null;
		PrintWriter pw = null;
		BufferedReader in = null;
		// 增加access-token参数
		System.out.println("addr:" + addr);
		try {
			URL url = new URL(addr);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			uc.setConnectTimeout(2000); // 连接超时设置为2s
			uc.setReadTimeout(35000); // 读超时设置为35s
			uc.setDoOutput(true); // 因为这个是post请求，参数要放在http正文内，因此需要设为true，默认情况下是false
			uc.setUseCaches(false); // post请求不能使用缓存
			// 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			uc.setRequestProperty("Content-type", "text/xml;charset=GBK");
			uc.setRequestMethod("POST"); // 设定请求的方法为"POST"，默认是GET
			uc.connect();

			os = uc.getOutputStream();
			OutputStreamWriter out = new OutputStreamWriter(os, "GBK");
			pw = new PrintWriter(out);
			pw.print(xml);
			pw.flush();

			/* 获取服务器端返回信息 */
			in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "GBK"));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				rtn.append(inputLine).append("\r\n");
			}

		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			return "time_out";
		} catch (ConnectException e) {
			e.printStackTrace();
			return "connect_exception";
		} catch (Exception ex) {
			System.out.println("调用IMCC监控中心接口异常" + ex);
		} finally {
			try {
				if (pw != null) {
					pw.close();
					pw = null;
				}

				if (os != null) {
					os.close();
					os = null;
				}

				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception ex) {
				System.out.println("释放资源异常" + ex);
			}
		}
		System.out.println("mc ret:" + rtn.toString());
		log.warn("mc ret:" + rtn.toString());
		return rtn.toString();
	}

	/**
	 * 过滤出现在XML里的非打印字符
	 * 
	 * @param in
	 * @return
	 * @author
	 */
	public static String filtInvalidXMLChars(String in) {
		if (in == null || "".equals(in))
			return "";

		StringBuilder out = new StringBuilder();
		char current;

		for (int i = 0; i < in.length(); i++) {
			current = in.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

}
