package com.tb.app.model.sys.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.service.SmsService;


/**
 * 短信
 */
@RequestMapping("${apiPath}/sys/sms")
@RestController
public class SmsController {
	
	@Resource
	SmsService smsService;
	
    /**
     * 
     * 发送验证码
     * 
     * @param mobile 手机号
	 * @param type 发送类型
			0 添加绑定手机号 E平台占用
			1 修改支付密码 E平台占用
			NC01 本系统登录验证码
			NC02 本系统下单验证码
     * @return
     */
    @RequestMapping("/unauth/sendVerifyCode")
    public Result send(String mobile,String type,String imageCode,String verifyKey) {
    	
    	smsService.sendCode(mobile,type,imageCode,verifyKey);
    	
    	return ResultGenerator.genSuccessResult();
    }
    
}
