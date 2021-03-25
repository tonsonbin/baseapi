package com.tb.app.model.sys.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.utils.Constant;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.User;
import com.tb.app.model.sys.service.UserService;


/**
 * 用户
 * @author tangbin
 * @date 2020年11月18日
 */
@RequestMapping("${apiPath}/sys/user")
@RestController
public class UserController {

	@Resource
	private UserService userService;
	
    /**
     * 获取登录用户信息
     * @param app
     * @return
     */
    @RequestMapping("/info")
    public Result info(@RequestAttribute(Constant.REQ_ATTR_USER)User user) {
    	
    	Result result = userService.getByLoginName(user.getLoginName());
    	
        return ResultGenerator.genSuccessResult(result);
    }
    
}
