package com.tb.app.common.interceptor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.utils.Constant;
import com.tb.app.common.utils.MD5;
import com.tb.app.common.utils.aesc.AesCbcUtil;
import com.tb.app.common.web.ResultCode;
import com.tb.app.model.sys.entity.App;
import com.tb.app.model.sys.entity.User;
import com.tb.app.model.sys.service.AppService;
import com.tb.app.model.sys.utils.LoginUtils;

/**
 * @Description 需要做校验的接口拦截
 * @Author Benjamin
 * @CreateDate 2019-06-10 16:45
 **/
public class ApiAuthInterceptor implements HandlerInterceptor {


    private Logger logger = LoggerFactory.getLogger(ApiAuthInterceptor.class);
    
    @Resource
    private AppService appService;
	
    /**
     * 
     * @throws IOException 
     * 
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws IOException {
        
		String SERVER_ASK_TYPE = (String) req.getAttribute("SERVER_ASK_TYPE");
		
		//判断请求来源
		if (Constant.REQ_APPKEY_APP_SELF.equals(SERVER_ASK_TYPE)) {
			
			//token校验
	    	String token = req.getParameter("token");
	    	
	    	//校验登录信息
	    	User user = LoginUtils.checkLogin(token);
	    	
	    	//注入
	    	req.setAttribute(Constant.REQ_ATTR_USER, user);
	    	
	    	return true;
			
		}else if (Constant.REQ_APPKEY_O1.equals(SERVER_ASK_TYPE)) {
			
			//appid
            String appId = req.getHeader("appId");

            if (StringUtils.isBlank(appId)) {
                throw new ServiceException(ResultCode.REQUEST_HEADER_ERROR, "appId参数非法");
            }

            //时间戳
            String timestamp = req.getHeader("timestamp");

            if (StringUtils.isBlank(timestamp)) {
                throw new ServiceException(ResultCode.REQUEST_HEADER_ERROR, "timestamp参数非法");
            }

            //接口版本
            String version = req.getHeader("version");

            if (StringUtils.isBlank(version)) {
                throw new ServiceException(ResultCode.REQUEST_HEADER_ERROR, "version参数非法");
            }

            //签名
            String sign = req.getHeader("sign");

            if (StringUtils.isBlank(sign)) {
                throw new ServiceException(ResultCode.REQUEST_HEADER_ERROR, "version参数非法");
            }

            App app = appService.getCacheAppByAppId(appId);

            if (app == null || StringUtils.isBlank(app.getAppSecret())) {
                throw new ServiceException(ResultCode.APPLICATION_NONE, "非授权应用");
            }
            String data = (String) req.getAttribute("SERVER_ASK_DATA");
            if (StringUtils.isBlank(data)) {
                throw new ServiceException(ResultCode.REQUEST_BODY_ERROR, "未获取到数据内容");
			}

            try {
                String encrypt = AesCbcUtil.encryptMode1(data, app.getAppSecret());

                logger.info("加密出来的数据：{}", encrypt);

                String newSign = MD5.MD5_32bit(encrypt);

                logger.info("签名校验：传入的签名：{}，重新加签:{}", sign, newSign);

                if (!StringUtils.equalsIgnoreCase(sign, newSign)) {
                    throw new ServiceException(ResultCode.UNAUTHORIZED, "签名认证失败");
                }

            } catch (Exception e) {
                throw new ServiceException(ResultCode.UNAUTHORIZED, "签名认证失败");
            }

            //注入
            req.setAttribute(Constant.REQ_ATTR_USER, app);

            return true;
			
		}else if (Constant.REQ_APPKEY_SWAGGER.equals(SERVER_ASK_TYPE)) {
				
		    	return true;
				
		}else{
			
			//其他来源，根据不同type做不同的校验
			throw new ServiceException("未知来源！");
			
		}
    }
    
}
