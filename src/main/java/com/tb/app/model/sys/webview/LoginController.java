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
public class LoginController  extends BaseController{

    @RequestMapping("/login")
    public String get(ModelAndView model) {
        
    	return "modules/sys/login";
    }
}
