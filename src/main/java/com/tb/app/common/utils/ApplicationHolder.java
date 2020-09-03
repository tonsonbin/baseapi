package com.tb.app.common.utils;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName ApplicationHolder
 * @Description [**请输入类注释**]
 * @Author Benjamin
 * @Date 2019/12/31 21:54
 * @Version 1.0
 **/
public class ApplicationHolder {
    private static ConfigurableApplicationContext application = null;

    public static void setApplication(ConfigurableApplicationContext application) {
        ApplicationHolder.application = application;
    }
    public static  <T> T getBean(Class<T> zClass) {
        return application.getBean(zClass);
    }
}
