package com.tb.app.model.sys.utils.runner;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tb.app.common.security.IdGen;
import com.tb.app.common.utils.ApplicationHolder;
import com.tb.app.common.utils.FormatDate;
import com.tb.app.common.utils.lockTools.LockUtils;
import com.tb.app.model.sys.entity.RequestLog;
import com.tb.app.model.sys.service.RequestLogService;

/**
 * 请求日志保存线程
 * 
 * 整个请求链中，只有有一个请求是需要入库的，那么整个请求链就会入库，便于追踪
 * @author Think
 *
 */
class ResultLogRuner implements Runnable {

	Logger logger = LogManager.getLogger(ResultLogRuner.class);
	
	private RequestLog requestLog;
	private RequestLog requestLogAskMe;
	
	//数据链，key：requestId（非保存数据链），requestId-save（保存数据链）
	//如果有失败数据链才进行整个数据链的入库
	private static Map<String, List<RequestLog>> requestLogInfos = Maps.newHashMap();
	
	protected  ResultLogRuner() {
		
	}
	/**
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param result 返回结果
	 * @param excetion 抛错信息
	 */
	protected ResultLogRuner(RequestLog requestLog,RequestLog requestLogAskMe) {
		
		this.requestLog = requestLog;
		this.requestLogAskMe = requestLogAskMe;
		
	}
	
	@Override
	public void run() {
		
		try {
			RequestLogService requestLogService = ApplicationHolder.getBean(RequestLogService.class);

			//设置默认均为插入日志
			requestLog.preInsert();
			requestLog.setId(IdGen.uuid());
			
			//动态数据表
			FormatDate formatDate = FormatDate.getInstance().init();
			String monthSign = formatDate.getType1Month();
			
			//表格名称
			requestLog.setYear(String.valueOf(formatDate.year));
			requestLog.setMonth(monthSign);
			
			//添加数据链
			List<RequestLog> requestLogs = optionLinkUrl(0,requestLog,requestLogAskMe);
			if (requestLogs != null && requestLogs.size() > 0) {
				
				requestLogService.insertBatch(requestLog.getYear(),requestLog.getMonth(),requestLogs);
				
			}
			
			//是否是终端出口
			Boolean finallyOut = requestLog.isFinallyOut();
			if (finallyOut) {
				
				//是终端出口，取数据
				List<RequestLog> requestLogsF = optionLinkUrl(2,requestLog,requestLogAskMe);
				if (requestLogsF != null && requestLogsF.size() > 0) {
					
					//有需要入库数据
					requestLogService.insertBatch(requestLog.getYear(),requestLog.getMonth(),requestLogsF);
					
				}
				
				//移除数据
				optionLinkUrl(1,requestLog,requestLogAskMe);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志入库失败，错误："+e.getLocalizedMessage());
		}
		
	}
    
	/**
	 * 操作数据链
	 * @param optionType 0-添加，1-删除（删除所有请求链），2-获取数据链（只有当存在保存数据链时才返回数据）
	 * @param requestLog
	 * @return
	 */
	public static List<RequestLog> optionLinkUrl(Integer optionType,RequestLog requestLog,RequestLog requestLogAskMe) {
		
		if (requestLog == null) {
			return null;
		}

		if (requestLogAskMe != null) {
			
			//记录请求链
			if (!StringUtils.equals(requestLog.getRequestUrl(), requestLogAskMe.getRequestUrl())) {
				requestLog.setRequestUrl(requestLogAskMe.getRequestUrl()+"=>>>"+requestLog.getRequestUrl());
			}
			
			//记录请求链id
			if (StringUtils.isNoneBlank(requestLogAskMe.getRequestId())) {
				requestLog.setRequestId(requestLogAskMe.getRequestId());
			}
			
			//同步保存效果
			if (requestLogAskMe.isSave()) {
				requestLog.setSave(requestLogAskMe.isSave());
			}
			
		}else {
			
			//非接口进入，内部直接出的，一般是定时器的请求
			if (StringUtils.isBlank(requestLog.getRequestId())) {

				//未设置请求链id，设置请求链id
				requestLog.setRequestId(IdGen.uuid());
				
			}
			
			//不如数据链，直接返回进行入库
			List<RequestLog> tRequestLogs = Lists.newArrayList();
			tRequestLogs.add(requestLog);
			return tRequestLogs;
			
		}
		
		//取请求链id
		String requestId = requestLog.getRequestId();
		//保存请求链id
		String requestIdSave = requestId+"-save";
		
		LockUtils.lock(requestId);
		
		try {
			
		//保存请求链
		if (!requestLogInfos.containsKey(requestIdSave)) {
			requestLogInfos.put(requestIdSave, Lists.newArrayList());
		}
		List<RequestLog> saveRequestLogs = requestLogInfos.get(requestIdSave);
		
		//普通请求链
		if (!requestLogInfos.containsKey(requestId)) {
			requestLogInfos.put(requestId, Lists.newArrayList());
		}
		List<RequestLog> requestLogs = requestLogInfos.get(requestId);
		
		//是否保存
		Boolean saveBoolean = requestLog.isSave();
		
		
		//判断操作类型
		if (optionType == 0) {
			//添加
			if (saveBoolean) {
				
				//入库请求链
				saveRequestLogs.add(requestLog);
				
			}else {
				
				//普通请求链
				requestLogs.add(requestLog);
				
			}
		}else if (optionType == 1) {
			
			//删除
			requestLogInfos.remove(requestIdSave);
			requestLogInfos.remove(requestId);
			
		}else {
			
			List<RequestLog> resList = Lists.newArrayList();
			//获取
			if (saveRequestLogs.size() >= 1) {
				
				//有保存数据链
				resList.addAll(requestLogs);
				resList.addAll(saveRequestLogs);
				return resList;
				
			}
			
		}
		

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
			LockUtils.unLock(requestId);
			
		}
		
		return null;
	}
}
