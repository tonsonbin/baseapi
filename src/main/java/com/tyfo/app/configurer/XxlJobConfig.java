package com.tyfo.app.configurer;

import com.tyfo.app.common.YamlConfig;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 轮询平台
 * @Author Benjamin
 * @CreateDate 2019-05-13 14:49
 **/

//@Configuration
public class XxlJobConfig {

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        /*xxlJobSpringExecutor.setAdminAddresses(YamlConfig.getXxlJobAdminAddresses() + "/xxl-job-admin/");
        xxlJobSpringExecutor.setAppName(YamlConfig.getXxlJobAppName());
        xxlJobSpringExecutor.setPort(Integer.parseInt(YamlConfig.getXxlJobPort()));
        xxlJobSpringExecutor.setAccessToken("");
        xxlJobSpringExecutor.setLogPath("/tmp/nonpurchases");
        xxlJobSpringExecutor.setLogRetentionDays(30);*/
        return xxlJobSpringExecutor;
    }

}

