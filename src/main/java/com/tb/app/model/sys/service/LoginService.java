package com.tb.app.model.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolsn.modules.tb.judgeParams.JudgeParams;
import com.coolsn.modules.tb.judgeParams.JudgeParamsBean;
import com.coolsn.modules.tb.judgeParams.JudgeParamsConfig;
import com.coolsn.modules.tb.judgeParams.JudgeParamsResult;
import com.tb.app.common.exception.ServiceException;
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

    public Result loginByPhone(String phone,String verifyCode) {

    	//校验参数
    	JudgeParamsResult judgeParamsResult = new JudgeParams()
    			.addParamsBean(new JudgeParamsBean("手机号", phone).type(JudgeParamsConfig.TYPE_PHONE))
    			.addParams("手机验证码", verifyCode)
    			.verify();
    	
    	if (!judgeParamsResult.success()) {
			throw new ServiceException(judgeParamsResult.getMessage());
		}
    	
    	User user = new User();
    	user.setLoginName(phone);
    	
    	//获取token
    	String token = TokenUtils.getToken(user);

    	return ResultGenerator.genSuccessResult(new JSONObject().element("token", token));
	}
	
}
