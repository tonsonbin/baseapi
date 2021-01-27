package com.tb.app.model.sys.webview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tb.app.common.web.BaseController;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${viewPath}/sys")
@Controller
public class MyController  extends BaseController{

    @RequestMapping("/my")
    public ModelAndView get(ModelAndView model) {
    	
    	model.setViewName("/modules/sys/my");
    	
    	return model;
    }
}
