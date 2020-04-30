package com.tyfo.app.model.sys.utils.runner;

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
			
			requestLog.setRequestUrl(requestLogAskMe.getRequestUrl()+"=>>>"+requestLog.getRequestUrl());
			
		}
		
		new Thread(new ResultLogRuner(requestLog)).start();;
		
	}
}
