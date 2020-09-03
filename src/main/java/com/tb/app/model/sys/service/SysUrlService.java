package com.tb.app.model.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.app.common.persistence.Page;
import com.tb.app.common.service.CrudService;
import com.tb.app.model.sys.entity.SysUrl;
import com.tb.app.model.sys.mapper.SysUrlMapper;


/**
 * Created by CodeGenerator on 2019/05/07.
 */
@Service
@Transactional(readOnly = true)
public class SysUrlService extends CrudService<SysUrlMapper, SysUrl> {

    public SysUrl get(String id) {
        return super.get(id);
    }

    public List<SysUrl> findList(SysUrl sysUrl) {
        return super.findList(sysUrl);
    }


    public Page<SysUrl> findPage(Page<SysUrl> page, SysUrl sysUrl) {
        return super.findPage(page, sysUrl);
    }

    @Transactional(readOnly = false)
    public void save(SysUrl sysUrl) {
        super.save(sysUrl);
    }

    @Transactional(readOnly = false)
    public void delete(SysUrl sysUrl) {
        super.delete(sysUrl);
    }
}
