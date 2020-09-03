package com.tb.app.model.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tb.app.common.YamlConfig;
import com.tb.app.model.sys.entity.Banner;


/**
 * Created by CodeGenerator on 2019/05/07.
 */
@Service
@Transactional(readOnly = true)
public class BannerService{

    public List<Banner> findList(Banner banner) {
       
    	//测试
    	List<Banner> banners = Lists.newArrayList();
    	
    	banner = new Banner();
    	banner.setUrl(YamlConfig.getServerPath()+"/static/sys/image/muwu.jpg");
    	
    	banners.add(banner);
    	
    	banner = new Banner();
    	banner.setUrl(YamlConfig.getServerPath()+"/static/sys/image/shuijiao.jpg");
    	
    	banners.add(banner);
    	
    	banner = new Banner();
    	banner.setUrl(YamlConfig.getServerPath()+"/static/sys/image/yuantiao.jpg");
    	
    	banners.add(banner);
    	
    	return banners;
    }
}
