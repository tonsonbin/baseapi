package com.tb.app.configurer.cachemanager;

import com.tb.app.common.YamlConfigCache;

public class CacheFactory {
	
	/**
	 * 取生效的cache
	 * @return
	 */
	public static CacheBase getCache() {
		
		if (CacheConstant.CACHE_ECACHE.equals(YamlConfigCache.getActive())) {
			
			return new EhCache();
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * 根据类型取cache实例
	 * 
	 * @param type @see CacheConstant 缓存实例
	 * @return
	 */
	public static CacheBase getCache(String type) {
		
		if (CacheConstant.CACHE_ECACHE.equals(type)) {
			
			return new EhCache();
			
		}
		
		return null;
	}
}
