package com.tb.app.model.weixin.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.User;
import com.tb.app.model.sys.service.LoginService;
import com.tb.app.model.sys.service.UserService;
import com.tb.app.model.sys.utils.tokenUtils.TokenUtils;
import com.tb.app.model.weixin.service.WeChatLoginService;

/**
 * @Author:tb
 * @Description:微信小程序登录控制器
 * @Data:Created in 2020/5/27
 */
@RequestMapping("${apiPath}/wx/login/unauth")
@RestController
public class WXLoginController {

    @Resource
    private WeChatLoginService weChatLoginService;
    @Resource
    private LoginService loginService;

    /**
     * 取微信用户信息的登录
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    @PostMapping()
    @RequestMapping("/wxappLogin")
    @ResponseBody
    public Result wxappLogin(String encryptedData,String iv,String code) {
    	
        Result loginResult = weChatLoginService.login(encryptedData, iv, code);
        if (!loginResult.isSuccess()) {
			return loginResult;
		}
        
        //信息入库成功，取token
        return loginService.getTokenByPhone((String)loginResult.getData());
    }

    @ResponseBody
    @PostMapping()
    @RequestMapping("/wxappRefreshToken")
    public Result wxappRefreshToken(String code) {

    	Result loginResult = weChatLoginService.wxappRefreshToken(code);
    	if (!loginResult.isSuccess()) {
			return loginResult;
		}
        
        //信息入库成功，取token
        return loginService.getTokenByPhone((String)loginResult.getData());
    	
    }

    /**
     * 以手机号授权的方式登录
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    @ResponseBody
    @PostMapping()
    @RequestMapping("/wxappBindPhone")
    public Result wxappBindPhone(String encryptedData, String iv, String code) {
        
    	Result loginResult = weChatLoginService.wxappBindPhone(encryptedData, iv, code);
    	if (!loginResult.isSuccess()) {
			return loginResult;
		}
        
        //信息入库成功，取token
        return loginService.getTokenByPhone((String)loginResult.getData());
            
    }

}
