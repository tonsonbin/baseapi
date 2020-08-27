package com.tyfo.app.model.sys.utils;

import org.apache.commons.lang3.StringUtils;

import com.tyfo.app.common.exception.ServiceException;
import com.tyfo.app.common.web.Result;
import com.tyfo.app.common.web.ResultCode;
import com.tyfo.app.model.sys.entity.User;

public class LoginUtils {

	/**
	 * 登录校验
	 * @param token
	 * @return
	 */
	public static User checkLogin(String token) {
		
		if (StringUtils.isBlank(token)) {
			throw new ServiceException(new Result().setCode(ResultCode.LOGIN_GETUSERINFO).setMessage("请登录").toString());
		}
		
		User user = new User();
		
		return user;
	}
}
