package com.tyfo.app.jobHandler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tyfo.app.model.message.service.MessageService;

/**
 * @ClassName BaseJobHandler
 * @Description [**JobHander基础类**]
 * @Author Benjamin
 * @Date 2019/12/28 9:46
 * @Version 1.0
 **/
public abstract class BaseJobHandler {
    @Resource
    protected MessageService messageService;

    final Logger LOG = LoggerFactory.getLogger("JobHandler");
}
