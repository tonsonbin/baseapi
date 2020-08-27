package com.tyfo.app.model.sys.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyfo.app.common.mapper.annotation.JSON;
import com.tyfo.app.common.web.Result;
import com.tyfo.app.common.web.ResultGenerator;
import com.tyfo.app.model.sys.entity.Banner;
import com.tyfo.app.model.sys.service.BannerService;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${apiPath}/banner")
@RestController
public class BannerController {

	@Resource
	private BannerService bannerService;
	
    /**
     * 进入授权过滤的
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
