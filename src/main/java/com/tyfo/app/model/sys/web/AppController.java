package com.tyfo.app.model.sys.web;

import com.tyfo.app.common.mapper.annotation.JSON;
import com.tyfo.app.common.web.Result;
import com.tyfo.app.common.web.ResultGenerator;
import com.tyfo.app.model.sys.entity.App;
import com.tyfo.app.model.sys.service.AppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("/app")
@RestController
public class AppController {
    @Resource
    private AppService appService;

    @PostMapping()
    @RequestMapping("/get")
    @JSON(type = App.class, include = "appId,appSecret,appName")
    public Result get(App app) {
        app = appService.get(app);
        return ResultGenerator.genSuccessResult(app);
    }

}
