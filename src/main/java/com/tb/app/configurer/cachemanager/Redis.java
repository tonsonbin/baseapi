package com.tb.app.configurer.cachemanager;

import com.tb.app.common.utils.ApplicationHolder;
import com.tb.app.configurer.redis.RedisUtil;

/**
 * [**缓存**]
 *
 * @ClassName EhCacheUtil
 * @Author Benjamin
 * @Date 2020/6/17 10:22
 * @Version 1.0
 **/
class Redis implements CacheBase{

	RedisUtil redisUtil = ApplicationHolder.getBean(RedisUtil.class);
	
	@Override
	public void put(String key, Object value) {
		redisUtil.set(key, value);
	}

	@Override
	public boolean remove(String key) {
		redisUtil.del(key);
		return true;
	}

	@Override
	public Object get(String key) {
		return redisUtil.get(key);
	}

	
}
