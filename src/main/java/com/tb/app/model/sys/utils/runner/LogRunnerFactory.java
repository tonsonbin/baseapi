package com.tb.app.model.sys.utils.runner;

import com.tb.app.common.interceptor.AllInterceptor;
import com.tb.app.model.sys.entity.RequestLog;

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
		
		RequestLog requestLogAskMe = AllInterceptor.requestInfoThreadLocal.get();
		
		new Thread(new ResultLogRuner(requestLog,requestLogAskMe)).start();
			
		
	}
	
	/**
	 * 设置该线程日志入库
	 * @param requestLog
	 */
	public static void setInDatabase() {
		
		RequestLog requestLogAskMe = AllInterceptor.requestInfoThreadLocal.get();
		if (requestLogAskMe == null) {
			
			requestLogAskMe = new RequestLog();
			AllInterceptor.requestInfoThreadLocal.set(requestLogAskMe);

		}
		
		requestLogAskMe.setSave(true);
		
	}
}
