package com.tyfo.app.model.sys.utils.runner;

import org.apache.commons.lang3.StringUtils;

import com.tyfo.app.common.interceptor.AllUrlInterceptor;
import com.tyfo.app.model.sys.entity.RequestLog;

/**
 * 异步日志
 * @author Think
 *
 */
public class LogRunnerFactory {

	/**
	 * 访问及请求日志处理
	 * @param requestLog
	 */
	public static void runResultLog(RequestLog requestLog) {
		
		RequestLog requestLogAskMe = AllUrlInterceptor.requestInfoThreadLocal.get();
		if (requestLogAskMe != null) {
			
			//记录请求链
			if (!StringUtils.equals(requestLog.getRequestUrl(), requestLogAskMe.getRequestUrl())) {
				requestLog.setRequestUrl(requestLogAskMe.getRequestUrl()+"=>>>"+requestLog.getRequestUrl());
			}
			
			//记录请求链id
			if (StringUtils.isNoneBlank(requestLogAskMe.getRequestId())) {
				requestLog.setRequestId(requestLogAskMe.getRequestId());
			}
			
		}
		//设置默认均为插入日志
		requestLog.setIsNewRecord(true);
		
		new Thread(new ResultLogRuner(requestLog)).start();;
		
	}
}
