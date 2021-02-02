package com.tb.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.tb.app.common.utils.ApplicationHolder;

//@EnableEurekaClient
@MapperScan(basePackages="com.tb.app.model.*.mapper")
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true)

//注意要恢复连接数据库，需要将下面的进行注释，并且将类SqlSessionFactoryConfig的注解去掉
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,DruidDataSourceAutoConfigure.class})
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

