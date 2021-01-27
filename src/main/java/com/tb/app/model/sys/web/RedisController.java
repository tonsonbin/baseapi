package com.tb.app.model.sys.web;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.configurer.redis.RedisUtil;

import groovy.util.logging.Slf4j;

@Slf4j
@RequestMapping("${apiPath}/redis")
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("unauth/set")
    public boolean redisset(String key, String value){

        return redisUtil.set(key,value);
    }

    @RequestMapping("unauth/get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("unauth/expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}
