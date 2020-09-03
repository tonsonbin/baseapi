package com.tyfo.app.common.utils;

import java.util.Calendar;

/**
 * 时间处理
 * @author Administrator
 *
 */
public class FormatDate {
	public int year;
	public int month;
	public int day;
	public int hours;
	public int minutes;
	public int seconds;
	public int week;
	private static FormatDate fd;
	private FormatDate(){
		Calendar cal=Calendar.getInstance();
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		day=cal.get(Calendar.DAY_OF_MONTH);
		hours=cal.get(Calendar.HOUR_OF_DAY);
		minutes=cal.get(Calendar.MINUTE);
		seconds=cal.get(Calendar.SECOND);
		week=cal.get(Calendar.DAY_OF_WEEK);
	}
	public Calendar getCalendar(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal;
	}
	/**
	 * 以初始化为当前时间
	 */
	public static FormatDate getInstance(){
		if(fd==null){
			fd=new FormatDate();
		}
		return fd;
	}
	public FormatDate init(){
		fd=new FormatDate();
		return fd;
	}
	/**
	 * 初始化为给定时间
	 * @param cal
	 */
	public FormatDate init(Calendar cal){
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		day=cal.get(Calendar.DAY_OF_MONTH);
		hours=cal.get(Calendar.HOUR_OF_DAY);
		minutes=cal.get(Calendar.MINUTE);
		seconds=cal.get(Calendar.SECOND);
		week=cal.get(Calendar.DAY_OF_WEEK);
		return fd;
	}
	/**
	 * 获取当前系统时间，若是月份不足10则将月份变为"0x"形式
	 * @return
	 */
	public String getType1Month(){
		String months=String.valueOf(month);
    	if((month*10)<100){
    		months="0"+months;
    	}
    	return months;
	}
	/**
	 * 将传入的月份若不足10则转为"0x"
	 * @param month
	 * @return
	 */
	public static String getType1Month(int month){
		String months=String.valueOf(month);
    	if((month*10)<100){
    		months="0"+months;
    	}
    	return months;
	}
	/**
	 * 获取当前系统时间，若是日不足10则将日变为"0x"形式
	 * @return
	 */
	public String getType1Day(){
    	String days=String.valueOf(day);
    	if(day*10<100){
    		days="0"+days;
    	}
    	return days;
	}
	/**
	 * 将传入的日若不足10则转为"0x"
	 * @param day
	 * @return
	 */
	public static String getType1Day(int day){
    	String days=String.valueOf(day);
    	if(day*10<100){
    		days="0"+days;
    	}
    	return days;
	}
	/**
	 * 设置为前/后几月的时间
	 * @return
	 */
	public FormatDate setDisMonth(int disMonth){
		Calendar cal=fd.getCalendar();;
		cal.add(Calendar.MONTH, disMonth);
		init(cal);
		return fd;
	}
	/**
	 *  初始化为给定月时间的前/后几月的时间
	 * @param month
	 * @param disMonth
	 * @return
	 */
	public FormatDate setDisMonth(int month,int disMonth){
		Calendar cal=fd.getCalendar();;
		cal.set(Calendar.MONTH, month);
		cal.add(Calendar.MONTH, disMonth);
		init(cal);
		return fd;
	}
	/**
	 * 设置为前/后几日的时间
	 * @return
	 */
	public FormatDate setDisDay(int disDay){
		Calendar cal=fd.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, disDay);
		init(cal);
		return fd;
	}
	/**
	 *  设置为给定日时间的前/后几日的时间
	 * @param month
	 * @param disMonth
	 * @return
	 */
	public FormatDate setDisDay(int day,int disDay){
		Calendar cal=fd.getCalendar();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.add(Calendar.DAY_OF_MONTH, disDay);
		init(cal);
		return fd;
	}
	/**
	 * 设置时间为当前给定时间的相差一定的时间的时间
	 * @param year
	 * @param month
	 * @param day
	 * @param disYear
	 * @param disMonth
	 * @param disDay
	 */
	public FormatDate setDisTime(int year,int month,int day,int disYear,int disMonth,int disDay){
		Calendar cal=fd.getCalendar();
		if(year!=0){
			cal.set(Calendar.YEAR, year);
		}
		if(month!=0){
			cal.set(Calendar.MONTH, month-1);
		}
		if(day!=0){
			cal.set(Calendar.DAY_OF_MONTH, day);
		}
		cal.add(Calendar.YEAR, disYear);
		cal.add(Calendar.MONTH, disMonth);
		cal.add(Calendar.DAY_OF_MONTH, disDay);
		init(cal);
		return fd;
	}
	/**
	 * 格式化时间格式为天时分秒的形式
	 * @param souTime 以秒计的时间
	 * @return
	 */
	public static String formatTime(long souTime){
		String res="";
		 if(souTime>24*60*60){//在天以上
			 int day=(int)Math.floor(souTime/(24*60*60));
		 	 souTime=souTime-day*24*60*60;
		 	 res+=day+"天";
		 }
		 if(souTime>60*60){//在小时以上
			 int hour=(int)Math.floor(souTime/(60*60));
		 	 souTime=souTime-hour*60*60;
		 	 res+=hour+"小时";
		 }
		 if(souTime>60){//在分以上
			 int min=(int)Math.floor(souTime/60);
		 	 souTime=souTime-min*60;
		 	 res+=min+"分";
		 }
		 res+=souTime+"秒";
		 return res;
	}
	public FormatDate display(){
		System.out.println(year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds+"-"+week);
		return fd;
	}
	public String toString(){
		return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
	}
	public static void main(String[] args){
		FormatDate.getInstance().setDisTime(1992, 8, 19, 1, 5, 1).display().setDisDay(9).display().setDisDay(5, 2).display().init().display();
	}
}
