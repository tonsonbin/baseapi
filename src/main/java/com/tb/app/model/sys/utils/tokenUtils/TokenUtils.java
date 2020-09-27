package com.tb.app.model.sys.utils.tokenUtils;

import java.security.NoSuchAlgorithmException;

import com.tb.app.common.security.IdGen;
import com.tb.app.common.utils.EhCacheUtil;
import com.tb.app.common.utils.MD5;
import com.tb.app.model.sys.entity.User;

public class TokenUtils {

	/**
	 * 获取token 
	 * @param user
	 * @return
	 */
	public static String getToken(User user) {
		
		String token = "";
		try {
			token = MD5.MD5_16bit(IdGen.uuid());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EhCacheUtil.put(EhCacheUtil.COMMON_CACHE, token, user);
		
		return token;
	}
	
	/**
	 * 根据登录的token取用户信息，如果返回的不为空则是有效的token
	 * @param user
	 * @return
	 */
	public static User getUser(String token) {
		
		Object object = EhCacheUtil.get(EhCacheUtil.COMMON_CACHE, token);
		if (object == null) {
			
			return null;
			
		}
		
		User user = (User) object;
		
		return user;
	}
}
