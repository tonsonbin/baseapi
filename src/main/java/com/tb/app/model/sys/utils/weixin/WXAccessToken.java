package com.tb.app.model.sys.utils.weixin;

import org.apache.commons.lang3.StringUtils;

import com.tb.app.common.YamlConfig;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.utils.Constant;
import com.tb.app.common.utils.httpSend.HttpSend;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.weixin.utils.signUtils.WXSignUtils;

import net.sf.json.JSONObject;

public class WXAccessToken {

	/**
	 * 获取accessToken服务
	 */
	private static final String URL_ACCESSTOKEN_GET = "/accessToken/unauth/get";
	
	public Result get() {
		
		String appId = Constant.WX_APPID;
		String appSecret = Constant.WX_APPSECRET;
		String key = Constant.WX_SERVERKEY;
		
		String sign = WXSignUtils.getSign(appId, appSecret, key);
		if (StringUtils.isBlank(sign)) {
			return ResultGenerator.genFailResult("sign生成失败！");
		}
		
		String res = HttpSend.postSend(YamlConfig.getServerWXPath()+URL_ACCESSTOKEN_GET, "appId="+appId
				+"&secret="+appSecret
				+"&sign="+sign, "从服务端获取accessToken");
		
		try {
			
			Result result = (Result) JSONObject.toBean(JSONObject.fromObject(res),Result.class);
			
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("请求accessToken失败！");
		}
		
	}
}
