package com.tb.app.jobHandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestJobHandler
 * @Description [**轮询测试类**]
 * @Author Benjamin
 * @Date 2020/1/2 13:12
 * @Version 1.0
 **/
@Component
public class TestJobHandler extends BaseJobHandler {
    @XxlJob("Tester")
    public ReturnT<String> Tester(String params) {
        LOG.info("Tester接收到参数：{}", params);
        return ReturnT.SUCCESS;
    }
}
