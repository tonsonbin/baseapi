package com.tyfo.app.model.sys.webview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${viewPath}/sys")
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String get(ModelAndView model) {
        
    	return "modules/sys/login";
    }
}
