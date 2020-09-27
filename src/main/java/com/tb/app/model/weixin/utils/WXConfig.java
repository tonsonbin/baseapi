package com.tb.app.model.weixin.utils;

import java.util.Map;

import com.google.common.collect.Maps;

public class WXConfig {

	/**
	 * sign的key
	 */
	public static final String APPID_TEST = "test";
	public static final String APPSECRET_TEST = "test";
	public static final String WXSIGN_KEY_TEST = "nowiev_2n0f23n_c3@nfe092";
	
	public static Map<String, String> KEYS; 
	
	//配置可用的appId信息
	static{
		
		KEYS = Maps.newHashMap();
		KEYS.put(getKeyId(APPID_TEST, APPSECRET_TEST), WXSIGN_KEY_TEST);
		
	}
	
	/**
	 * 获取key
	 * @param appId
	 * @param secret
	 * @return
	 */
	public static String getKey(String appId,String secret) {
		
		String keyId = getKeyId(appId, secret);
		
		if (KEYS.containsKey(keyId)) {
			
			return KEYS.get(keyId);
			
		}
		
		return null;
		
	}
	
	
	/**
	 * 获取缓存的keyId
	 * @param appId
	 * @param secret
	 * @return
	 */
	private static String getKeyId(String appId,String secret) {
		
		return appId+"_"+secret;
	}
}
