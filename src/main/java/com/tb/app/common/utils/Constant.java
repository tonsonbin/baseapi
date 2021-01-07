package com.tb.app.common.utils;

/**
 * @Description 静态字符串
 * @Author Benjamin
 * @CreateDate 2018-12-21 11:38
 **/
public class Constant {
	
	//=====================================公用参数====================================

    /*
     * 请求类型 自己app
     */
    public static final String REQ_APPKEY_APP_SELF = "A01";
    /*
     * 请求类型 swagger
     */
    public static final String REQ_APPKEY_SWAGGER = "SWAGGER01";

    /*
     * 请求类型 未知请求来源
     */
    public static final String REQ_APPKEY_UNKNOW = "0000";

    /**
     * 请求超时时间
     */
	public static final Long REQUEST_TIMEOUT = 30000l;
	
	/**
	 * 设置的attribute的name
	 */
	public static final String REQ_ATTR_USER = "REQ_ATTR_USER";
	
	
	//=====================================微信====================================

    /*
     * 微信appId
     */
    public static final String WX_APPID = "test";
    /*
     * 微信appSecret
     */
    public static final String WX_APPSECRET = "test";
    /*
     * 请求微信服务的key
     */
    public static final String WX_SERVERKEY = "test";
    
    //短信
	public static final String SMS_TYPE_BASE = "B01";
}
