package com.tb.app.common.utils;


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
public class EhCacheUtil {

    /**
     * 统一的数据
     */
    public static final String COMMON_CACHE = "common";

    /**
     * @param cacheName 缓存名字
     * @return Cache
     * @Description: 获取缓存
     * @Autor: Jason
     */
    private static Cache getCache(String cacheName) {
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

    /**
     * @param cacheName 缓存名字
     * @param key       缓存key
     * @Description: 新增缓存记录
     * @Autor: Jason
     */
    public static void put(String cacheName, String key,Object value) {
        Cache cache = getCache(cacheName);
        if (null != cache) {
            Element element = new Element(key, value);
            cache.put(element);
        }
    }

    /**
     * @param cacheName 缓存名字
     * @param key       缓存key
     * @return boolean  是否成功删除
     * @Description: 删除缓存记录
     * @Autor: Jason
     */
    public static boolean remove(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        if (null == cache) {
            return false;
        }
        return cache.remove(key);
    }

    /**
     * @param cacheName 缓存名字
     * @Description: 删除全部缓存记录
     * @Autor: Jason
     */
    public static void removeAll(String cacheName) {
        Cache cache = getCache(cacheName);
        if (null != cache) {
            //logOnRemoveAllIfPinnedCache();
            cache.removeAll();
        }
    }

    /**
     * @param cacheName 缓存名字
     * @param key       缓存key
     * @return Object    缓存记录数据Object
     * @Description: 获取缓存记录
     * @Autor: Jason
     */
    public static Object get(String cacheName, String key) {
        Cache cache = getCache(cacheName);
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
