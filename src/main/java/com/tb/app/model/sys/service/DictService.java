package com.tb.app.model.sys.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.persistence.Page;
import com.tb.app.common.service.CrudService;
import com.tb.app.configurer.cachemanager.CacheFactory;
import com.tb.app.model.sys.entity.Dict;
import com.tb.app.model.sys.mapper.DictMapper;


/**
 * Created by CodeGenerator on 2021/03/08.
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictMapper, Dict> {
    @Resource
    DictMapper dictMapper;
    Map<String, List<Dict>> mydictMap;
    public static final String CACHE_DICT_MAP = "dictMap";

    public Dict get(String id) {
        return super.get(id);
    }

    public List<Dict> findList(Dict sysDict) {
        return super.findList(sysDict);
    }

    public List<Dict> findAllList(Dict sysDict) {
        return dictMapper.findAllList(sysDict);
    }

    public Page<Dict> findPage(Page<Dict> page, Dict sysDict) {
        return super.findPage(page, sysDict);
    }

    @Transactional(readOnly = false)
    public void save(Dict sysDict) {
        super.save(sysDict);
    }

    @Transactional(readOnly = false)
    public void delete(Dict sysDict) {
        super.delete(sysDict);
    }

    public List<Dict> getDictList(String type) {
    	
    	if (StringUtils.isBlank(type)) {
			throw new ServiceException("type不能为空！");
		}
    	
        @SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap =(Map<String, List<Dict>>) CacheFactory.getCache().get(CACHE_DICT_MAP);
        if (dictMap==null){
            dictMap = Maps.newHashMap();
            for (Dict dict : findAllList(new Dict())){
                List<Dict> dictList = dictMap.get(dict.getType());
                if (dictList != null){
                    dictList.add(dict);
                }else{
                    dictMap.put(dict.getType(), Lists.newArrayList(dict));
                }
            }
            CacheFactory.getCache().put(CACHE_DICT_MAP, dictMap);
        }
        List<Dict> dictList = dictMap.get(type);
        if (dictList == null){
            dictList = Lists.newArrayList();
        }
        return dictList;
    }
}
