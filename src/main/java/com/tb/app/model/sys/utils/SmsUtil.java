package com.tb.app.model.sys.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 短信接口工具类
 *              <p>
 *              File:com.sctel.soa.SmsUtil.java
 *              </p>
 * @date 2016-5-9 下午2:47:32
 * @version 1.0
 * @author wj
 */
public class SmsUtil {
	
	//短信发送后的间隔时间（秒）
	public final static Integer SEND_CODE_WAIT_SECOND = 300;
	
	/************************短信配置*******************************/ 
	public final static String SEND_URL = "";

	private static Logger log4j = LoggerFactory.getLogger(SmsUtil.class);
	
	/**
	 * @Description 返回值：是否发送成功
	 * @param phoneNum 电话号码
	 * @param smsContent 发送内容
	 * @return
	 */
	public static boolean sendSms(String phoneNum, String smsContent) {
		
		return true;
		
	}
	
}
