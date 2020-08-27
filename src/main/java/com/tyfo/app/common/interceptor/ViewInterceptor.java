package com.tyfo.app.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tyfo.app.common.utils.Constant;
import com.tyfo.app.model.sys.entity.User;
import com.tyfo.app.model.sys.utils.LoginUtils;

/**
 * @Description 第三方权限拦截器
 * @Author Benjamin
 * @CreateDate 2019-06-10 16:45
 **/
public class ViewInterceptor implements HandlerInterceptor {
	
    /**
     * 
     * 做参数校验，完成后会将业务数据这样处理
     * 
     * req.setAttribute("data", 数据);
     * @throws IOException 
     * 
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws IOException {
        
    	
    	
		return true;
    }
    
}
