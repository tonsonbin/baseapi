package com.tb.app.model.weixin.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolsn.common.weixin.common.WXImplResult;
import com.coolsn.modules.tb.judgeParams.JudgeParams;
import com.coolsn.modules.tb.judgeParams.JudgeParamsResult;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.weixin.utils.WXConfig;
import com.tb.app.model.weixin.utils.signUtils.WXSignUtils;
import com.tb.app.model.weixin.utils.weixin.GetAccessToken;


/**
 * Created by CodeGenerator on 2019/05/07.
 */
@Service
@Transactional(readOnly = true)
public class WXAccessTokenService{

	/**
	 * 
	 * 获取accessToken
	 * 
	 * 用于公共accessToken服务，不能本地使用
	 * 
	 * @param appId
	 * @param appSecret
	 * @param sign 
	 * @return
	 */
    public Result getAccessToken(String appId,String appSecret, String sign) {
    	
    	//校验参数
    	JudgeParamsResult judgeParamsResult = new JudgeParams()
    			.addParams("appId", appId)
    			.addParams("appSecret", appSecret)
    			.addParams("sign", sign)
    			.verify();
    	
    	if (!judgeParamsResult.success()) {
			throw new ServiceException(judgeParamsResult.getMessage());
		}
    	
    	//校验签名
    	String signKey = WXConfig.getKey(appId, appSecret);
    	if (StringUtils.isBlank(signKey)) {
			
    		throw new ServiceException("服务端校验失败，非法appId，请联系管理员配置！");
    		
		}
    	String newSign = WXSignUtils.getSign(appId, appSecret, signKey);
    	if (StringUtils.isBlank(newSign)) {
			throw new ServiceException("服务端sign生成失败，请重试！");
		}
    	
    	if (!newSign.equals(sign)) {
			throw new ServiceException("签名校验失败！");
		}
    	
    	//校验通过
    	WXImplResult wxImplResult = GetAccessToken.get(appId, appSecret);
    	
    	if (!wxImplResult.success()) {
			throw new ServiceException(wxImplResult.getException());
		}
    	
        return ResultGenerator.genSuccessResult(wxImplResult.getData());
    }
}
