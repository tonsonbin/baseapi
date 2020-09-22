package com.tb.app.model.sys.web;

import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.mapper.annotation.JSON;
import com.tb.app.common.utils.Constant;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.App;
import com.tb.app.model.sys.entity.RequestLog;
import com.tb.app.model.sys.entity.User;
import com.tb.app.model.sys.service.AppService;
import com.tb.app.model.sys.utils.runner.LogRunnerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${apiPath}/test")
@RestController
public class AppController {
    @Resource
    private AppService appService;

    /**
     * 进入授权过滤的
     * @param app
     * @return
     */
    @PostMapping()
    @RequestMapping("/get")
    @JSON(type = App.class, include = "appId,appSecret,appName")
    public Result get(App app,@RequestAttribute(Constant.REQ_ATTR_USER)User user) {
        app = appService.get(app);
        return ResultGenerator.genSuccessResult(app);
    }
    
    /**
     * 跳过授权过滤的
     * @param app
     * @return
     */
    @PostMapping()
    @RequestMapping("/unauth/get")
    @JSON(type = App.class, include = "appId,appSecret,appName")
    public Result unauthget(App app) {
        app = appService.get(app);
        return ResultGenerator.genSuccessResult(app);
    }

    /**
     * 测试数据链
     * @param app
     * @return
     */
    @RequestMapping("/unauth/testDataLink")
    public Result testDataLink() {
    	
    	RequestLog requestLog = new RequestLog();
    	requestLog.setRequestDesc("测试不入库1");
    	requestLog.setRequestUrl("测试不入库1");
    	LogRunnerFactory.runResultLog(requestLog);
    	
    	RequestLog requestLog2 = new RequestLog();
    	requestLog2.setRequestDesc("测试入库2");
    	requestLog2.setRequestUrl("测试入库2");
    	requestLog2.setSave(true);
    	LogRunnerFactory.runResultLog(requestLog2);
    	

    	RequestLog requestLog3 = new RequestLog();
    	requestLog3.setRequestDesc("测试不入库3");
    	requestLog3.setRequestUrl("测试不入库3");
    	requestLog3.setSave(true);
    	LogRunnerFactory.runResultLog(requestLog3);
    	
    	throw new ServiceException("抛错入库");
    	
    }
}
