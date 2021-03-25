package com.tb.app.model.sys.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.web.Result;
import com.tb.app.model.sys.service.LoginService;


/**
 * 登录
 * @author tangbin
 * @date 2020年9月23日
 */
@RequestMapping("${apiPath}/sys/login")
@RestController
public class LoginApiController {

	@Resource
	private LoginService loginService;
	
    /**
     * 登录-根据手机号和手机验证码
     * @param app
     * @return
     */
    @PostMapping()
    @RequestMapping("unauth/byPhone")
    public Result loginByPhone(String mobile,String verifyCode) {
    	
    	Result result = loginService.loginByPhone(mobile, verifyCode);
    	
        return result;
    }
    
    /**
     * 登录-根据手机号和手机验证码
     * @param app
     * @return
     */
    @PostMapping()
    @RequestMapping("unauth/byPassword")
    public Result byPassword(String loginName,String password) {
    	
    	Result result = loginService.byPassword(loginName, password);
    	
        return result;
    }
}
