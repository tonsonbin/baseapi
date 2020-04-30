package com.tyfo.app.model.sys.mapper;

import com.tyfo.app.common.persistence.CrudMapper;
import com.tyfo.app.model.sys.entity.Area;

public interface AreaMapper extends CrudMapper<Area> {
    /**
     * @param area 区域实体
     * @return com.tyfo.app.model.sys.entity.Area
     * @author Benjamin
     * @description [**通过云商区域ID或飞通区域ID查询连接关系，如果两个都传可以查询是否连接**]
     * @date 9:47 2019/12/29
     * @version 1.0
     **/
    Area getByTyfoFt(Area area);
}