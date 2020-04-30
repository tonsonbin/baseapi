package com.tyfo.app.model.sys.utils.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tyfo.app.common.utils.ApplicationHolder;
import com.tyfo.app.model.sys.entity.RequestLog;
import com.tyfo.app.model.sys.service.RequestLogService;

import net.sf.json.JSONObject;

/**
 * 请求日志保存线程
 * @author Think
 *
 */
class ResultLogRuner implements Runnable {

	Logger logger = LogManager.getLogger(ResultLogRuner.class);
	
	private RequestLog requestLog;
	
	protected  ResultLogRuner() {
		
	}
	/**
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param result 返回结果
	 * @param excetion 抛错信息
	 */
	protected ResultLogRuner(RequestLog requestLog) {
		
		this.requestLog = requestLog;
		
	}
	
	@Override
	public void run() {
		
		try {
			RequestLogService requestLogService = ApplicationHolder.getBean(RequestLogService.class);

			logger.info("日志入库信息："+JSONObject.fromObject(requestLog).toString());
			requestLogService.save(requestLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志入库失败，错误："+e.getLocalizedMessage());
		}
		
	}
    
}
