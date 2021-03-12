package com.tb.app.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description 第三方权限拦截器
 * @Author Benjamin
 * @CreateDate 2019-06-10 16:45
 **/
public class ViewInterceptor implements HandlerInterceptor {
	
    /**
     * @throws IOException 
     * 
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws IOException {
        
    	
    	
		return true;
    }
    
}
