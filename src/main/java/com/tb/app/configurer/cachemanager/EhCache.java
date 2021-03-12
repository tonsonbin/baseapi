package com.tb.app.configurer.cachemanager;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * [**缓存**]
 *
 * @ClassName EhCacheUtil
 * @Author Benjamin
 * @Date 2020/6/17 10:22
 * @Version 1.0
 **/
class EhCache implements CacheBase{

	public Cache getCache(String cacheName) {
		
		CacheManager cacheManager = CacheManager.getInstance();
        if (null == cacheManager) {
            return null;
        }
        Cache cache = cacheManager.getCache(cacheName);
        if (null == cache) {
            return null;
        }
        return cache;
        
	}

	@Override
	public void put(String key, Object value) {
		// TODO Auto-generated method stub
		Cache cache = getCache(CacheConstant.COMMON_CACHE);
        if (null != cache) {
            Element element = new Element(key, value);
            cache.put(element);
        }
	}

	@Override
	public boolean remove(String key) {
		Cache cache = getCache(CacheConstant.COMMON_CACHE);
        if (null == cache) {
            return false;
        }
        return cache.remove(key);
	}

	public void removeAll() {
		Cache cache = getCache(CacheConstant.COMMON_CACHE);
        if (null != cache) {
            //logOnRemoveAllIfPinnedCache();
            cache.removeAll();
        }
	}

	@Override
	public Object get(String key) {
		Cache cache = getCache(CacheConstant.COMMON_CACHE);
        if (null == cache) {
            return null;
        }
        Element cacheElement = cache.get(key);
        if (null == cacheElement) {
            return null;
        }
        return cacheElement.getObjectValue();
	}

    
}
