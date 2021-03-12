package com.tb.app.model.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.app.common.service.CrudService;
import com.tb.app.configurer.cachemanager.CacheFactory;
import com.tb.app.model.sys.entity.App;
import com.tb.app.model.sys.entity.SysUrl;
import com.tb.app.model.sys.mapper.AppMapper;
import com.tb.app.model.sys.mapper.SysUrlMapper;

import net.sf.json.JSONObject;


/**
 * Created by CodeGenerator on 2019/05/07.
 */
@Service
@Transactional(readOnly = true)
public class AppService extends CrudService<AppMapper, App> {

    @Resource
    private SysUrlMapper sysUrlMapper;

    public App get(String id) {
        return super.get(id);
    }

    public List<SysUrl> findUrlByApp(App app) {
        return sysUrlMapper.findUrlByApp(app);
    }

    /**
     * 从缓存中取app信息
     * @param appId
     * @return
     */
	public App getCacheAppByAppId(String appId) {
		
		Object appInfo = CacheFactory.getCache().get(appId);
        App app = null;
        if (appInfo != null) {
        	//缓存中有数据，则从缓存中取
        	app = (App) JSONObject.toBean(JSONObject.fromObject(appInfo.toString()), App.class);
        }
        if (app == null) {
        	//缓存中没有数据，则查询数据库
            App temp = new App();
            temp.setAppId(appId);
            app = get(temp);
            
            if (app != null && StringUtils.isNotBlank(app.getAppId())) {
            	//查到数据，缓存到缓存中
            	CacheFactory.getCache().put(appId, JSONObject.fromObject(app).toString());
            }
        }
        return app;
	}
}
