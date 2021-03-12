package com.tb.app.common.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.interceptor.http.HttpHelper;
import com.tb.app.common.security.IdGen;
import com.tb.app.common.utils.Constant;
import com.tb.app.common.utils.httpSend.Log;
import com.tb.app.common.utils.requestInputStream.BufferedServletRequestWrapper;
import com.tb.app.model.sys.entity.RequestLog;

/**
 * @Description 所有入口请求拦截
 * @Author Benjamin
 * @CreateDate 2019-06-10 16:45
 **/
public class AllInterceptor implements HandlerInterceptor {
	
    private Logger logger = LoggerFactory.getLogger(AllInterceptor.class);
    
    //保存日志
    public static final ThreadLocal<RequestLog> requestInfoThreadLocal =
			new NamedThreadLocal<RequestLog>("ThreadLocal requestInfo");

    /**
     * 
     * 全局拦截
     * @throws IOException 
     * 
     */
	@Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws IOException {
    	
        //数据库日志存储
        RequestLog requestLog = new RequestLog();
        requestLog.setRequestId(IdGen.uuid());
        
    	//请求的连接
    	String url=req.getRequestURL().toString();
    	
    	//打印header
    	StringBuffer sbBuffer = new StringBuffer();
    	Enumeration<String> headerNames = req.getHeaderNames();
    	while (headerNames.hasMoreElements()) {
    		
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			sbBuffer.append("header-"+headerName+":"+headerValue);
			
		}
    	//请求日志记录
		logger.info("访问者ip："+Log.getRemortIP(req));
		logger.info("访问者header："+sbBuffer.toString());
		logger.info("访问者访问连接："+url);
		
		String appKey = req.getParameter("appKey");
		//swagger处理
		if (StringUtils.isNoneBlank(url) && (url.contains("/webjars/") || url.contains("/swagger"))) {
			appKey = Constant.REQ_APPKEY_SWAGGER;
		}
		
		requestLog.setIp(Log.getRemortIP(req));
		requestLog.setRequestUrl(url);
		try {

			//请求信息处理
			//非流类型数据
			requestLog.setRequestType(appKey);
			Map<String, String> reqMap = HttpHelper.getParameterMap(req);
			String reqString = new ObjectMapper().writeValueAsString(reqMap);
			//流类型数据
			String param = StreamUtils.copyToString(new BufferedServletRequestWrapper(req).getInputStream(), Charset.forName("UTF-8"));
			
			//请求参数
			reqString = "parameterMap："+reqString+"，inputStream："+param;
			
			req.setAttribute("SERVER_ASK_TYPE", requestLog.getRequestType());
			req.setAttribute("SERVER_ASK_DATA", param);
			
			logger.info("访问者访问参数parameterMap："+reqString+",inputStream："+param);
			requestLog.setRequestJson(reqString);
			
		} catch (Exception e) {
			
			throw new ServiceException(e.getLocalizedMessage());
			
		}finally {
			
			//默认请求不入库
			//requestLog.setSave(true);
			requestInfoThreadLocal.set(requestLog);
			
		}
		
        //req.setAttribute("appid", appid);
        return true;
    }
    
}
