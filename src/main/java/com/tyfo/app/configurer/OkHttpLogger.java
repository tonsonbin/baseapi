package com.tyfo.app.configurer;

import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 请输入该文件注解
 * @Author Benjamin
 * @CreateDate 2019-06-24 10:10
 **/
public class OkHttpLogger implements HttpLoggingInterceptor.Logger {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void log(String s) {
        logger.info(s);
    }
}
