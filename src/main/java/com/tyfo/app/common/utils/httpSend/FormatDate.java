package com.tyfo.app.common.utils.httpSend;

import java.util.Calendar;

public class FormatDate {
	public static int year;
	public static int month;
	public static int day;
	public static int hours;
	public static int minutes;
	public static int seconds;
	public FormatDate(){
		Calendar cal=Calendar.getInstance();
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		day=cal.get(Calendar.DAY_OF_MONTH);
		hours=cal.get(Calendar.HOUR_OF_DAY);
		minutes=cal.get(Calendar.MINUTE);
		seconds=cal.get(Calendar.SECOND);
	}
	/**
	 * 获取当前月"0x"格式
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
	 * 格式化月"0x"
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
	 * 去当前日"0x"格式
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
	 * 格式化日"0x"
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
	public static int getBeforeDay(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	public String normalDate() {
		
		return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		
	}
}
