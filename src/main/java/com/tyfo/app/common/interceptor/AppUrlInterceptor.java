package com.tyfo.app.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tyfo.app.common.utils.Constant;
import com.tyfo.app.common.web.ServiceException;
import com.tyfo.app.model.sys.entity.RequestLog;
import com.tyfo.app.model.sys.utils.runner.LogRunnerFactory;

/**
 * @Description 第三方权限拦截器
 * @Author Benjamin
 * @CreateDate 2019-06-10 16:45
 **/
public class AppUrlInterceptor implements HandlerInterceptor {
	
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
        
		String SERVER_ASK_TYPE = (String) req.getAttribute("SERVER_ASK_TYPE");
		
		String exception = "";
		try {
			//判断请求来源
			if (Constant.REQ_APPKEY_WXAPP_SELF.equals(SERVER_ASK_TYPE)) {
				
				//自己的微信小程序，校验token什么的
				return true;
				
			}else {
				
				//其他来源，根据不同type做不同的校验
				throw new ServiceException("未知来源！");
				
			}
		} catch (Exception e) {
			
			exception = e.getLocalizedMessage();
			throw new ServiceException(e.getLocalizedMessage());
			
		}finally {
			
			if (StringUtils.isNoneBlank(exception)) {//抛错的时候才存入日志

				RequestLog requestLog = AllUrlInterceptor.requestInfoThreadLocal.get();
		        if (requestLog != null) {
		        	requestLog.setException(exception);
		            LogRunnerFactory.runResultLog(requestLog);
				}
			}
			
		}
    }
    
}
