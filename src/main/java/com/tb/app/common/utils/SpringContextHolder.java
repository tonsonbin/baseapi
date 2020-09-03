package com.tb.app.common.utils;

import org.springframework.context.ConfigurableApplicationContext;

public class SpringContextHolder{

	private static ConfigurableApplicationContext context;

    public static void setContext(ConfigurableApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }
}
