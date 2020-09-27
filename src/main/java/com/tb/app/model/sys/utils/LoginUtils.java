package com.tb.app.model.sys.utils;

import org.apache.commons.lang3.StringUtils;

import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.web.ResultCode;
import com.tb.app.model.sys.entity.User;
import com.tb.app.model.sys.utils.tokenUtils.TokenUtils;

public class LoginUtils {

	/**
	 * 登录校验
	 * @param token
	 * @return
	 */
	public static User checkLogin(String token) {
		
		if (StringUtils.isBlank(token)) {
			throw new ServiceException(ResultCode.LOGIN_GETUSERINFO,"请登录");
		}
		
		//根据token获取缓存用户信息
		User user = TokenUtils.getUser(token);
		if (user == null) {
			throw new ServiceException(ResultCode.LOGIN_GETUSERINFO,"请登录");
		}
		
		return user;
	}
}
