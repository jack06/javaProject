package com.icloud.util.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.*;

/**
 * 1.该类是独立的类,除了依赖jar包,不依赖其他任何类
 * 2.在项目根目录下的config.properties配置
		send_msg_host=211.159.184.84:7003
		send_msg_hosttel=15709
		send_msg_hostnumber=gh_3d9742641f8b
		如果是其他位置修改"步骤一中的参数"

 * 3.只对外提供sendModelMessage(String jsonStr)方法
                                参数的的拼接本类中的main2方法
 * 4.方法返回结果如下
 *        0:模板消息发送成功
 *        其他:自己去微信JSSDK查看错误码
 */
public class WxSendModelMessageUtil {
	/**
	 * 只要传递按规则拼接好的字符串,就能发送模板消息
	 * 详情参考本类中的main2方法
	 */
	public static String   sendModelMessage(String jsonStr){
		//步骤一:加载配置文件(无需处理)
		//步骤二.获取access_token
		String access_token = getAccessToken();
		//步骤三.拼接访问字符串,得到链接结果
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		JSONObject jsonObject = httpRequest(url,"POST",jsonStr);
		//步骤四.发送消息,返回字符串
		/*
			System.out.println("模板消息发送结果:"+jsonObject );
			if( "ok".equals(jsonObject.getString("errmsg"))){
				System.out.println("发送ok");
			}else{
				System.out.println("发送失败");
			}
		 */
		return jsonObject.getString("errcode");
	}

	/**
	 * 步骤一:加载根目录下的config.properties文件
	 */
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");
	//获取配置信息的值
	private static final String getPropValByKey(String key) {
		return bundle.getString(key);
	}
	/**
	 * 步骤二
	 * 根据服务器信息,获取access_token
	 */
	private static String getAccessToken(){
		
		String host       =getPropValByKey("send_msg_host");
		String hosttel    =getPropValByKey("send_msg_hosttel");
		String hostnumber =getPropValByKey("send_msg_hostnumber");
		/*

		
			 测试服务器链接参数
			String host       ="211.159.184.84:7003";
			String hosttel    ="15709";
			String hostnumber ="gh_3d9742641f8b";	
			
			String host       ="119.29.25.86:7003";
			String hosttel    ="15709";
			String hostnumber ="gh_43224dd38126";	
			
			String host       ="119.29.25.36:7003";
			String hosttel    ="15709";
			String hostnumber ="gh_7d11c93a6da5";			 
	    */
		//获取access_token的访问地址
		String accessTokenAccessAddress = "http://"+host+"/mc/comm_protocol?hostel="+hosttel
				                                      +"&imtype=161"// 161代表微信
				                                      +"&hostnumber="+hostnumber
				                                      +"&waitret=true";		
		StringBuilder rtn = new StringBuilder();
		OutputStream os = null;
		PrintWriter pw = null;
		BufferedReader in = null;
		try {
			URL url = new URL(accessTokenAccessAddress);
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
			pw.print("gethostnumberinfo");
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
		//1.3获取请求返回的xml
		String xml = rtn.toString();// TOKENCONTENT
		//System.out.println("access_token返回数据xml=="+xml);
		int beginIndex = xml.indexOf("<apptoken>");
		int endIndex   = xml.indexOf("</apptoken>");
		String access_token = xml.substring(beginIndex, endIndex).replace("<apptoken>", "");
		//System.out.println("access_token=="+access_token);
		return access_token;		
	}
	/**
	 * 步骤三:发起https请求并获取结果
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject (通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;

		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化(证书过滤)
			TrustManager[] tm = { new MyX509TrustManager() };
			// 取得SSL的SSLContext实例
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			// 初始化SSLContext
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			/*
			 * if ("GET".equalsIgnoreCase(requestMethod)) httpUrlConn.connect();
			 */
			// 当有数据需要提交时(当outputStr不为null时，向输出流写数据)
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			//System.out.println("json is =="+buffer.toString());
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("连接超时: {}"+ ce.getMessage());
		} catch (Exception e) {
			System.out.println("https请求异常: {}"+ e.getMessage());
		}
		return jsonObject;
	}
	/**
	 * 参数拼接参考
	 */
	public static void main2(String[] args) {
		/**
		 该模板的来源:真龙测试环境"下单提醒"
		 {{first.DATA}}
			商品信息：{{keyword1.DATA}}
			消费金额：{{keyword2.DATA}}
			下单时间：{{keyword3.DATA}}
			配送信息：{{keyword4.DATA}}
			配送地址：{{keyword5.DATA}}
			{{remark.DATA}}
		 测试时:1.放开"步骤二中的: 测试服务器链接参数"
		      2.修改为自己的openid
		 */
		JSONObject jsonObj = new JSONObject();
		JSONObject data = new JSONObject();
		//发送数据
		//设置开头语
		JSONObject first = new JSONObject();
		first.put("value", "恭喜您小豆夺宝中奖了");// 开头语,默认黑色first.put("color", "#173177");
		//商品信息
		JSONObject keyword1 = new JSONObject();
		keyword1.put("value", "活动奖品");
		//消费金额
		JSONObject keyword2 = new JSONObject();
		keyword2.put("value", "开奖时间");
		//备注
		JSONObject remark = new JSONObject();
		remark.put("value", "请到真龙服务号【龙粉之家】-【我的乐豆】-【小豆夺宝中心领奖】");
		
		//数据嵌套
		data.put("first", first);
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		data.put("remark", remark);
		
		String openId  = "oREekjnGeLC2nh3tOs-sDpjiB2ns";
		String modelId = "YaWoZqey-1r7yYLTy4paS5JyXLoW5PztPniw_cdBH7s";
		
		jsonObj.put("template_id", modelId);
		jsonObj.put("url","http://www.baidu.com");
		jsonObj.put("topcolor", "#173177");
		jsonObj.put("touser", openId);
		jsonObj.put("data", data);
		
		//配置好请求的字符串直接请求
		String result= sendModelMessage(jsonObj.toString());
		if("0".equals(result)){
			System.out.println("模板消息发送成功");
		}else{
			System.out.println("模板消息发送失败!错误码为:"+result);
		}
	}		
}
