package com.tb.app.model.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.app.common.persistence.Page;
import com.tb.app.common.service.CrudService;
import com.tb.app.model.sys.entity.RequestLog;
import com.tb.app.model.sys.mapper.RequestLogMapper;


/**
 * Created by CodeGenerator on 2019/12/30.
 */
@Service
@Transactional(readOnly = true)
public class RequestLogService extends CrudService<RequestLogMapper, RequestLog> {

    public RequestLog get(String id) {
        return super.get(id);
    }

    public List<RequestLog> findList(RequestLog requestLog) {
        return super.findList(requestLog);
    }

    public Page<RequestLog> findPage(Page<RequestLog> page, RequestLog requestLog) {
        return super.findPage(page, requestLog);
    }

    @Transactional(readOnly = false)
    public void save(RequestLog requestLog) {
        super.save(requestLog);
    }
    
    //批量入库
    @Transactional(readOnly = false)
    public void insertBatch(String year,String month,List<RequestLog> requestLogs) {
        dao.insertBatch(year,month,requestLogs);
    }

    @Transactional(readOnly = false)
    public void delete(RequestLog requestLog) {
        super.delete(requestLog);
    }
    
    @Transactional(readOnly = false)
    public void createYearTable(RequestLog requestLog) {
        dao.createYearTable(requestLog);
    }
}
