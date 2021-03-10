package com.tb.app.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @Description yaml 读取微信相关配置
 * @author tangbin
 * @date 2021年3月9日
 */
@Configuration
public class YamlConfigWeixin {
	
    //appId
    private static String appId;
    //appSecret
    private static String appSecret;
    //商户号id
    private static String payMachId;
    //商户号key
    private static String payKey;
    //支付类型
    private static String payTradeType;
    //标价币种
    private static String payFeeType;
    //支付回调地址
    private static String payNotifyUrl;
    
	@Value("${weixin.appId}")
	public static void setAppId(String appId) {
		YamlConfigWeixin.appId = appId;
	}

	@Value("${weixin.appSecret}")
	public static void setAppSecret(String appSecret) {
		YamlConfigWeixin.appSecret = appSecret;
	}

	@Value("${weixin.payMachId}")
	public static void setPayMachId(String payMachId) {
		YamlConfigWeixin.payMachId = payMachId;
	}

	@Value("${weixin.payKey}")
	public static void setPayKey(String payKey) {
		YamlConfigWeixin.payKey = payKey;
	}

	@Value("${weixin.payTradeType}")
	public static void setPayTradeType(String payTradeType) {
		YamlConfigWeixin.payTradeType = payTradeType;
	}

	@Value("${weixin.payFeeType}")
	public static void setPayFeeType(String payFeeType) {
		YamlConfigWeixin.payFeeType = payFeeType;
	}

	@Value("${weixin.payNotifyUrl}")
	public static void setPayNotifyUrl(String payNotifyUrl) {
		YamlConfigWeixin.payNotifyUrl = payNotifyUrl;
	}
	
	public static String getPayNotifyUrl() {
		return payNotifyUrl;
	}

	public static String getPayFeeType() {
		return payFeeType;
	}

	public static String getPayTradeType() {
		return payTradeType;
	}

	public static String getPayKey() {
		return payKey;
	}

	public static String getPayMachId() {
		return payMachId;
	}

	public static String getAppSecret() {
		return appSecret;
	}

	public static String getAppId() {
		return appId;
	}

    
}
