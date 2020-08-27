package com.tyfo.app.common.utils;

/**
 * @Description 静态字符串
 * @Author Benjamin
 * @CreateDate 2018-12-21 11:38
 **/
public class Constant {
	
	//=====================================公用参数====================================

    /*
     * 请求类型 自己微信端app
     */
    public static final String REQ_APPKEY_WXAPP_SELF = "0";
	/*
     * 请求类型 非通
     */
    public static final String REQUEST_TYPE_NON = "1";
    
    /*
     * 请求类型 云商soa
     */
    public static final String REQ_APPKEY_SOA = "2";
    

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
}
