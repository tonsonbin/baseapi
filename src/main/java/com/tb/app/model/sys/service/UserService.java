package com.tb.app.model.sys.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.visitor.functions.If;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.User;
import com.tb.app.model.sys.mapper.UserMapper;


/**
 * Created by CodeGenerator on 2019/05/07.
 */
@Service
@Transactional(readOnly = true)
public class UserService{
	
	@Resource
	private UserMapper userMapper;

	/**
	 * 根据手机号取用户信息
	 * @param mobile
	 * @return
	 */
    public Result getByMobile(String  mobile) {

		User user = userMapper.getByMobile(mobile);
		if (user == null) {
			return ResultGenerator.genFailResult("未找到该用户信息");
		}
		
    	return ResultGenerator.genSuccessResult(user);
	}
	
    /**
     * 根据登录名取用户信息
     * @param loginName
     * @return
     */
    public Result getByLoginName(String  loginName) {

		User userQ = new User();
		//取用户信息
		userQ.setLoginName(loginName);
		User user = userMapper.getByLoginName(userQ);
		if (user == null) {
			return ResultGenerator.genFailResult("未找到该用户信息");
		}
		
    	return ResultGenerator.genSuccessResult(user);
	}
}
