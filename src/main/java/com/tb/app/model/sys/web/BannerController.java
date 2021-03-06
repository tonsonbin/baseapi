package com.tb.app.model.sys.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.mapper.annotation.JSON;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.Banner;
import com.tb.app.model.sys.service.BannerService;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${apiPath}/sys/banner")
@RestController
public class BannerController {

	@Resource
	private BannerService bannerService;
	
    /**
     * banner列表
     * @param app
     * @return
     */
    @PostMapping()
    @RequestMapping("/unauth/list")
    @JSON(type = Banner.class, include = "url")
    public Result get(Banner banner) {
        
    	//测试
    	List<Banner> banners = bannerService.findList(banner);
    	
        return ResultGenerator.genSuccessResult(banners);
    }

}
