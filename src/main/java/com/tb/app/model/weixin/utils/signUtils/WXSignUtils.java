package com.tb.app.model.weixin.utils.signUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import com.tb.app.common.utils.MD5;

public class WXSignUtils {

	public static String getSign(String appId,String appSecret,String key) {
		
		String preString = appId+appSecret+key;
		char[] preC = preString.toCharArray();
		
		Arrays.sort(preC);
		
		preC = ArrayUtils.addAll(preC, key.toCharArray());
		
		try {
			return MD5.MD5_16bit(new String(preC));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
}
