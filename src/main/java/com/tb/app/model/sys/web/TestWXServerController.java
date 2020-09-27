package com.tb.app.model.sys.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.web.Result;
import com.tb.app.model.sys.service.TestWXServerService;
import com.tb.app.model.weixin.service.WXAccessTokenService;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${apiPath}/sys/teswx")
@RestController
public class TestWXServerController {
	
	@Resource
	private TestWXServerService testWXServerService;

    /**
     * 需要校验签名
     * @param app
     * @return
     */
    @PostMapping()
    @RequestMapping("/unauth/getAccessToken")
    public Result getAccessToken() {
    	
        return testWXServerService.getAccessToken();
    }
}
