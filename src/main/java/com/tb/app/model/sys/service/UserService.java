package com.tb.app.model.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.User;


/**
 * Created by CodeGenerator on 2019/05/07.
 */
@Service
@Transactional(readOnly = true)
public class UserService{

	@Transactional(readOnly = false)
    public Result get(String mobile) {

		User user = new User();
		//取用户信息
		user.setLoginName(mobile);
		
    	return ResultGenerator.genSuccessResult(user);
	}
	
}
