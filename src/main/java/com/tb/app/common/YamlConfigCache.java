package com.tb.app.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * cache配置
 * 
 * @Description yaml 读取
 * @Author Benjamin
 * @CreateDate 2018-12-20 16:06
 **/
@Configuration
public class YamlConfigCache {
	
    private static String active;

	@Value("${cache.active}")
	public static void setActive(String active) {
		YamlConfigCache.active = active;
	}
	
	public static String getActive() {
		return active;
	}
	
    
}
