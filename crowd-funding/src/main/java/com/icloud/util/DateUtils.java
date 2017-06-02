package com.icloud.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


/**
 * 日期操作类，主要是提供一些静态方法实现String与Date对象的转换，以及String在不同格式之间的转换。 y--years M--months
 * d--days H--hours m--minutes s--seconds S--milliseconds
 * 
 * @author sandykin
 * @version 1.0
 */
public class DateUtils
{
  /**
   * 转换格式为yyyy-MM-dd HH:mm:ss:SSS。
   */
  public final static String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss:SSS";

  /**
   * 转换格式为yyyy-MM-dd HH:mm:ss。
   */
  public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

  /**
   * 转换格式为yyyy-MM-dd HH:mm。
   */
  public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

  /**
   * 转换格式为yyyy-MM-dd。
   */
  public final static String yyyy_MM_dd = "yyyy-MM-dd";

  /**
   * 转换格式为yyyy-MM-dd。
   */
  public final static String yyyy_MM = "yyyy-MM";
  
  /**
   * 转换格式为yyyy年MM月DD日
   */
  public final static String yyyynMMyddr = "yyyy年MM月dd日";
  /**
   * 转换格式为yyyy-M-d H:m:s:S
   */
  public final static String yyyy_M_d_H_m_s_S = "yyyy-M-d H:m:s:S";

  /**
   * 转换格式为yyyy-M-d H:m:s
   */
  public final static String yyyy_M_d_H_m_s = "yyyy-M-d H:m:s";

  /**
   * 转换格式为yyyy-M-d H:m
   */
  public final static String yyyy_M_d_H_m = "yyyy-M-d H:m";

  /**
   * 转换成格式为yyyy-M-d
   */
  public final static String yyyy_M_d = "yyyy-M-d";

  /**
   * 转换格式为yyyyMMdd HH:mm:ss:SSS。
   */
  public final static String yyyyMMdd_HH_mm_ss_SSS = "yyyyMMdd HH:mm:ss:SSS";

  /**
   * 转换格式为yyyyMMdd HH:mm:ss。
   */
  public final static String yyyyMMdd_HH_mm_ss = "yyyyMMdd HH:mm:ss";

  /**
   * 转换格式为yyyyMMdd HH:mm。
   */
  public final static String yyyyMMdd_HH_mm = "yyyyMMdd HH:mm";

  /**
   * 转换格式为yyyyMMdd。
   */
  public final static String yyyyMMdd = "yyyyMMdd";

  /**
   * 转换格式为yyyyMMddHHmmssSSS。
   */
  public final static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

  /**
   * 转换格式为yyyyMMddHHmmss。
   */
  public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";

  /**
   * 转换格式为yyyyMMddHHmm。
   */
  public final static String yyyyMMddHHmm = "yyyyMMddHHmm";

  /**
   * 转换格式为yyyy年M月d日H点m分。
   */
  public final static String yyyyMdHm_zh = "yyyy年M月d日H点m分";
  
  /**
   * 转换格式为yyyy年M月d日 HH:mm。
   */
  public final static String yyyyMdHHmm_zh = "yyyy年M月d日 HH:mm";

  /**
   * 将String类型的日期转换为Date对象。
   * 
   * @param dateString
   *          代表日期的字符串。
   * @param style
   *          转换格式。
   * @return 日期对象。
   * @throws ParseException
   *           日期字符串不满足指定转换格式时抛出的异常。
   */
  public static Date stringToDate(String dateString, String style)
      throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat(style, Locale.CHINESE);
    return format.parse(dateString);
  }

  /**
   * 将Date转换成为指定格式的String。
   * 
   * @param date
   *          日期对象。
   * @param style
   *          转换格式。
   * @return 代表日期的字符串。
   */
  public static String dateToString(Date date, String style)
  {
	if (null == date) return "";
	
    SimpleDateFormat format = new SimpleDateFormat(style, Locale.CHINESE);
    return format.format(date);
  }

  public static int getIntervalDays(Date startday,Date endday)
  {        
      if(startday.after(endday))
      {
          Date cal=startday;
          startday=endday;
          endday=cal;
      }        
      long sl=startday.getTime();
      long el=endday.getTime();       
      long ei=el-sl;           
      return (int)(ei/(1000*60*60*24));
  }

  /**
   * 将日期字符串从源格式转换成为目标格式。
   * 
   * @param dateString
   *          日期字符串。
   * @param sourceStyle
   *          源格式。
   * @param tagetStyle
   *          目标格式。
   * @return 转换成为目标格式后的字符串。
   * @throws ParseException
   *           转换格式与指定字符串不符时抛出的异常。
   */
  public static String stringToString(String dateString, String sourceStyle,
      String tagetStyle) throws ParseException
  {
    Date date = stringToDate(dateString, sourceStyle);
    return dateToString(date, tagetStyle);
  }

  /**
   * 返回某个日期的前几天或者后几天
   * 
   * @param date
   *          日期字符串
   * @param num
   *          前后多少天
   * @param dateStyle
   *          输入格式
   * @return
   * @throws ParseException
   */
  public static String getSomeDate(String dateString, int num, String dateStyle)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(dateStyle);
    Calendar day = Calendar.getInstance();
    try
    {
      day.setTime(sdf.parse(dateString));
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    day.add(Calendar.DATE, num);
    return sdf.format(day.getTime());
  }

  /**
   * 计算两个日期之间的天数
   * 
   * @param beginDate
   * @param endDate
   * @return
   */
  public static int daysOfTwoDate(Date beginDate, Date endDate, String dateStyle)
  {
    int days = 0;// 两个日期之间的天数
    DateFormat df = new SimpleDateFormat(dateStyle);

    Calendar beginCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();

    beginCalendar.setTime(beginDate);
    endCalendar.setTime(endDate);
    // 计算天数
    while (beginCalendar.before(endCalendar))
    {
      days++;
      beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
    }
    return days==0?1:days;
  }

  /**
   * 根据输入两个日期,获取这段时间内对应的月份列表
   * 
   * @param beginDate
   * @param endDate
   * @return
   */
  public static List getListMonth(Date beginDate, Date endDate)
  {
    List list = new ArrayList();
    Calendar beginCal = Calendar.getInstance();
    Calendar endCal = Calendar.getInstance();
    endCal.setTime(endDate);
    beginCal.setTime(beginDate);
    while (beginCal.before(endCal))
    {
      list.add(format(beginCal.getTime(),"yyyy-MM"));
      beginCal.add(Calendar.MONTH, 1);
    }
    if(beginCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH))
    {
      list.add(format(beginCal.getTime(),"yyyy-MM"));
    }
    return list;
  }
  
  public static String getSystemTime()
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.SIMPLIFIED_CHINESE);
    return df.format(new Date()).toString();  
  }
  
  public static String getNowTime()
  {
      Calendar calendar = new GregorianCalendar();
      Date date = calendar.getTime();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
      return  format.format(date);
  }
  
  /**
   * 
   * @param dateString
   * @param dateStyle
   * @return
   */
  public static Calendar getCalendarDate(String dateString,String dateStyle)
  {
	  SimpleDateFormat sdf = new SimpleDateFormat(dateStyle);
	  
	  Calendar day = Calendar.getInstance();
	  try
	  {
		  Date date = sdf.parse(dateString);
		  date.setTime(date.getTime()+ 8*60*60*1000);
		  day.setTime(date);
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  return day;
  }
  
  /**
   * 时间差计算
   * @param beforeTime  比较前时间
   * @param currentTime 当前系统时间
   * @param time        时间差值
   * @return
   */
  public static boolean dateCompare(String beforeTime,String currentTime,int time){
    try{
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.SIMPLIFIED_CHINESE);
      boolean temp = false;
      Date begin=df.parse(beforeTime);
      Date end = df.parse(currentTime);
      long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
      long day=between/(24*3600);
      long hour=between%(24*3600)/3600;
      long minute=between%3600/60;
      long second=between%60/60;
      if((hour==0)&&(day==0)&&(minute<=time)) 
      {
        temp = true;
      }
      return temp; 
    }
    catch(ParseException e){
      return false;
    }
  }
  
  /**
   * DOUBLE 数据类型四舍五入
   * @param num 保留小数点后几位
   * @param value 
   * @return
   */
  public static double changeDecimal(int num,double value){   
    BigDecimal   b=   new   BigDecimal(value);   
    double   v=b.setScale(num,BigDecimal.ROUND_HALF_UP).doubleValue();//   表明四舍五入，保留两位小数   
    return   v;   
    }
  
  /** 根据加减天数计算日期
 * @param nativeDate 需要计算的日期
 * @param calMon 加减天数
 * @return 计算后的日期
 */
public static String getSpecificDateByDay(Date nativeDate, int calDay){
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(nativeDate);
	  cal.add(Calendar.DATE, calDay);
	
	  Date specificDate = cal.getTime();
	  String specificDateStr = DateUtils.dateToString(specificDate, "yyyy-MM-dd");
	
	  return specificDateStr;
  }

    public static String format(Date date, String format)
    {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    
    
    /**
  　　 * 获得指定时间"i"小时前/后的时间，格式化成yyyy-MM-dd HH:mm:ss<br>
  　　 * 
  　　 * @return 指定时间i小时前/后的时间
  　　 */
    public static String getIntervalTime(Date nativeDate, int i) {
	  	String ret = "";
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(nativeDate);
  		cal.add(Calendar.HOUR, i);
  		ret = dateToString(cal.getTime(), yyyy_MM_dd_HH_mm_ss);
		return ret;
	} 
    
    
    /**
  　　 * 获得指定年月"i"小时前/后的年月，格式化成yyyyMM<br>
  　　 * 
  　　 * @return 
  　　 */
    public static String getLastNMonth(String nativeMonth, int i, String style) {
    	String ret = "";
		try {
			Date day = stringToDate(nativeMonth, style);
			
	  		Calendar cal = Calendar.getInstance();
	  		cal.setTime(day);
	  		cal.add(Calendar.MONTH, i);
	  		ret = dateToString(cal.getTime(), style);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	
		return ret;
	}
    
    
    /** 获取指定日期前一月的日期
     * @param nativeDate 需要计算的日期
     * @return 计算后的日期
     */
    public static String getLastMonthDay(Date nativeDate){
    	  Calendar cal = Calendar.getInstance();
    	  cal.setTime(nativeDate);
    	  cal.add(Calendar.MONTH, -1);
    	
    	  Date specificDate = cal.getTime();
    	  String specificDateStr = DateUtils.dateToString(specificDate, "yyyy-MM-dd");
    	
    	  return specificDateStr;
	}
    
    
    /** 获取指定日期 所在周的第一天的日期
     * @param nativeDate 需要计算的日期
     * @return 计算后的日期
     */
    public static String getWeekFirstDay(Date nativeDate){
    	  Calendar cal = Calendar.getInstance();
    	  cal.setTime(nativeDate);
    	  /*从周日开始算起
    	   * int weekday = cal.get(7)-1;  
    	  cal.add(5,-weekday);  */
    	  int weekday = cal.get(7)-2;  
    	  cal.add(5,-weekday);
    	  Date specificDate = cal.getTime();
    	  String specificDateStr = DateUtils.dateToString(specificDate, "yyyy-MM-dd");
    	
    	  return specificDateStr;
	}
    
    /** 获取指定日期 所在周的最后一天的日期
     * @param nativeDate 需要计算的日期
     * @return 计算后的日期
     */
    public static String getWeekLastDay(Date nativeDate){
    	  Calendar cal = Calendar.getInstance();
    	  cal.setTime(nativeDate);
    	  //*从周日开始算起
    	  //int weekday = cal.get(7)-1;  
    	  int weekday = cal.get(7)-2;  
    	  cal.add(5,-weekday);  
    	  //System.out.println("本周开始时间："+cal.getTime());  
    	  cal.add(5,6);
    	  Date specificDate = cal.getTime();
    	  String specificDateStr = DateUtils.dateToString(specificDate, "yyyy-MM-dd");
    	
    	  return specificDateStr;
	}
    
    
    /** 
     * 获取指定日期月份的最后一天日期
     * @param currDay 指定日期
     * @return 
     */
    public static String getMonthLastDay(Date currDay){
    	  Calendar cal = Calendar.getInstance();
    	  cal.setTime(currDay);
    	  int endday = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
    	  cal.set(Calendar.DAY_OF_MONTH, endday);
    	  Date specificDate = cal.getTime();
    	  String specificDateStr = DateUtils.dateToString(specificDate, "yyyy-MM-dd");
    	
    	  return specificDateStr;
      }
    
    /** 
     * 获取指定日期月份的第一天日期
     * @param currDay 指定日期
     * @return 
     */
    public static String getMonthFirstDay(Date currDay){
    	Calendar cal = Calendar.getInstance();
  	  	cal.setTime(currDay);
  	  	cal.set(Calendar.DAY_OF_MONTH, 1);
  	  	Date specificDate = cal.getTime();
  	  	String specificDateStr = DateUtils.dateToString(specificDate, "yyyy-MM-dd");
  	  	return specificDateStr;
    }
    
    
    /**
     * 根据输入两个日期,获取这段时间内对应的日期列表
     * 
     * @param beginDate
     * @param endDate
     * @param filter  是否过滤掉周末
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<String> getListDays(String strStartDate,String strEndDate, boolean filter) {
    	List<String> ret = new ArrayList<String>();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date startDate=null;
	    Date endDate = null;
	
	    try {
		    startDate=df.parse(strStartDate);
		    endDate = df.parse(strEndDate);
	    } catch (ParseException e) {
	    	System.out.println("非法的日期格式,无法进行转换");
	    	e.printStackTrace();
	    }
	    while (startDate.compareTo(endDate) <= 0) {
	    	if(filter){
	    		if (startDate.getDay() != 6 && startDate.getDay() != 0){
		    		continue;
		    	}
	    	}
	    	//System.out.println(df.format(startDate));
	    	ret.add(df.format(startDate));
	    	startDate.setDate(startDate.getDate() + 1);
	    }
	    return ret;
    }
    
    
    /**
     * 获取当前日期和三个月前的日期 用;分隔
     * @return
     */
    public static String nowAndBefor3Month(){
    	Date nowDate = new Date();
    	String nowDateStr = new SimpleDateFormat("yyyy-MM-dd").format(nowDate);
    	Calendar   cal=Calendar.getInstance(); 
    	cal.setTime(nowDate); 
    	cal.add(Calendar.MONTH,-3);
    	Date before3moth = cal.getTime();
    	String before3monthStr = new SimpleDateFormat("yyyy-MM-dd").format(before3moth);
    	
    	return nowDateStr+";"+before3monthStr;
    }
    
    
    /**
     * 获取当前时间前一小时
     * @return
     */
    public static String getOneHoursAgoTime(String style) {
        String oneHoursAgoTime = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, Calendar.HOUR-8);//设置时间为当前时间-8小时
        oneHoursAgoTime = new SimpleDateFormat(style).format(cal.getTime());
        return oneHoursAgoTime;
    }
    /**
     * 根据星期获取指定时间段的日期
     * @param startDate 指定开始时间
     * @param endDate 指定结束时间
     * @param week 星期数 0-6
     * @return String类型时间集合
     */
    public static List<String> getDateStr(String startDate,String endDate,String[] week){
		List<String> dateList=new ArrayList<String>();
		List<String> list=getListDays(startDate, endDate,false);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		try {
			for(String day:list){
				Date date= sdf.parse(day);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				for(int i=0;i<week.length;i++){
					if(String.valueOf(calendar.get(calendar.DAY_OF_WEEK)-1).equals(week[i])){
						dateList.add(day);
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateList;
	}
    
    /**
     * 是否处于时间范围内
     * @param startDate
     * @param endDate
     * @param nowDate
     * @return
     */
    public static boolean isInTheTimeRange(Date startDate, Date endDate, Date nowDate){
    	Long startTime = startDate.getTime();
		Long endTime = endDate.getTime();
		Long nowTime = nowDate.getTime();
		if(nowTime > startTime && nowTime < endTime){
			return true;
		}else{
			return false;
		}
    }
    
}
