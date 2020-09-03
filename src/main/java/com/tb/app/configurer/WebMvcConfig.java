package com.tb.app.configurer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.app.common.YamlConfig;
import com.tb.app.common.interceptor.AllUrlInterceptor;
import com.tb.app.common.interceptor.AppUrlInterceptor;
import com.tb.app.common.interceptor.ViewInterceptor;
import com.tb.app.common.mapper.JsonMapper;
import com.tb.app.common.mapper.JsonReturnHandler;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);


    @Bean
    public JsonReturnHandler JsonReturnHandler() {
        return new JsonReturnHandler();
    }

    @Bean
    public AppUrlInterceptor appUrlInterceptor() {
        return new AppUrlInterceptor();
    }
    
    @Bean
    public ViewInterceptor viewInterceptor() {
        return new ViewInterceptor();
    }
    
    /**
     * 所有链接拦截
     * @return
     */
    @Bean
    public AllUrlInterceptor allUrlInterceptor() {
        return new AllUrlInterceptor();
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        jackson2HttpMessageConverter.setSupportedMediaTypes(list);
        new JsonMapper().setUpObjectMap(objectMapper);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(0, jackson2HttpMessageConverter);
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
    	//全路径
    	String all = "/**";
    	//不需要签权
    	String unauth = "/**/unauth/**";
    	//api
    	String api = YamlConfig.getApiPath()+"/**";
    	//view
    	String view = YamlConfig.getViewPath()+"/**";
    	//文件
    	String file = YamlConfig.getApiPath()+"/file/**";
    	//系统自定义的error抛错路径
    	String errorS = "/error";
    	//资源文件路径
    		//项目静态资源
    	String resourceFile = "/static/**";
    		//服务器文件资源
    	String serverFile = YamlConfig.getUserFilesBaseUrl()+"/**";
    	
        //全连接拦截
        registry.addInterceptor(allUrlInterceptor())
        .addPathPatterns(all)
        .order(1)
        .excludePathPatterns(errorS)
        .excludePathPatterns(resourceFile)
        .excludePathPatterns(serverFile)
        .excludePathPatterns(file);//文件不入全过滤器
//      //api资源访问权限
        registry.addInterceptor(appUrlInterceptor())
                .addPathPatterns(all)
                .order(10)
                .excludePathPatterns(unauth)
                .excludePathPatterns(errorS)
                .excludePathPatterns(resourceFile)
                .excludePathPatterns(serverFile)
                .excludePathPatterns(view);
//      //view资源访问权限
        registry.addInterceptor(viewInterceptor())
                .addPathPatterns(all)
                .order(10)
                .excludePathPatterns(unauth)
                .excludePathPatterns(errorS)
                .excludePathPatterns(resourceFile)
                .excludePathPatterns(serverFile)
                .excludePathPatterns(api);
        
    }

    //需要告知系统，这是要被当成静态文件的,
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	registry.addResourceHandler(YamlConfig.getUserFilesBaseUrl()+"/**").addResourceLocations("file:/data/files/");

    	registry.addResourceHandler("/static/**").addResourceLocations("/static/");

    }

    /**
     * 解决ReturnHandler不生效问题
     */
    @PostConstruct
    public void init() {
        final List<HandlerMethodReturnValueHandler> originalHandlers =
                new ArrayList<>(requestMappingHandlerAdapter.getReturnValueHandlers());
        final int deferredPos = obtainValueHandlerPosition(originalHandlers, HttpEntityMethodProcessor.class);
        originalHandlers.add(deferredPos + 1, JsonReturnHandler());
        requestMappingHandlerAdapter.setReturnValueHandlers(originalHandlers);
        return;
    }

    private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers,
                                           Class<?> handlerClass) {
        for (int i = 0; i < originalHandlers.size(); i++) {
            final HandlerMethodReturnValueHandler valueHandler = originalHandlers.get(i);
            if (handlerClass.isAssignableFrom(valueHandler.getClass())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     */
    private boolean validateSign(HttpServletRequest request) {
        String requestSign = request.getParameter("sign");//获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        keys.remove("sign");//排除sign参数
        Collections.sort(keys);//排序

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&");//拼接字符串
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);//去除最后一个'&'

        String secret = "Potato";//密钥，自己修改
        String sign = DigestUtils.md5Hex(linkString + secret);//混合密钥md5

        return StringUtils.equals(sign, requestSign);//比较
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
    
}
