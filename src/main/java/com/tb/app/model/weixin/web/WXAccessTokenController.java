package com.tb.app.model.weixin.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.web.Result;
import com.tb.app.model.weixin.service.WXAccessTokenService;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${apiPath}/weixin/accessToken")
@RestController
public class WXAccessTokenController {
	
	@Resource
	private WXAccessTokenService wxAccessTokenService;

    /**
     * 需要校验签名
     * @param app
     * @return
     */
    @PostMapping()
    @RequestMapping("/unauth/get")
    public Result unauthget(String appId,String secret,String sign) {
    	
        return wxAccessTokenService.getAccessToken(appId, secret,sign);
    }
}
