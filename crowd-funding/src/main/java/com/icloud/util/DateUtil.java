package com.icloud.util;

import java.text.SimpleDateFormat;
/**
 * 空格和:不表示,中划线-用_下划线表示
 * 直接调用sdf.format(date); 日期转字符
 * 直接调用sdf.parse(dstr);  字符转日期
 */
public class DateUtil {
	//格式yyyy-MM-dd HH:mm:ss == yyyy_MM_ddHHmmss
	public static SimpleDateFormat yyyy_MM_ddHHmmss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
}
