package com.tb.app.model.sys.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolsn.modules.tb.judgeParams.JudgeParams;
import com.coolsn.modules.tb.judgeParams.JudgeParamsBean;
import com.coolsn.modules.tb.judgeParams.JudgeParamsConfig;
import com.coolsn.modules.tb.judgeParams.JudgeParamsResult;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.utils.Constant;
import com.tb.app.common.utils.passUtils.PassUtils;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.User;
import com.tb.app.model.sys.utils.tokenUtils.TokenUtils;

import net.sf.json.JSONObject;


/**
 * Created by CodeGenerator on 2019/05/07.
 */
@Service
@Transactional(readOnly = true)
public class LoginService{
	
	@Resource
	SmsService smsService;
	@Resource
	UserService userService;

	/**
	 * 根据手机号登录
	 * @param mobile
	 * @param verifyCode
	 * @return
	 */
	@Transactional(readOnly = false)
    public Result loginByPhone(String mobile,String verifyCode) {

    	//校验参数
    	JudgeParamsResult judgeParamsResult = new JudgeParams()
    			.addParamsBean(new JudgeParamsBean("手机号", mobile).type(JudgeParamsConfig.TYPE_PHONE))
    			.addParams("手机验证码", verifyCode)
    			.verify();
    	
    	if (!judgeParamsResult.success()) {
			throw new ServiceException(judgeParamsResult.getMessage());
		}
    	
    	//校验验证码
    	smsService.verificationCode(mobile, verifyCode, Constant.SMS_TYPE_BASE);
    	
    	return getTokenByPhone(mobile);
	}

	/**
	 * 根据手机号登录取token
	 * @param mobile
	 * @return
	 */
	@Transactional(readOnly = false)
    public Result getTokenByPhone(String mobile) {

    	//校验参数
    	JudgeParamsResult judgeParamsResult = new JudgeParams()
    			.addParamsBean(new JudgeParamsBean("手机号", mobile).type(JudgeParamsConfig.TYPE_PHONE))
    			.verify();
    	
    	if (!judgeParamsResult.success()) {
			throw new ServiceException(judgeParamsResult.getMessage());
		}
    	
    	Result userResult = userService.getByMobile(mobile);
    	if (!userResult.isSuccess()) {
			return userResult;
		}
    	
    	
    	//获取token
    	String token = TokenUtils.getToken((User)userResult.getData());

    	return ResultGenerator.genSuccessResult(new JSONObject().element("token", token));
	}
	/**
	 * 根据登录名和登录密码登录
	 * @param loginName
	 * @param password
	 * @return
	 */
	public Result byPassword(String loginName, String password) {
		
		//校验参数
    	JudgeParamsResult judgeParamsResult = new JudgeParams()
    			.addParams("登录账号", loginName)
    			.addParams("登录密码", password)
    			.verify();
    	
    	if (!judgeParamsResult.success()) {
			throw new ServiceException(judgeParamsResult.getMessage());
		}
    	
    	//根据登录名取用户信息
    	Result userResult = userService.getByLoginName(loginName);
    	if (!userResult.isSuccess()) {
			return userResult;
		}
    	User user = (User)userResult.getData();
    	
    	//校验密码
    	try {
			PassUtils.equalsPass(user.getPassword(), password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultGenerator.genFailResult("密码错误！");
		}
    	
    	//获取token
    	String token = TokenUtils.getToken(user);

    	return ResultGenerator.genSuccessResult(new JSONObject().element("token", token));
	}
	
}
