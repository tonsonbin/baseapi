package com.tb.app.common.aspect;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.app.common.interceptor.AllInterceptor;
import com.tb.app.common.mapper.JsonMapper;
import com.tb.app.model.sys.entity.RequestLog;
import com.tb.app.model.sys.utils.runner.LogRunnerFactory;

/**
 * 网络请求切片
 */
@Aspect
@Component
public class HttpLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);
    public final String string = "execution(* com.tb.app.model.*.web.*.*(..))";
    private static final String UTF_8 = "utf-8";

    // 定义切点Pointcut
    @Pointcut(string)
    public void logs() {
    }

    //执行切点 之前
    @Before("logs()")
    public void exBefore(JoinPoint joinPoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 打印请求相关参数
        logger.info("========================================== Start ==========================================");
        // 打印请求 url
        logger.debug("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        logger.info("HTTP Method    : {}", request.getMethod());
        // 打印 Http header
        logger.info("HTTP Method    : {}", getHeaders(request));
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        logger.info("Request Args   : {}", formatRequestArgs(request));
    }

    /**
     * 获取头部信息
     *
     * @param request
     * @return
     */
    private String getHeaders(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getHeaderNames();
        Map<String, String> header = new HashMap<>();
        String headerString = "{}";
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            header.put(name, request.getHeader(name));
        }
        try {
            headerString = new ObjectMapper().writeValueAsString(header);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("log error !!", e);
        }
        return headerString;
    }

    /**
     * 重新获取请求参数
     *
     * @param request 请求
     * @return
     */
    private String formatRequestArgs(HttpServletRequest request) {
        String params = "";
        try {
            switch (request.getMethod().toUpperCase()) {
                case "POST":
                    Enumeration<String> enumeration = request.getParameterNames();
                    Map<String, Object> paramsMap = new HashMap<>();
                    while (enumeration.hasMoreElements()) {
                        String key = enumeration.nextElement();
                        paramsMap.put(key, request.getParameter(key));
                    }
                    params = JsonMapper.getMapper().copy().writeValueAsString(paramsMap);
                    break;
                case "GET":
                    params = request.getQueryString();
                    break;
            }
            params = URLDecoder.decode(params==null?"":params, UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("log error !!", e);
        }
        return params;
    }

    // 通知（环绕）
    @Around("logs()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        
        //日志入库处理
        RequestLog requestLog = AllInterceptor.requestInfoThreadLocal.get();
        if (requestLog != null) {
        	requestLog.setFinallyOut(true);
        	requestLog.setResponseJson(result==null?"":result.toString());
            LogRunnerFactory.runResultLog(requestLog);
		}
        //AppUrlInterceptor.
        // 打印出参
        logger.info("Response Args  : {}", JsonMapper.getMapper().copy().writeValueAsString(result));
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }


    // 执行切点之后
    @After("logs()")
    public void exAfter(JoinPoint joinPoint) {
        logger.info("=========================================== End ===========================================");
        // 每个请求之间空一行
        logger.info("");
    }
}
