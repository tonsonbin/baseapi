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
     * 请求类型 第三方01类型
     */
    public static final String REQ_APPKEY_O1 = "O01";

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

    
    /*
     * 服务信息加密KEY
     */
    public static final String SERVERINFO_E_KEY = "2n30f02nNFO2NI@J";
	
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
	
	//==================================机构=========================================//
	/**
	 * 机构-顶级机构id
	 */
	public static final String OFFICE_ROOT_ID = "1";
	

	//==================================账号登录状态=========================================//
	/**
	 * 账号登录状态-正常
	 */
	public static final String USER_LOGINFLAG_NORMAL = "1";
	/**
	 * 账号登录状态-禁用
	 */
	public static final String USER_LOGINFLAG_FORBIDDEN = "0";
	

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
}
