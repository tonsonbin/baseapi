package com.tb.app.model.sys.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.configurer.cachemanager.CacheConstant;
import com.tb.app.configurer.cachemanager.CacheFactory;
import com.tb.app.configurer.redis.RedisUtil;

import groovy.util.logging.Slf4j;

@Slf4j
@RequestMapping("${apiPath}/redis")
@RestController
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("unauth/set")
    public boolean redisset(String key, String value){

        CacheFactory.getCache(CacheConstant.CACHE_REDIS).put(key, value);
        
        return true;
    }

    @RequestMapping("unauth/get")
    public Object redisget(String key){
        return CacheFactory.getCache(CacheConstant.CACHE_REDIS).get(key);
    }
}
