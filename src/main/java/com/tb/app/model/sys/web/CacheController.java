package com.tb.app.model.sys.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.utils.Constant;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.configurer.cachemanager.CacheBase;
import com.tb.app.configurer.cachemanager.CacheFactory;
import com.tb.app.model.sys.entity.User;


/**
 * 缓存
 * @author tangbin
 * @date 2020年11月18日
 */
@RequestMapping("${apiPath}/sys/cache")
@RestController
public class CacheController {
	
    /**
     * 
     * 清理缓存
     * 
     * @param user
     * @param key
     * @return
     */
    @RequestMapping("/clear")
    public Result clear(@RequestAttribute(Constant.REQ_ATTR_USER)User user,String key) {
    	
    	String userId = user.getId();
    	if (!StringUtils.equals(userId, "1")) {
			return ResultGenerator.genFailResult("无操作权限");
		}
    	
    	CacheBase cacheBase = CacheFactory.getCache();
    	Object data = cacheBase.get(key);
    	if (data == null) {
			return ResultGenerator.genFailResult("无该key对应的缓存");
		}
    	
    	cacheBase.remove(key);
    	
        return ResultGenerator.genSuccessResult("清理完成");
    }
    
}
