package com.tyfo.app;

import com.tyfo.app.common.utils.ApplicationHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


@MapperScan("com.tyfo.app.model.*.mapper")
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
        ApplicationHolder.setApplication(application);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //注入application对象实例
        application.application().addInitializers(ApplicationHolder::setApplication);
        return application.sources(Application.class);
    }

}

