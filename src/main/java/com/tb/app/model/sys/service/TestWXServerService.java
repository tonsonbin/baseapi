package com.tb.app.model.sys.service;

import org.springframework.stereotype.Service;

import com.tb.app.common.web.Result;
import com.tb.app.model.sys.utils.weixin.WXAccessToken;

@Service
public class TestWXServerService {

	
	/**
	 * 获取token从wx服务
	 * @return
	 */
	public Result getAccessToken() {
		
		
		return WXAccessToken.get();
	}
	
	
}
