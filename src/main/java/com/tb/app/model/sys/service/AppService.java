package com.tb.app.model.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.app.common.service.CrudService;
import com.tb.app.model.sys.entity.App;
import com.tb.app.model.sys.entity.SysUrl;
import com.tb.app.model.sys.mapper.AppMapper;
import com.tb.app.model.sys.mapper.SysUrlMapper;


/**
 * Created by CodeGenerator on 2019/05/07.
 */
@Service
@Transactional(readOnly = true)
public class AppService extends CrudService<AppMapper, App> {

    @Resource
    private SysUrlMapper sysUrlMapper;

    public App get(String id) {
        return super.get(id);
    }

    public List<SysUrl> findUrlByApp(App app) {
        return sysUrlMapper.findUrlByApp(app);
    }
}