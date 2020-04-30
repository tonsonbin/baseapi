package com.tyfo.app.model.sys.mapper;

import com.tyfo.app.common.persistence.CrudMapper;
import com.tyfo.app.model.sys.entity.Area;
import com.tyfo.app.model.sys.entity.Sort;

public interface SortMapper extends CrudMapper<Sort> {
    /**
     * @param sort 分类实体
     * @return com.tyfo.app.model.sys.entity.Area
     * @author Benjamin
     * @description [**通过云商分类ID或飞通分类ID查询连接关系，如果两个都传可以查询是否连接**]
     * @date 9:47 2019/12/29
     * @version 1.0
     **/
    Sort getByTyfoFt(Sort sort);
}