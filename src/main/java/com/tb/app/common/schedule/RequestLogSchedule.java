package com.tb.app.common.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.tb.app.common.utils.ApplicationHolder;
import com.tb.app.common.utils.FormatDate;
import com.tb.app.model.sys.entity.RequestLog;
import com.tb.app.model.sys.service.RequestLogService;

@EnableScheduling
@Configuration
public class RequestLogSchedule {

	/**
	 * 每个月创建一次下一年的日志表格
	 */
	@Scheduled(cron = "0 33 20 29 * *")//"0 15 10 L * ?"
    public void createYearTable() {
		
		RequestLogService requestLogService = ApplicationHolder.getBean(RequestLogService.class);
		 
		//定义表名，定义其中的年
		FormatDate formatDate = FormatDate.getInstance().init();
		
		RequestLog requestLog = new RequestLog();
		requestLog.setYear(String.valueOf(formatDate.year+1));
		
		requestLogService.createYearTable(requestLog);
		
        System.out.println("每月定时任务，创建下一年的日志表");    
    }
	
}
