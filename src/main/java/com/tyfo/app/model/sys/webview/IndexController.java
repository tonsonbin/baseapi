package com.tyfo.app.model.sys.webview;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tyfo.app.common.exception.ServiceException;
import com.tyfo.app.model.sys.service.BannerService;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${viewPath}/sys")
@Controller
public class IndexController {
    @Resource
    private BannerService bannerService;

    @RequestMapping("/index")
    public ModelAndView get(ModelAndView model) {
    	
    	model.setViewName("/modules/sys/index");
    	
    	return model;
    }

    /**
     * 测试抛错
     * @param model
     * @return
     */
    @RequestMapping("/testError")
    public String testError(ModelAndView model) {
        
    	throw new ServiceException("测试抛错");
    	
    }
}
