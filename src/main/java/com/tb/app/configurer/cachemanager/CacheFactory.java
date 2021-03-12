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
}
