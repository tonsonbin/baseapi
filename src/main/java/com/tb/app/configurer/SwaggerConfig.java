package com.tb.app.configurer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(buildApiInf()) // .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.tb.app.model"))// 需要生成文档的包的位置
        .paths(PathSelectors.any())
        .build()
        //.securitySchemes(securitySchemes())
        //.securityContexts(securityContexts())
        ;
    }

    private ApiInfo buildApiInf() {
    return new ApiInfoBuilder()
        .title("测试provide接口详情")
        .description("eureka client构建RESTful APIs")
        .termsOfServiceUrl("")
        .version("1.0")
        .build();
    }
    
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("^(?!auth).*$"))
                .forPaths(PathSelectors.regex("^.*$"))
                .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}