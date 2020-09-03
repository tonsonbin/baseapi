package com.tb.app.model.sys.mapper;

import com.tb.app.common.persistence.CrudMapper;
import com.tb.app.model.sys.entity.App;
import com.tb.app.model.sys.entity.SysUrl;

import java.util.List;

public interface SysUrlMapper extends CrudMapper<SysUrl> {

    List<SysUrl> findUrlByApp(App app);
}