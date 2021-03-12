package com.tb.app.configurer.cachemanager;

public interface CacheBase {

    /**
     * @param cacheName 缓存名字
     * @param key       缓存key
     * @Description: 新增缓存记录
     * @Autor: Jason
     */
    public void put(String key,Object value);

    /**
     * @param cacheName 缓存名字
     * @param key       缓存key
     * @return boolean  是否成功删除
     * @Description: 删除缓存记录
     * @Autor: Jason
     */
    public boolean remove(String key);

    /**
     * @param cacheName 缓存名字
     * @param key       缓存key
     * @return Object    缓存记录数据Object
     * @Description: 获取缓存记录
     * @Autor: Jason
     */
    public Object get(String key);
}
