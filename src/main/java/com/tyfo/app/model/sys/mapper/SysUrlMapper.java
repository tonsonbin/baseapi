package com.tyfo.app.model.sys.mapper;

import com.tyfo.app.common.persistence.CrudMapper;
import com.tyfo.app.model.sys.entity.App;
import com.tyfo.app.model.sys.entity.SysUrl;

import java.util.List;

public interface SysUrlMapper extends CrudMapper<SysUrl> {

    List<SysUrl> findUrlByApp(App app);
}