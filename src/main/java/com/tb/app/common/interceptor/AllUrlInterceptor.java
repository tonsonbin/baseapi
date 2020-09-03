package com.tb.app.common.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.security.IdGen;
import com.tb.app.common.utils.httpSend.Log;
import com.tb.app.common.utils.requestInputStream.BufferedServletRequestWrapper;
import com.tb.app.model.sys.entity.RequestLog;

/**
 * @Description 第三方权限拦截器
 * @Author Benjamin
 * @CreateDate 2019-06-10 16:45
 **/
public class AllUrlInterceptor implements HandlerInterceptor {
	
    private Logger logger = LoggerFactory.getLogger(AllUrlInterceptor.class);
    
    //保存日志
    public static final ThreadLocal<RequestLog> requestInfoThreadLocal =
			new NamedThreadLocal<RequestLog>("ThreadLocal requestInfo");

    /**
     * 
     * 全局拦截
     * @throws IOException 
     * 
     */
    @SuppressWarnings("unchecked")
	@Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws IOException {
    	
        //数据库日志存储
        RequestLog requestLog = new RequestLog();
        requestLog.setRequestId(IdGen.uuid());
        
    	//请求的连接
    	String url=req.getRequestURL().toString();
    	
    	//请求日志记录
		logger.info("访问者ip："+Log.getRemortIP(req));
		logger.info("访问者访问连接："+url);
		
		String appKey = req.getParameter("appKey");
		
		requestLog.setIp(Log.getRemortIP(req));
		requestLog.setRequestUrl(url);
		try {

			//请求信息处理
			//非流类型数据
			requestLog.setRequestType(appKey);
			Map<String, String> reqMap = getParameterMap(req);
			String reqString = new ObjectMapper().writeValueAsString(reqMap);
			//流类型数据
			String param = StreamUtils.copyToString(new BufferedServletRequestWrapper(req).getInputStream(), Charset.forName("UTF-8"));
			
			//请求参数
			reqString = "parameterMap："+reqString+"，inputStream："+param;
			
			req.setAttribute("SERVER_ASK_TYPE", requestLog.getRequestType());
			
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
    
    /** 
     * 取传入为json字符串形式的数据
	 * 从request中获得参数Map，并返回可读的Map 
	 * @param request 
	 * @return 
	 */
	public static Map<String,String> getParameterMap(HttpServletRequest request) {  
	    // 参数Map  
	    Map properties = request.getParameterMap();  
	    // 返回值Map  
	    Map<String,String> returnMap = new HashMap<String,String>();  
	    Iterator entries = properties.entrySet().iterator();  
	    Map.Entry<String,String> entry;  
	    String name = "";  
	    String value = "";  
	    while (entries.hasNext()) {  
	        entry = (Map.Entry) entries.next();  
	        name = (String) entry.getKey();  
	        Object valueObj = entry.getValue();  
	        if(null == valueObj){  
	            value = "";  
	        }else if(valueObj instanceof String[]){  
	            String[] values = (String[])valueObj;  
	            for(int i=0;i<values.length;i++){  
	                value = values[i] + ",";  
	            }  
	            value = value.substring(0, value.length()-1);  
	        }else{  
	            value = valueObj.toString();  
	        }  
	        returnMap.put(name, value);  
	    }
	    return returnMap;  
	}
	
}
