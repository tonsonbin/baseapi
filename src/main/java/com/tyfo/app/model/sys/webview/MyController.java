package com.tyfo.app.model.sys.webview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${viewPath}/sys")
@Controller
public class MyController {

    @RequestMapping("/my")
    public ModelAndView get(ModelAndView model) {
    	
    	model.setViewName("/modules/sys/my");
    	
    	return model;
    }
}
