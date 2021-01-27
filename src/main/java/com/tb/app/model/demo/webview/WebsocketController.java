package com.tb.app.model.demo.webview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by CodeGenerator on 2019/06/06.
 */
@RequestMapping("${viewPath}/demo")
@Controller
public class WebsocketController {
	
    /**
     * tag-demo
     * @param model
     * @return
     */
    @RequestMapping("/websocket")
    public ModelAndView demo(ModelAndView model) {

    	model.setViewName("/modules/demo/websocket");
    	
    	return model;
    	
    }
}
