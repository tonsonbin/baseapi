package com.tb.app.model.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tb.app.common.persistence.CrudMapper;
import com.tb.app.model.sys.entity.RequestLog;

public interface RequestLogMapper extends CrudMapper<RequestLog> {

	/***
	 * 创建一年的表
	 * @param requestLog
	 */
	void createYearTable(RequestLog requestLog);

	/**
	 * 批量入库
	 * @param requestLogs
	 */
	void insertBatch(@Param("year")String year,@Param("month")String month,@Param("requestLogs")List<RequestLog> requestLogs);
}